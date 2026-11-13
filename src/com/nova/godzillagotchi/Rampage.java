package com.nova.godzillagotchi;
/** One building at a time. All timers use active-play milliseconds, so backgrounding pauses attacks. */
public final class Rampage {
 public static final int BUILDINGS=1;
 public int landmark=0;
 public int building=0,hp=maxHp(0),health=100,charge=60,level,move=-1,damage,phase=0,kills=0,spawnCount=0;
 public long time=0,eventAt=-10000,nextSpawn=3200,lastClock=-1,hitAt=-10000,shotAt=-10000,popAt=-10000;
 public float popX,popY,shotX,shotY;
 public boolean finished=false,rewarded=false;
 public String log="Break this tower. Tap moving threats!";
 public final Vehicle[] vehicles=new Vehicle[3];
 public static final class Vehicle {public int type;public long born,lastShot;public Vehicle(int t,long at){type=t;born=lastShot=at;}}
 public Rampage(int level){this(level,0);}
 public Rampage(int level,int type){this.level=level;landmark=Landmarks.valid(type);log="Smash "+Landmarks.NAMES[landmark]+". Tap moving threats!";}
 public static int maxHp(int building){return 150+building*20;}
 public boolean won(){return finished&&building==BUILDINGS;}
 public boolean canAttack(int m){return !finished&&phase==0&&m>=0&&m<4&&(m!=3||charge>=45);}
 public boolean attack(int m){if(!canAttack(m))return false;move=m;damage=Pet.damage(m,level);phase=1;eventAt=time;charge=m==3?charge-45:Math.min(100,charge+(m==0?18:m==1?12:8));log=Battle.MOVES[m]+" / wind-up";return true;}
 public boolean tick(long now){if(lastClock<0){lastClock=now;return false;}long dt=Math.max(0,now-lastClock);lastClock=now;if(finished)return false;time+=dt;boolean impact=false;
  if(phase==1&&time-eventAt>=Battle.HIT_MS[move]){hp=Math.max(0,hp-damage);hitAt=time;phase=2;impact=true;log=Battle.MOVES[move]+" -"+damage+(hp==0?" / COLLAPSING!":" / paper tearing");}
  if(phase==2&&time-eventAt>=Battle.DURATION_MS[move]&&(hp>0||time-hitAt>=1600)){if(hp==0){building++;if(building>=BUILDINGS){finished=true;for(int i=0;i<vehicles.length;i++)vehicles[i]=null;log="BUILDING SMASHED! +20 XP";return impact;}}phase=0;}
  // At most three threats, with a deterministic rotating roster and varied gaps.
  if(time>=nextSpawn){for(int i=0;i<vehicles.length;i++)if(vehicles[i]==null){vehicles[i]=new Vehicle(spawnCount%3,time);spawnCount++;break;}nextSpawn=time+4200+(spawnCount%3)*700;}
  for(int i=0;i<vehicles.length;i++){Vehicle v=vehicles[i];if(v==null)continue;if(time-v.born>lifetime(v)){vehicles[i]=null;continue;}if(time-v.lastShot>=2200){health=Math.max(0,health-(v.type==0?3:v.type==1?4:5));v.lastShot=time;shotAt=time;shotX=x(v)+width(v)/2;shotY=y(v)+height(v)/2;if(health==0){finished=true;log="RETREAT! Your pet is safe.";break;}}}
  return impact;
 }
 public void pause(){lastClock=-1;}
 public static long lifetime(Vehicle v){return v.type==0?10500:v.type==1?8500:6500;}
 public static float width(Vehicle v){return v.type==0?64:v.type==1?74:72;}
 public static float height(Vehicle v){return v.type==0?34:40;}
 public float x(Vehicle v){float f=Math.min(1,(time-v.born)/(float)lifetime(v));return 588-f*510;}
 public float y(Vehicle v){return v.type==0?383:v.type==1?186+(float)Math.sin((time-v.born)/450.0)*12:240+(float)Math.sin((time-v.born)/650.0)*20;}
 public int target(float x,float y){for(int i=vehicles.length-1;i>=0;i--){Vehicle v=vehicles[i];if(v!=null&&x>=x(v)-10&&x<=x(v)+width(v)+10&&y>=y(v)-12&&y<=y(v)+height(v)+12)return i;}return -1;}
 public boolean tap(float x,float y){if(finished)return false;int i=target(x,y);if(i<0)return false;Vehicle v=vehicles[i];popX=x(v)+width(v)/2;popY=y(v)+height(v)/2;popAt=time;vehicles[i]=null;kills++;charge=Math.min(100,charge+5);log="Threat stopped! +5 charge";return true;}
 public boolean claimReward(Pet p){if(!finished||rewarded)return false;rewarded=true;p.joy=Pet.clip(p.joy+(won()?25:5));if(won())p.xp+=20;return true;}
}
