package com.nova.godzillagotchi;
/** Pose changes and timed wind-up, contact, recoil and recovery rendered from battle time. */
public final class BattleAnimation {
 static final float[][] POSES=PaperArt.ATTACKS;
 static float clamp(float x){return Math.max(0,Math.min(1,x));}
 static float smooth(float x){x=clamp(x);return x*x*(3-2*x);}
 static float reach(float age,float hit,float end){return age<hit?smooth((age-120)/(hit-120)):1-smooth((age-hit-110)/(end-hit-110));}
 static int fade(int color,float alpha){return (Math.round(clamp(alpha)*255)<<24)|(color&0xffffff);}
 public static void draw(SkinRenderer.Painter d,SkinRenderer.State s){Battle b=s.battle;float age=s.battleNow-b.eventAt;int move=Math.max(0,b.lastMove);boolean player=b.turn>0&&!b.enemyEvent;float duration=Battle.DURATION_MS[move],hit=Battle.HIT_MS[move];boolean active=player&&age>=0&&age<duration;
  float push=active?reach(age,hit,duration):0,px=94,py=426,ex=400;
  if(active){float[] advance={110,140,105,8};px+=(advance[move]+(move<3?(1-s.pet.sizeScale())*222:0))*push;if(age<120)px-=10*(float)Math.sin(age/120*Math.PI);if(move==1)py-=28*push;}
  float afterHit=age-hit;
  if(player&&afterHit>=0&&afterHit<430)ex+=18*(float)Math.sin(Math.PI*afterHit/430);
  if(b.enemyEvent&&age>=b.enemyHitMs()&&age<b.enemyDurationMs()){px-=14*(float)Math.sin((age-b.enemyHitMs())/(b.enemyDurationMs()-b.enemyHitMs())*Math.PI);}
  d.save();d.clipRound(50,167,620,278,0);
  // Ground shadows keep lunges and jumps anchored in the arena.
  d.round(114,420,194,7,4,0x55031221);d.round(424,420,211,7,4,0x55031221);
  if(active&&move==2&&age>180&&age<1000){float sweep=smooth((age-180)/600);for(int trail=0;trail<3;trail++){float a=fadeAlpha(age,180,1000)*(.45f-trail*.10f);for(int j=0;j<30;j++){float t=j/29f;float x=200+300*t;float y=390-125*(float)Math.sin(t*Math.PI)*(1-sweep)+trail*10;d.rect(x,y,9,4,fade(0xff83ecdf,a));}}}
  if(active&&move<2&&push>.15f){for(int i=0;i<4;i++)d.rect(px-24-i*13,285+i*25,24+i*9,3,0x879ce3da);}
  boolean pose=active&&age>=120&&age<duration-120;float ph=222*s.pet.sizeScale(),pw=ph;
  if(pose){float[] src=POSES[move];pw=ph*src[2]/src[3];d.image("paper_attacks",src[0],src[1],src[2],src[3],px,py-ph,pw,ph);}
  else PaperArt.pet(d,-1,px,py-ph,pw,ph);
  if(s.rampage==null)EnemyAnimation.draw(d,s,ex);
  if(active&&move==3){float mx=px+pw*.95f,my=py-ph*.60f;
   if(age<900){float charge=clamp(age/900);d.circle(mx,my,9+charge*16,0x5554eaff);d.circle(mx,my,4+charge*8,0xffa2f9ff);for(int i=0;i<9;i++){float a=(float)(i*2*Math.PI/9+age/180);float r=38*(1-charge)+12;d.rect(mx+(float)Math.cos(a)*r,my+(float)Math.sin(a)*r,4,4,0xffb4ffff);}for(int i=0;i<6;i++)d.circle(px+48+i*8,py-35-i*26,3+charge*4,0x9941ddef);}
   if(age>=650){float length=(560-mx)*clamp((age-650)/250)*(1-smooth((age-1750)/450));if(length>1)PaperArt.draw(d,"paper_props",PaperArt.PROPS[11],mx,my-22,length,44);if(age>=900&&age<1900)burst(d,530,my,(age-900)%300,0xff75eeff);}

  }
  float impactY=move==0?310:move==1?285:move==2?365:293;
  if(player&&afterHit>=0&&afterHit<500){burst(d,478,impactY,afterHit,move==3?0xff7befff:0xffffdc98);d.text("-"+b.lastDamage,493,207-afterHit*.045f,24,0xffffd49a);}
  if(b.enemyEvent&&age>=b.enemyHitMs()&&age<b.enemyHitMs()+500){float since=age-b.enemyHitMs();burst(d,288,b.opponent==5?382:315,since,0xffffb06b);d.text("-"+b.lastEnemyDamage,166,205-since*.04f,24,0xffffad82);}

  d.restore();
 }
 static float fadeAlpha(float age,float start,float end){return clamp((age-start)/100)*clamp((end-age)/180);}
 static void burst(SkinRenderer.Painter d,float x,float y,float age,int color){float t=clamp(age/500),a=1-t;for(int i=0;i<12;i++){double angle=i*Math.PI/6;float radius=7+t*40;float size=(i%2==0?9:5)*(1-t*.6f);float bx=x+(float)Math.cos(angle)*radius,by=y+(float)Math.sin(angle)*radius;d.save();d.rotate(i*39+t*160,bx,by);d.rect(bx-1,by-1,size+2,size+2,fade(0xfff5ebd1,a));d.rect(bx,by,size,size,fade(color,a));d.restore();}if(age<90)d.circle(x,y,14,0xbffff2cb);}
}
