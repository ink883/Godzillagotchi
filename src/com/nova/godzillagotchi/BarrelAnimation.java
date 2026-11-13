package com.nova.godzillagotchi;
/** An overhead wind-up, spinning arc and wooden impact fragments. */
public final class BarrelAnimation {
 public static void draw(SkinRenderer.Painter d,float age,float hit,float fromX,float fromY){
  float launch=hit-390,t=BattleAnimation.clamp((age-launch)/(hit-launch));
  float x=fromX+(274-fromX)*t,y=fromY+(330-fromY)*t-95*(float)Math.sin(t*Math.PI);
  if(age<hit){d.save();d.rotate(t*450,x,y);d.round(x-36,y-45,72,90,16,0xffefdbad);d.round(x-32,y-41,64,82,12,0xffab713f);for(int i=-2;i<=2;i++)d.rect(x+i*11-1,y-36,2,72,0xff603e2b);d.round(x-35,y-29,70,10,3,0xffc6c6af);d.round(x-35,y+19,70,10,3,0xffc6c6af);d.restore();}
  else if(age<hit+450){float u=(age-hit)/450;BattleAnimation.burst(d,274,330,age-hit,0xffdeb171);for(int i=0;i<12;i++){float a=(float)(i*Math.PI/6),dx=(float)Math.cos(a),dy=(float)Math.sin(a);d.save();float px=274+dx*u*95,py=330+dy*u*65+u*u*65;d.rotate(i*27+u*160,px,py);d.rect(px,py,7,22,BattleAnimation.fade(0xffbd8d57,1-u));d.restore();}}
 }
}
