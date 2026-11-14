package com.nova.godzillagotchi;
/** Layered paper towers, falling facade scraps and puppet vehicles, in the same logical 720-square coordinate space. */
public final class RampageRenderer {
 public static void draw(SkinRenderer.Painter d,SkinRenderer.State s){Rampage r=s.rampage;

  d.round(62,113,596,47,8,0xee082332);
  d.text(Landmarks.NAMES[r.landmark]+"  HP "+r.hp+"/150",75,132,14,0xfff0e8cc);
  d.text("HEALTH "+r.health+"   CHARGE "+r.charge,75,151,13,0xff69e4f1);
  if(!r.finished){float[] exit=SkinLayout.cityExitButton();d.round(exit[0],exit[1],exit[2],exit[3],10,0xff326b77);d.text("LEAVE CITY",exit[0]+17,exit[1]+28,18,0xfff0e8cc);}
  float age=r.time-r.hitAt,x=444+(age>=0&&age<300?(float)Math.sin(age/22)*4:0);
  boolean materialDamage=s.theme==Theme.VINYL||s.theme==Theme.LCD;
  if(materialDamage){BuildingDamage.draw(d,s);}
  else if(r.landmark>0){LandmarkRenderer.draw(d,s,x);}
  else if(r.building<Rampage.BUILDINGS){int stage=r.hp==Rampage.maxHp(r.building)?0:r.hp>Rampage.maxHp(r.building)/2?1:2;float[] source=PaperArt.SCENERY[stage];
   if(r.hp>0)PaperArt.draw(d,"paper_scenery",source,x,173,166,253);
   else {float t=Math.min(1.6f,Math.max(0,age/1000f));for(int row=0;row<5;row++)for(int col=0;col<3;col++){int i=row*3+col;float delay=(4-row)*.055f,fall=Math.max(0,t-delay),sx=x+col*166/3f+(float)Math.sin(fall*5+i)*fall*24,sy=173+row*253/5f+fall*fall*220;float w=166/3f,h=253/5f;d.save();d.rotate((i%2==0?1:-1)*fall*(65+i*7),sx+w/2,sy+h/2);d.image("paper_scenery",source[0]+source[2]*col/3,source[1]+source[3]*row/5,source[2]/3,source[3]/5,sx,sy,w,h);d.restore();}}
   d.round(x,433,166,7,2,0xff132d3e);d.round(x,433,166*r.hp/Rampage.maxHp(r.building),7,2,0xffffb571);
  }
  Battle proxy=new Battle(0);proxy.level=r.level;proxy.lastMove=r.move;proxy.lastDamage=r.damage;proxy.turn=r.move>=0?1:0;proxy.eventAt=r.eventAt;
  Battle original=s.battle;long originalNow=s.battleNow;s.battle=proxy;s.battleNow=r.time;BattleAnimation.draw(d,s);s.battle=original;s.battleNow=originalNow;
  if(!materialDamage&&age>=0&&age<850&&r.hp>0){float t=age/850f;for(int i=0;i<12;i++){float sx=485+(i%5-2)*t*60,sy=300+130*t*t-(float)Math.sin(t*Math.PI)*(i%3*15);d.save();d.rotate(i*37+t*200,sx,sy);d.rect(sx,sy,8+i%3*4,5+i%2*4,BattleAnimation.fade(i%2==0?0xffece0be:0xff6a9a9d,1-t));d.restore();}}
  for(Rampage.Vehicle v:r.vehicles)if(v!=null)vehicle(d,r,v,s.theme);
  if(r.time-r.shotAt<500){float t=(r.time-r.shotAt)/500f;d.circle(r.shotX+(210-r.shotX)*t,r.shotY+(345-r.shotY)*t,4,0xffffa764);if(t>.65f)BattleAnimation.burst(d,210,345,(t-.65f)*700,0xffff9869);}
  if(r.time-r.popAt<500)BattleAnimation.burst(d,r.popX,r.popY,r.time-r.popAt,0xffffdc98);
  d.round(62,409,328,26,7,0xe5082332);d.text("TAP TANKS / HELICOPTERS / JETS",73,427,12,0xfff0e8cc);
  if(r.finished){d.round(119,250,482,105,15,0xf0082435);d.text(r.won()?"BUILDING SMASHED!":"TIME TO RETREAT",178,289,26,0xfff0e8cc);d.text(r.won()?"+20 XP / +25 JOY":"Your pet is safe. Try again!",176,325,17,0xff69e4f1);}
 }
 static void vehicle(SkinRenderer.Painter d,Rampage r,Rampage.Vehicle v,int theme){float x=r.x(v),y=r.y(v),w=Rampage.width(v),h=Rampage.height(v);
  if(theme==Theme.PUPPET&&v.type==0){d.rect(x+w*.55f,y+h-4,6,445-y-h+4,0xff8d633e);d.rect(x+w*.55f+1,y+h-4,2,445-y-h+4,0xffd1ad70);}
  if(theme==Theme.PUPPET&&v.type==1){d.rect(x+w*.46f,153,1.5f,Math.max(0,y-153)+6,0xffeee5ce);d.circle(x+w*.46f,y+5,2,0xffae997c);}
  d.save();if(v.type==1)d.rotate((float)Math.sin(r.time/380.0)*5,x+w*.46f,y);PaperArt.fit(d,"paper_scenery",PaperArt.SCENERY[3+v.type],x,y,w,h);d.restore();
 }
 public static void buttons(SkinRenderer.Painter d,Rampage r){d.rect(0,527,720,193,0xff06182a);if(r.finished){d.round(20,575,680,84,25,0xff104555);d.text("BACK TO PET",249,628,28,0xfff0e8cc);return;}
  for(int i=0;i<4;i++){float[] q=SkinLayout.fightButton(i);boolean enabled=r.canAttack(i);d.round(q[0],q[1],q[2],q[3],24,enabled?0xff38869a:0xff294b5b);d.round(q[0]+3,q[1]+3,q[2]-6,q[3]-6,21,0xff104555);d.text(Battle.MOVES[i],q[0]+24,q[1]+33,25,enabled?0xfff0e8cc:0xff8098a2);d.text((i==3?"45 CHARGE":"RECHARGE")+" / "+Pet.damage(i,r.level)+" DAMAGE",q[0]+24,q[1]+57,12,0xffb2d6d0);}
 }
}
