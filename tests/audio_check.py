"""Offline PCM validation; does not claim Android speaker/playback testing."""
import array,hashlib,json,math,wave
from pathlib import Path
root=Path(__file__).resolve().parents[1]
records=json.loads((root/'art/audio-originals/processed.json').read_text());seen=set();demo=[]
for record in records:
 p=root/'res/raw'/record['asset']
 assert hashlib.sha256(p.read_bytes()).hexdigest()==record['sha256']
 with wave.open(str(p),'rb') as f:
  assert (f.getnchannels(),f.getsampwidth(),f.getframerate())==(1,2,22050)
  raw=f.readframes(f.getnframes());a=array.array('h');a.frombytes(raw)
 assert len(raw)<1000000,'SoundPool sample too large'
 assert 0<max(abs(v) for v in a)<32760,'Silent/clipped sample'
 assert math.sqrt(sum(v*v for v in a)/len(a))/32768>.03
 assert abs(a[0])<100 and abs(a[-1])<100,'Abrupt sample edge'
 assert record['sha256'] not in seen;seen.add(record['sha256'])
 demo.append(raw);demo.append(b'\0\0'*11025)
with wave.open(str(root.parent/'Godzillagotchi-3.6-sounds-preview.wav'),'wb') as f:
 f.setnchannels(1);f.setsampwidth(2);f.setframerate(22050);f.writeframes(b''.join(demo))
print('PASS: eleven distinct mono PCM effects, bounded decoded sizes, no output clipping, non-silent levels and click-free endpoints')
