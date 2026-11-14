package com.nova.godzillagotchi;
public class Pet {
 public final Outings outings = new Outings();
 public double food=80,joy=80,energy=85,clean=90;public int xp=0;public boolean asleep=false;public long updated;
 public long lastHappy=-10000;
 public String name="Godzilla";public long birthday,lastBond=-60000,lastGame=-600000,hatchedAt;public boolean hatched=true,adopted=false,lightsOff=false,sick=false;
 public int eggTaps=0,messes=0,meals=0,baths=0,bonds=0,games=0,medicines=0,favorite=0;
 public double weight=25,poopHours=0,poorHours=0,wellHours=0,sleepHours=0;
 public Pet(long now){updated=birthday=now;favorite=(int)Math.floorMod(now/1000,3);}
 public void tick(long now){if(now<=updated)return;double h=Math.min(24,(now-updated)/3600000.0);updated=now;if(!hatched)return;
  // Small bounded steps make offline care similar to foreground care, including mess/illness thresholds.
  int careBefore=(int)(wellHours/6);
  while(h>0){double dt=Math.min(h,1.0/60);h-=dt;food=clip(food-dt*4);joy=clip(joy-dt*3);clean=clip(clean-dt*(2+messes*.8));energy=clip(energy+dt*(asleep?(lightsOff?14:8):-3));
   poopHours+=dt;if(poopHours>=3){poopHours-=3;messes=Math.min(3,messes+1);}
   if(food<20||clean<20)poorHours+=dt;else poorHours=Math.max(0,poorHours-dt);
   if(poorHours>=4)sick=true;
   if(food>=50&&joy>=50&&clean>=50&&!sick)wellHours+=dt;
   if(asleep&&lightsOff)sleepHours+=dt;
  }
  xp+=(int)(wellHours/6)-careBefore;
 }
 public static double clip(double n){return Math.max(0,Math.min(100,n));}
 public static final int[] LEVEL_XP={0,250,750,1800,3600};
 public int level(){int n=0;while(n<4&&xp>=LEVEL_XP[n+1])n++;return n;}
 public String stage(){return !hatched?"RADIOACTIVE EGG":new String[]{"HATCHLING","JUNIOR KAIJU","ADULT KAIJU","TITAN","KING OF MONSTERS"}[level()];}
 public float sizeScale(){return .74f+level()*.09f;}
 public static int damage(int move,int level){return Math.round(new int[]{14,18,21,34}[move]*(1+Math.max(0,Math.min(4,level))*.20f));}
 public boolean drinkRush(){if(!hatched||asleep)return false;energy=clip(energy+33);return true;}
 private final int[] harborFish = new int[3];
 public int harborFish(int type) { return harborFish[type]; }
 public void restoreHarborFish(int type, int count) { harborFish[type] = Math.max(0,count); }
 public void storeHarborFish(int type) {
  if (harborFish[type] < Integer.MAX_VALUE) harborFish[type]++;
 }
 public void feed(){feed(favorite);}
 public void feed(int fish){if(!hatched||asleep)return;boolean fromHarbor=fish>=0&&fish<harborFish.length&&harborFish[fish]>0;if(fromHarbor)harborFish[fish]--;food=clip(food+(fromHarbor?30:22));clean=clip(clean-4);xp+=2;meals++;weight=Math.min(80,weight+.3);if(fish==favorite)joy=clip(joy+5);}
 public boolean cleanMess(){if(!hatched||messes<=0)return false;messes--;clean=clip(clean+10);joy=clip(joy+1);return true;}
 public void wash(){if(!hatched)return;clean=clip(clean+30);joy=clip(joy+3);messes=0;poopHours=0;baths++;xp+=2;}
 public void play(){joy=clip(joy+25);energy=clip(energy-12);food=clip(food-7);xp+=12;}
 public boolean roar(){if(!hatched||sick||energy<15)return false;energy-=15;joy=clip(joy+8);xp+=4;return true;}
 public boolean hatch(long now){if(hatched)return false;if(++eggTaps<5)return false;hatched=true;birthday=hatchedAt=now;updated=now;return true;}
 public boolean bond(long now){if(!hatched||asleep||now-lastBond<60000)return false;lastBond=now;bonds++;joy=clip(joy+8);xp++;return true;}
 public boolean medicine(){if(!sick)return false;sick=false;poorHours=0;medicines++;joy=clip(joy+3);return true;}
 public boolean gameReward(long now){if(!hatched||asleep||sick||now-lastGame<600000)return false;lastGame=now;games++;joy=clip(joy+15);energy=clip(energy-3);weight=Math.max(15,weight-.2);xp+=5;return true;}
 public String favoriteFood(){return new String[]{"Salmon","Tuna","Sardines"}[favorite];}
 public String personality(){if(bonds+games>=8)return "PLAYFUL";if(sleepHours>=12&&sleepHours>wellHours*.6)return "DREAMER";if(wellHours>=24)return "GENTLE";return "CURIOUS";}
 public String form(){if(level()<2)return "Crest develops at Adult";return personality().equals("PLAYFUL")?"Heart crest":personality().equals("DREAMER")?"Moon crest":personality().equals("GENTLE")?"Guardian crown":"Explorer diamond";}
 public long ageDays(long now){return Math.max(0,(now-birthday)/86400000);}
 public String need(boolean night){if(!hatched)return "Tap the egg to help it hatch!";if(sick)return "Tummy ache / CARE > MEDICINE";if(asleep)return lightsOff?"Sleeping peacefully...":"Please turn off my light / CARE";if(food<30)return "Hungry! A fish would be lovely.";if(messes>0)return "Tap a mess to clean it up!";if(clean<30)return "Bath time, please!";if(energy<30||night)return "Sleepy... time for bed?";if(joy<35)return "Bored... pet me or play a game!";return name+" / "+personality().toLowerCase();}
 public int expression(long now){if(sick)return 2;if(food<30)return 0;if(joy<35)return 1;if(now-lastHappy<3500||now-lastBond<3500||now-lastGame<3500||now-hatchedAt<3500&&hatchedAt>0)return 3;return -1;}
}
