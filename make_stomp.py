"""Original synthesized impact and debris effect; no third party sample."""
import math, random, struct, wave
from pathlib import Path
random.seed(31)
rate=22050
samples=[]
low=0.0
for i in range(int(rate*.72)):
 t=i/rate
 noise=random.uniform(-1,1)
 low=.82*low+.18*noise
 boom=math.sin(2*math.pi*(74*t-25*t*t))*math.exp(-t*8)
 crack=noise*math.exp(-t*48)
 rubble=low*math.exp(-t*4)*(0.65+.35*math.sin(t*113))
 envelope=min(1,t/.003)*min(1,(.72-t)/.03)
 value=(.6*boom+.2*crack+.65*rubble)*envelope
 samples.append(struct.pack('<h',int(max(-.95,min(.95,value))*32767)))
with wave.open(str(Path(__file__).parent/'res/raw/stomp.wav'),'wb') as f:
 f.setnchannels(1);f.setsampwidth(2);f.setframerate(rate);f.writeframes(b''.join(samples))
