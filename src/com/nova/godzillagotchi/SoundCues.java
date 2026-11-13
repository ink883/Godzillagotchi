package com.nova.godzillagotchi;
/** One-shot care timing, independent of Android and theme. Uses the renderer's effect clock. */
public final class SoundCues {
 public static final int NONE=-1,BUBBLES=0,MUNCH=1,ENEMY_BASE=2,WILHELM=8;
 public static final String[] ASSETS={"bubbles","munch","roar_mechagodzilla","roar_kong","roar_ghidorah","roar_rodan","roar_gigan","roar_anguirus","wilhelm"};
 private String effect="";private long end=-1;private boolean played=true;
 public void start(String name,long until){effect=name;end=until;played=false;}
 public void cancel(){effect="";end=-1;played=true;}
 public boolean matches(String name,long until){return end==until&&effect.equals(name);}
 public int poll(String name,long until,long now,boolean allowed){if(!allowed||!matches(name,until)||now>=end){cancel();return NONE;}if(played)return NONE;
  long start=end-(effect.equals("fish")?2200:1800),due=start+(effect.equals("fish")?1500:0);
  if(now<due)return NONE;played=true;return effect.equals("fish")?MUNCH:effect.equals("bath")?BUBBLES:NONE;
 }
 public static boolean screamForRoll(int roll){return roll>=0&&roll<5;}
 public static int enemy(int opponent){return opponent>=0&&opponent<Battle.NAMES.length?ENEMY_BASE+opponent:NONE;}
 public static int enemyTransition(int before,Battle b){return before==2&&b.phase==3&&b.enemyEvent&&b.turn%3==0&&!b.finished?enemy(b.opponent):NONE;}
}
