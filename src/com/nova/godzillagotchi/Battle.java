package com.nova.godzillagotchi;
/** Timed battle phases: ready, player hit pending, player recovery, enemy hit pending, enemy recovery. */
public final class Battle {
 public static final String[] NAMES={"MECHAGODZILLA","KING KONG","KING GHIDORAH","RODAN","GIGAN","ANGUIRUS"};
 public static final String[] MOVES={"PUNCH","KICK","TAIL","BREATH"};
 public static final String[] SPECIALS={"LASER CANNON","BARREL THROW","GRAVITY BEAMS","WING STRIKE","HOOK SLASH","SPIKE ROLL"};
 public static final int[] MAX_HP={112,100,128,90,108,116},POWER={12,13,14,10,12,11},WEAKNESS={2,3,2,1,0,3};
 public static final int[] HIT_MS={380,470,560,900},DURATION_MS={1100,1300,1450,2200};
 public int level=0;
 public int damage(int move){return Pet.damage(move,level)+(move==WEAKNESS[opponent]?6:0);}
 public int opponent,playerHp=100,enemyHp,charge=60,turn=0,lastMove=-1,lastDamage=0,lastEnemyDamage=0,phase=0;
 public boolean waiting,finished,rewarded,enemyEvent;public long nextAt,eventAt=-10000,remaining=0,eventAge=10000;public String log="Choose an attack.";
 public Battle(int opponent){if(opponent<0||opponent>=NAMES.length)throw new IllegalArgumentException("opponent");this.opponent=opponent;enemyHp=MAX_HP[opponent];}
 public boolean canAttack(int move){return !finished&&!waiting&&phase==0&&move>=0&&move<4&&(move!=3||charge>=45);}
 public boolean attack(int move,long now){if(!canAttack(move))return false;
  lastMove=move;lastDamage=damage(move);
  charge=move==3?charge-45:Math.min(100,charge+(move==0?18:move==1?12:8));turn++;eventAt=now;eventAge=0;enemyEvent=false;
  log=move==3?"Charging atomic breath...":MOVES[move]+" / wind-up";waiting=true;phase=1;remaining=HIT_MS[move];nextAt=now+remaining;return true;
 }
 public int enemyHitMs(){return turn%3==0?new int[]{650,700,850,600,550,680}[opponent]:450;}
 public int enemyDurationMs(){return turn%3==0?new int[]{1450,1500,1800,1350,1300,1450}[opponent]:1000;}
 public boolean tick(long now){if(!waiting||finished||now<nextAt)return false;
  if(phase==1){enemyHp=Math.max(0,enemyHp-lastDamage);log=MOVES[lastMove]+" -"+lastDamage+(lastMove==WEAKNESS[opponent]?"  SUPER EFFECTIVE!":"");phase=2;nextAt=eventAt+DURATION_MS[lastMove];}
  else if(phase==2){if(enemyHp==0){finish(true);return true;}enemyEvent=true;eventAt=now;eventAge=0;phase=3;lastEnemyDamage=POWER[opponent]+(turn%3==0?5:0);log=turn%3==0?SPECIALS[opponent]+" / wind-up":"Opponent winds up...";nextAt=now+enemyHitMs();}
  else if(phase==3){playerHp=Math.max(0,playerHp-lastEnemyDamage);log=(turn%3==0?SPECIALS[opponent]:"COUNTERATTACK")+" -"+lastEnemyDamage;phase=4;nextAt=eventAt+enemyDurationMs();}
  else if(phase==4){if(playerHp==0)finish(false);else{waiting=false;phase=0;}}
  remaining=Math.max(0,nextAt-now);return true;
 }
 private void finish(boolean win){finished=true;waiting=false;phase=0;log=win?"VICTORY! +25 XP":"KNOCKED OUT / Your pet is safe.";}
 public void pause(long now){if(waiting)remaining=Math.max(0,nextAt-now);eventAge=Math.max(0,now-eventAt);}
 public void resume(long now){if(waiting)nextAt=now+remaining;eventAt=now-eventAge;}
 public boolean claimReward(Pet pet){if(!finished||rewarded)return false;rewarded=true;if(enemyHp==0){pet.xp+=25;pet.joy=Pet.clip(pet.joy+20);}else pet.joy=Pet.clip(pet.joy-5);return true;}
 public boolean won(){return finished&&enemyHp==0;}
}
