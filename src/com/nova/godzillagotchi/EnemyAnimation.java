package com.nova.godzillagotchi;
/** Opponent-specific pose, locomotion and signature effects. */
public final class EnemyAnimation {
 static final float[][] POSES=PaperArt.ENEMY_ATTACKS;
 static float smooth(float x){return BattleAnimation.smooth(x);}
 static float clamp(float x){return BattleAnimation.clamp(x);}
 static int fade(int c,float a){return BattleAnimation.fade(c,a);}
 public static void draw(SkinRenderer.Painter d,SkinRenderer.State s,float baseX){Battle b=s.battle;float age=s.battleNow-b.eventAt,hit=b.enemyHitMs(),end=b.enemyDurationMs();boolean active=b.enemyEvent&&age>=0&&age<end;boolean posed=active&&age>=100&&age<end-100;boolean special=b.turn%3==0;int who=b.opponent;
  float push=active?BattleAnimation.reach(age,hit,end):0,baseY=426,x=baseX;
  if(active){float[] advance={special?12:125,special?0:125,special?12:75,155,140,170};x-=advance[who]*push;if(who==1&&!special)baseY-=42*(float)Math.sin(push*Math.PI*.75);if(who==2&&special)baseY-=9*(float)Math.sin(age/110);if(who==3)baseY-=44*(float)Math.sin(push*Math.PI*.75);}
  Theme.Sprite sprite=Theme.sprite(s.theme,posed?"paper_enemy_attacks":"paper_opponents",who);float[] src=sprite.bounds;float h=new float[]{220,200,220,190,210,145}[who];
  // Raised arms/wings extend the silhouette without shrinking the body below them.
  if(posed&&who==1)h*=new float[]{1.20f,1.22f,1.25f,1.25f}[s.theme];
  if(posed&&who==3)h*=1.12f;
  float w=h*src[2]/src[3];
  float left=x+(245-w)/2,top=baseY-h;
  if(active&&who==3){for(int i=0;i<5;i++)d.rect(left+w-15+i*8,top+30+i*17,28+i*10,3,fade(0xfff5bca0,.45f*push));}
  if(active&&who==5){for(int i=0;i<7;i++)d.rect(left+w+i*12,415-i%3*7,7,5,fade(0xffdbc08f,push*.7f));}
  d.save();if(posed&&who==1&&!special)d.rotate(-18*push,left+w/2,baseY);if(posed&&who==4)d.rotate(-8*push,left+w/2,baseY);if(posed&&who==5)d.rotate(-720*smooth(age/end),left+w/2,top+h/2);
  d.image(sprite.name,src[0],src[1],src[2],src[3],left,top,w,h);d.restore();
  if(!active)return;
  float after=age-hit;float contact=clamp((age-(hit-180))/180),alpha=1-smooth((age-(end-250))/250);
  if(who==0&&special){float mx=left+w*.15f,my=top+h*.36f;charge(d,mx,my,age,hit,0xffff5969);if(age>hit-180)beam(d,mx,my,286,310,contact,alpha,age,0xffff5973);}
  if(who==1&&special)BarrelAnimation.draw(d,age,hit,x+100,baseY-220);
  if(who==1&&!special){if(age>hit-170&&age<hit+240){for(int i=0;i<3;i++)d.rect(left+40+i*28,top+20+i*8,5,43,fade(0xffeac999,.65f));}if(after>=0&&after<500){for(int i=0;i<18;i++){float t=after/500,dist=8+t*100;d.rect(280+(i-9)*dist/8,423-(float)Math.sin(t*Math.PI)*((i%4)*7+8),6,6,fade(0xffd8b68c,1-t));}d.rect(190,423,190,3,fade(0xffeacc97,1-after/500));}}
  if(who==2&&special){for(int head=0;head<3;head++){float mx=left+w*(head==0?.15f:head==1?.02f:.4f),my=top+h*(head==0?.18f:head==1?.43f:.34f);charge(d,mx,my,age+head*25,hit,0xffffd657);if(age>hit-180)beam(d,mx,my,270,280+head*27,contact,alpha,age+head*55,0xffffd657);}}
  if(who==3&&after>-180&&after<440){float t=clamp((after+180)/620);for(int trail=0;trail<3;trail++)for(int j=0;j<25;j++){float u=j/24f;d.rect(245+u*165,285+trail*13+50*(float)Math.sin(u*Math.PI+t*2),7,3,fade(0xffffbf94,(1-t)*.75f));}}
  if(who==4&&after>-170&&after<430){float t=clamp((after+170)/600);for(int slash=0;slash<2;slash++)for(int i=0;i<24;i++){float a=(float)(-.9+i/23.0*2.6),r=45+slash*15;d.rect(300+(float)Math.cos(a)*r,310+(float)Math.sin(a)*r,6,5,fade(special?0xffff8b8b:0xffd1eeef,1-t));}}
  if(who==5&&after>=0&&after<450){BattleAnimation.burst(d,284,388,after,0xffe4d1a4);for(int i=0;i<5;i++)d.rect(220+i*27,420,12,3,fade(0xffd3c698,1-after/450));}
 }
 static void charge(SkinRenderer.Painter d,float x,float y,float age,float hit,int color){if(age>hit)return;float t=clamp(age/hit);d.circle(x,y,6+13*t,fade(color,.45f));d.circle(x,y,3+5*t,0xfffff4c0);for(int i=0;i<6;i++){float a=(float)(i*Math.PI/3+age/95);d.rect(x+(float)Math.cos(a)*(27-15*t),y+(float)Math.sin(a)*(27-15*t),3,3,color);}}
 static void beam(SkinRenderer.Painter d,float x,float y,float tx,float ty,float length,float alpha,float age,int color){float dx=(tx-x)*length,dy=(ty-y)*length,len=(float)Math.sqrt(dx*dx+dy*dy);if(len<1)return;d.save();d.rotate((float)Math.toDegrees(Math.atan2(dy,dx)),x,y);float[] edge=new float[48],core=new float[48];for(int i=0;i<12;i++){float u=i/11f,xx=x+len*u,jag=(i%2==0?3:-2)+(float)Math.sin(age/90+i)*1.5f;edge[i*2]=xx;edge[i*2+1]=y-7-jag;edge[(23-i)*2]=xx;edge[(23-i)*2+1]=y+7+jag;core[i*2]=xx;core[i*2+1]=y-3-jag*.4f;core[(23-i)*2]=xx;core[(23-i)*2+1]=y+3+jag*.4f;}d.polygon(edge,fade(0xfff5e6bf,alpha));d.polygon(core,fade(color,alpha));d.restore();}
}
