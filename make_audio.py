"""Deterministic care Foley and normalized, silence-trimmed source roars.
Use --ffmpeg /path/to/ffmpeg to rebuild the roar WAVs from art/audio-originals.
No pitch shifting of the monster recordings. Standard library only.
"""
from pathlib import Path
import argparse,array,json,math,random,subprocess,wave,hashlib
ROOT=Path(__file__).resolve().parent
RATE=22050

def save(name,values,peak=.76):
 values=list(values);maximum=max(abs(x) for x in values) or 1
 gain=peak/maximum
 pcm=array.array('h',(round(max(-.98,min(.98,x*gain))*32767) for x in values))
 path=ROOT/'res/raw'/(name+'.wav')
 with wave.open(str(path),'wb') as f:
  f.setnchannels(1);f.setsampwidth(2);f.setframerate(RATE);f.writeframes(pcm.tobytes())
 return {'asset':path.name,'duration':round(len(pcm)/RATE,3),'peak':round(max(abs(x) for x in pcm)/32768,4),'rms':round(math.sqrt(sum(x*x for x in pcm)/len(pcm))/32768,4),'sha256':hashlib.sha256(path.read_bytes()).hexdigest()}

def care():
 rng=random.Random(731);b=[0.] * int(RATE*1.8)
 # A bath-length sequence of resonant water plips: rising bubble pitch and soft fizz.
 for j in range(24):
  start=.025+j*.067+rng.uniform(0,.025);dur=rng.uniform(.065,.15);freq=rng.uniform(280,1050);amp=rng.uniform(.2,.6);phase=0
  for k in range(int(dur*RATE)):
   i=int(start*RATE)+k
   if i>=len(b):break
   t=k/RATE;u=t/dur;phase+=2*math.pi*freq*(1+1.7*u)/RATE
   env=min(1,t/.003)*math.exp(-u*5)*(1-u)
   b[i]+=amp*env*(math.sin(phase)+.22*math.sin(phase*2)+rng.uniform(-.07,.07))
 records=[save('bubbles',b,.65)]
 return records

def roars(ffmpeg):
 records=[]
 for name in ['mechagodzilla','kong','ghidorah','rodan','gigan','anguirus']:
  data=subprocess.check_output([ffmpeg,'-v','error','-i',str(ROOT/'art/audio-originals'/(name+'.ogg')),'-ac','1','-ar',str(RATE),'-f','s16le','-'])
  a=array.array('h');a.frombytes(data);values=[v/32768 for v in a]
  window=220;active=[i for i in range(0,len(a),window) if math.sqrt(sum(v*v for v in values[i:i+window])/len(values[i:i+window]))>.012]
  first=max(0,active[0]-int(.025*RATE));last=min(len(a),active[-1]+window+int(.09*RATE));last=min(last,first+int(2.0*RATE)) if name=="kong" else last;values=values[first:last]
  # Gentle edges avoid clicks; moderate level targets reduce harsh differences between recordings.
  rms=math.sqrt(sum(x*x for x in values)/len(values));peak=min(.78,max(abs(x) for x in values)*(.18/rms))
  for i in range(len(values)):
   values[i]*=min(1,i/(RATE*.008),(len(values)-1-i)/(RATE*.045))
  record=save('roar_'+name,values,peak);record.update({'source_start':round(first/RATE,3),'source_end':round(last/RATE,3)});records.append(record)
 return records

def drink(ffmpeg):
 # Three differently pitched takes of a recorded swallow, timed to the raised can.
 source=ROOT/'art/audio-originals/swallow_gulp.mp3'
 mix=[0.0]*int(RATE*1.6)
 for start,speed,volume in [(.02,.92,.86),(.52,1.02,.94),(1.02,.96,1.0)]:
  data=subprocess.check_output([ffmpeg,'-v','error','-ss','0.19','-i',str(source),
   '-t','0.37','-af',f'asetrate=44100*{speed},highpass=f=85',
   '-ac','1','-ar',str(RATE),'-f','s16le','-'])
  samples=array.array('h');samples.frombytes(data)
  offset=int(start*RATE)
  for i,sample in enumerate(samples):
   edge=min(1,i/(RATE*.012),(len(samples)-1-i)/(RATE*.034))
   mix[offset+i]+=sample/32768*edge*volume
 record=save('drink',mix,.85)
 record.update({'source_file':source.name,'source_start':.19})
 return record

def foley(ffmpeg):
 records=[]
 for name,filename,start,duration in [('munch','chewing.mp3',20.8,1.6),('wilhelm','wilhelm.ogg',0,1.6),('poop_splatter','poop_splatter.mp3',0,1.0)]:
  data=subprocess.check_output([ffmpeg,'-v','error','-ss',str(start),'-i',str(ROOT/'art/audio-originals'/filename),'-t',str(duration),'-ac','1','-ar',str(RATE),'-f','s16le','-'])
  a=array.array('h');a.frombytes(data);values=[v/32768 for v in a]
  for i in range(len(values)):values[i]*=min(1,i/(RATE*.008),(len(values)-1-i)/(RATE*.025))
  record=save(name,values,.72);record.update({'source_file':filename,'source_start':start,'source_duration':duration});records.append(record)
 return records

if __name__=='__main__':
 parser=argparse.ArgumentParser();parser.add_argument('--ffmpeg');args=parser.parse_args()
 records=care()
 if args.ffmpeg:records += [drink(args.ffmpeg)] + foley(args.ffmpeg) + roars(args.ffmpeg)
 else:
  previous=json.loads((ROOT/'art/audio-originals/processed.json').read_text())
  records += [r for r in previous if r['asset']!='bubbles.wav']
 (ROOT/'art/audio-originals/processed.json').write_text(json.dumps(records,indent=2)+'\n')
 print(json.dumps(records,indent=2))
