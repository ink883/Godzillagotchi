package com.nova.godzillagotchi;
/** Actual app drawing, shared with the desktop renderer for visual verification. */
public final class SkinRenderer {
 public interface Painter {
  void image(String name,float sx,float sy,float sw,float sh,float x,float y,float w,float h);
  void polygon(float[] points,int color);
  void rect(float x,float y,float w,float h,int color);
  void round(float x,float y,float w,float h,float radius,int color);
  void text(String text,float x,float baseline,float size,int color);
  void circle(float x,float y,float r,int color);
  void save();void restore();void rotate(float degrees,float cx,float cy);void clipRound(float x,float y,float w,float h,float radius);
 }
 public static class State {public int theme=Theme.PUPPET;public int fishType;public Pet pet;public Rampage rampage;public City city;public Battle battle;public long battleNow;public boolean night,playing,showCity;public long now,gameEnd,effectEnd;public String effect="",message="";}
 static final float[][] FISH=PaperArt.PROPS;
 final int INK=0xfff0e8cc,CYAN=0xff69e4f1;
 public void draw(Painter d,State s){
  d=new ThemePainter(d,s.theme,s.night);
  d.image("shell",0,0,1,1,0,0,720,720);
  d.save();d.clipRound(50,80,620,417,28);
  d.rect(50,80,620,417,0xff081d2b);
  d.image("paper_background",0,s.night?.5f:0,1,.5f,50,108,620,337);
  if(s.rampage!=null){RampageRenderer.draw(d,s);}else if(s.battle!=null){drawBattle(d,s);}else if(s.showCity){d.rect(50,108,620,337,s.night?0xa5081729:0x73405f79);drawCity(d,s);}else{
   float growth=s.pet.sizeScale();
   float size=260*growth;float bob=s.pet.asleep?0:(float)Math.sin(s.now/320.0)*2;
   if(!s.pet.hatched){CareRenderer.egg(d,s);}
   else if(s.pet.asleep){float breathing=(float)Math.sin(s.now/850.0)*1.5f;PaperArt.pet(d,0,355-size/2,417-size-breathing,size,size+breathing);}
   else if(s.effect.equals("roar")&&s.now<s.effectEnd){float age=5000-(s.effectEnd-s.now);float lift=BattleAnimation.smooth(age/350)*BattleAnimation.smooth((5000-age)/350);d.save();d.rotate(-3*lift*(float)Math.sin(age/130),355,410);PaperArt.pet(d,1,355-size/2,417-size-5*lift,size,size+5*lift);d.restore();}
   else if(s.pet.expression(s.now)>=0){int pose=s.pet.expression(s.now);PaperArt.pet(d,pose+2,355-size/2,417-size+bob,size,size);}
   else PaperArt.pet(d,-1,355-size/2,417-size+bob,size,size);
   if(s.pet.asleep){d.round(413,164,95,34,12,0xaa102a39);d.text("z Z Z",425,188,20,INK);}
   if(!s.pet.asleep&&s.now<s.effectEnd){
    if(s.effect.equals("roar")){d.text("SKREEONK!",426,160,18,INK);}
    if(s.effect.equals("fish")){
     float age=2200-(s.effectEnd-s.now),travel=BattleAnimation.smooth((age-250)/1250f),eat=BattleAnimation.smooth((age-1500)/450f);
     float mouthX=355+size*.30f,mouthY=417-size*.63f+bob;
     int fish=Math.max(0,Math.min(2,s.fishType));float[] crop=FISH[fish];
     float fw=new float[]{110,104,90}[fish]*(1-eat),fh=fw*crop[3]/crop[2],fx=550+(mouthX-550)*travel,fy=mouthY-fh/2-18*(float)Math.sin(travel*Math.PI);
     if(fw>1){d.save();d.rotate(-6*(float)Math.sin(age/140),fx+fw/2,fy+fh/2);d.image("paper_props",crop[0],crop[1],crop[2],crop[3],fx,fy,fw,fh);d.restore();}
     if(age>1700){float rise=(age-1700)/35;for(int i=0;i<3;i++){float hx=mouthX+12+i*15,hy=mouthY-14-rise-i%2*9;d.rect(hx,hy,4,4,0xffffd890);d.rect(hx-3,hy+3,10,3,0xffffd890);d.rect(hx,hy+6,4,3,0xffffd890);}}
    }
    if(s.effect.equals("medicine"))PaperArt.prop(d,10,452,275,60,85);
    if(s.effect.equals("bath")){for(int i=0;i<14;i++){float bx=210+(i*37)%280,by=410-((s.now/12+i*43)%230),bs=18+i%4*6;d.save();d.rotate((float)Math.sin(s.now/400.0+i)*18,bx+bs/2,by+bs/2);PaperArt.prop(d,5,bx,by,bs,bs);d.restore();}}
   }
  }
  if(s.battle==null&&s.rampage==null&&!s.showCity)CareRenderer.overlay(d,s);
  // HUD and meters are rendered from live game state, never baked-in values.
  d.rect(50,80,620,28,0xff081d2b);d.rect(50,105,620,2,0xff245568);
  String stage="LV "+(s.pet.level()+1)+" "+s.pet.stage();
  d.text(s.battle!=null?"KAIJU BATTLE":stage,76,99,14,0xffdceef0);d.text(s.pet.xp+" XP",367,99,14,0xffdceef0);d.text(s.night?"NIGHT":"DAY",586,99,14,0xffdceef0);
  if(s.battle!=null||s.rampage!=null){}else if(s.playing){d.round(145,113,430,25,8,0xec082332);d.text("STOMP "+s.city.count+"/10   "+Math.max(0,(s.gameEnd-s.now+999)/1000)+"s  / TAP BUILDINGS",160,131,13,INK);}
  else if(s.showCity){d.round(217,113,286,25,8,0xec082332);d.text("CITY STOMPED! +12 XP",237,131,16,INK);}
  else if(s.now<s.effectEnd){d.round(76,416,568,25,8,0xdd092330);d.text(s.message,90,433,13,INK);}
  if(s.rampage!=null){Rampage r=s.rampage;d.rect(50,445,620,52,0xff082330);d.text(r.log,67,466,13,INK);d.text("LV "+(s.pet.level()+1)+" / "+r.kills+" THREATS STOPPED   "+(r.finished?"BACK TO PET":r.phase>0?"ATTACK IN PROGRESS":"CHOOSE AN ATTACK"),67,486,12,CYAN);}
  else if(s.battle==null){
  d.rect(50,445,620,52,0xff082330);d.rect(50,445,620,2,0xff2b7181);
  String[] labels={"FOOD","JOY","ENERGY","CLEAN"};int[] colors={0xff68e980,0xffffd87c,0xff40d5ed,0xffa492f2};double[] v={s.pet.food,s.pet.joy,s.pet.energy,s.pet.clean};
  for(int i=0;i<4;i++){float x=50+i*155; if(i>0)d.rect(x,447,1,49,0xff2b5663);
   PaperArt.prop(d,new int[]{0,7,11,5}[i],x+5,451,32,34);
   d.text(labels[i],x+44,464,12,0xffdceef0);d.round(x+44,473,76,12,3,0xff244554);d.round(x+44,473,(float)(76*v[i]/100),12,3,colors[i]);d.text(""+(int)v[i],x+125,484,12,colors[i]);
  }
  }
  d.restore();
  if(s.rampage!=null)RampageRenderer.buttons(d,s.rampage);
  else if(s.battle!=null)battleButtons(d,s);
  else CareRenderer.buttons(d,s.pet.asleep);
 }
 static final float[][] SPRITES=PaperArt.OPPONENTS;
 void drawBattle(Painter d,State s){Battle b=s.battle;d.rect(50,108,620,337,0x66051b2b);
  d.round(62,113,283,52,9,0xeb082330);d.round(375,113,283,52,9,0xeb082330);
  d.text("GODZILLA  "+b.playerHp+"/100",74,132,14,INK);d.text(Battle.NAMES[b.opponent],387,132,14,INK);
  d.round(74,143,255,10,3,0xff213e4e);d.round(74,143,255*b.playerHp/100f,10,3,0xff6de58c);
  d.round(387,143,255,10,3,0xff213e4e);d.round(387,143,255*b.enemyHp/(float)Battle.MAX_HP[b.opponent],10,3,0xffffa17a);
  BattleAnimation.draw(d,s);
  d.rect(50,445,620,52,0xff082330);d.rect(50,445,620,2,0xff2b7181);
  d.text("CHARGE "+b.charge+"/100",67,466,13,CYAN);d.round(67,477,150,8,3,0xff254c5d);d.round(67,477,b.charge*1.5f,8,3,CYAN);
  d.text(b.log,237,466,13,INK);d.text(b.finished?"Tap BACK TO PET":b.waiting?(b.enemyEvent?"Opponent attack / recovering...":"Your attack / recovering..."):"Choose PUNCH / KICK / TAIL / BREATH",237,486,12,0xff9ac6cc);
  if(b.finished){d.round(149,225,422,102,16,0xf0082435);d.text(b.won()?"VICTORY!":"KNOCKED OUT",223,268,30,b.won()?0xff9cf3a5:0xffffbf90);d.text(b.won()?"+25 GROWTH XP / +20 JOY":"Your pet is safe. Try again!",211,302,16,INK);}
 }
 void battleButtons(Painter d,State s){Battle b=s.battle;d.rect(0,527,720,193,0xff06182a);
  if(b.finished){d.round(20,575,680,84,25,0xff28758a);d.round(23,578,674,78,23,0xff0c3b49);d.text("BACK TO PET",249,628,28,INK);return;}
  for(int i=0;i<4;i++){float[] r=SkinLayout.fightButton(i);boolean enabled=b.canAttack(i);d.round(r[0],r[1],r[2],r[3],24,enabled?0xff38869a:0xff294b5b);d.round(r[0]+3,r[1]+3,r[2]-6,r[3]-6,21,enabled?0xff104555:0xff112c3c);d.text(Battle.MOVES[i],r[0]+24,r[1]+33,25,enabled?(i==3?CYAN:INK):0xff8098a2);
   String hint=(i==3?"45 CHARGE":i==0?"+18 CHARGE":i==1?"+12 CHARGE":"+8 CHARGE")+" / "+b.damage(i)+" DAMAGE";
   d.text(hint,r[0]+24,r[1]+57,12,enabled?0xffc0d6d0:0xff6e8998);
  }
 }
 void drawCity(Painter d,State s){
  float ax=SkinLayout.SCENE_X,ay=SkinLayout.SCENE_Y,sx=SkinLayout.SCENE_W/360,sy=SkinLayout.SCENE_H/250;
  for(int i=0;i<10;i++){float x=City.left(i),base=City.bottom(i),top=City.top(i);float age=(s.now-s.city.hitAt[i])/1000f;boolean broken=s.city.destroyed[i];float y=broken?top+(base-top-8)*Math.min(1,Math.max(0,age/.25f)):top;
   // Outline and recessed windows make the ten interactive foreground targets distinct.
   d.rect(ax+(x-2)*sx,ay+(y-2)*sy,58*sx,(base-y+4)*sy,0xff061b30);
   d.rect(ax+x*sx,ay+y*sy,54*sx,(base-y)*sy,s.night?0xff21445e:0xff507c97);
   d.rect(ax+(x+2)*sx,ay+y*sy,50*sx,3*sy,s.night?0xff4e89a6:0xffa7c9cf);
   d.rect(ax+(x+43)*sx,ay+(y+3)*sy,9*sx,Math.max(0,base-y-3)*sy,0x553a99b4);
   for(float k=y+10;k<base-6;k+=13)for(int j=0;j<3;j++)d.rect(ax+(x+7+j*15)*sx,ay+k*sy,7*sx,6*sy,s.night?0xffffd27a:0xffa3d7e5);
   if(!broken)d.text(""+(i+1),ax+(x+23)*sx,ay+(base-3)*sy,12,0xffe4eddf);
   if(broken&&age<.7f){for(int j=0;j<8;j++){float dx=(j-3.5f)*age*55/4,dy=(float)(-Math.sin(Math.min(1,age/.7f)*Math.PI)*22);d.rect(ax+(x+27+dx)*sx,ay+(base-6+dy+(j%3)*4)*sy,5,5,0xffd2b993);}}
  }
 }
}
