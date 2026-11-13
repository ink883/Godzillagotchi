package com.nova.godzillagotchi;
/** Code-drawn can follows each theme and needs no extra bitmap allocation. */
public final class RushArt {
 public static void can(SkinRenderer.Painter d,int theme,float x,float y,float w,float h){
  boolean lcd=theme==Theme.LCD;int edge=lcd?0xffefffff:0xfff4e5bd,body=lcd?0xff172324:0xff169a8b,ink=lcd?0xffefffff:0xffeaff93;
  float radius=theme==Theme.CLASSIC||lcd?0:w*.13f;
  d.round(x-2,y-2,w+4,h+4,radius+2,edge);d.round(x,y,w,h,radius,body);
  if(theme==Theme.VINYL)d.round(x+w*.08f,y+h*.1f,w*.12f,h*.78f,w*.05f,0xff65c8ad);
  if(theme==Theme.PUPPET)for(int i=0;i<6;i++)d.rect(x+w*.1f,y+h*(.18f+i*.12f),w*.8f,1,0xff53b7a3);
  d.round(x,y,w,h*.09f,radius,0xffdce8cf);d.round(x,y+h*.91f,w,h*.09f,radius,0xffdce8cf);d.round(x+w*.4f,y+h*.02f,w*.22f,h*.04f,2,0xff36565a);
  d.polygon(new float[]{x+w*.57f,y+h*.30f,x+w*.25f,y+h*.58f,x+w*.48f,y+h*.58f,x+w*.39f,y+h*.76f,x+w*.76f,y+h*.47f,x+w*.53f,y+h*.47f},ink);
  if(w>=40){d.text("KAIJU",x+w*.09f,y+h*.25f,w*.23f,ink);d.text("RUSH",x+w*.14f,y+h*.86f,w*.23f,ink);}
 }
 public static void drink(SkinRenderer.Painter d,SkinRenderer.State s,float size,float bob){
  float age=SoundCues.DRINK_MS-(s.effectEnd-s.now),travel=BattleAnimation.smooth(age/800),away=BattleAnimation.smooth((age-2400)/300);
  float mouthX=355+size*.30f,mouthY=417-size*.63f+bob,x=555+(mouthX-555)*travel+away*100,y=340+(mouthY-340)*travel+away*65;
  d.save();d.rotate(-65*travel*(1-away),x,y);can(d,s.theme,x,y,48,78);d.restore();
  if(age>=800&&age<2400)for(int i=0;i<3;i++){float t=((age-800+i*160)%500)/500;d.circle(mouthX+8+i*5,mouthY-12-t*25,2+t,0xffb9f3dc);}
 }
}
