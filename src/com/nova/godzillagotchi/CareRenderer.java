package com.nova.godzillagotchi;
public final class CareRenderer {
 public static void egg(SkinRenderer.Painter d,SkinRenderer.State s){float wobble=(float)Math.sin(s.now/160.0)*(1+s.pet.eggTaps);d.save();d.rotate(wobble,355,350);PaperArt.prop(d,s.pet.eggTaps>1?4:3,282,222,146,184);d.restore();d.text("TAP TO HATCH  "+s.pet.eggTaps+"/5",245,195,18,0xfff3ead1);}
 public static void overlay(SkinRenderer.Painter d,SkinRenderer.State s){Pet p=s.pet;
  for(int i=0;i<p.messes;i++)PaperArt.prop(d,6,MessLayout.x(i),377,48,48);
  if(p.hatched&&p.level()>=2){float x=355,y=417-260*p.sizeScale()-12;String form=p.form();if(form.equals("Heart crest")){PaperArt.prop(d,7,x-13,y-12,26,26);}else if(form.equals("Moon crest")){PaperArt.prop(d,8,x-13,y-12,26,26);}else if(form.equals("Explorer diamond")){d.save();d.rotate(45,x,y);d.rect(x-7,y-7,14,14,0xff7de5f0);d.restore();}else{d.rect(x-16,y,32,8,0xffffdc88);for(int i=0;i<3;i++)d.rect(x-16+i*13,y-9,6,12,0xffffdc88);}}
  if(p.lightsOff)d.rect(50,108,620,337,0x99051022);
  String need=p.need(s.night);d.round(68,114,584,29,9,0xe0082332);d.text(need,79,134,13,p.sick||p.food<30?0xffffc19b:0xffd6eee1);
  if(p.hatched&&!p.asleep&&p.expression(s.now)==3){for(int i=0;i<3;i++)PaperArt.prop(d,7,295+i*38,170+(float)Math.sin(s.now/160.0+i)*7,26,26);}

 }
 public static void buttons(SkinRenderer.Painter d,boolean asleep){int[] icons={0,7,8,5,11,9};String[] labels={"FEED","PLAY",asleep?"WAKE":"SLEEP","CLEAN","ROAR","CARE"};for(int i=0;i<6;i++){float[] r=SkinLayout.button(i);d.round(r[0],r[1],r[2],r[3],22,0xff48828a);d.round(r[0]+3,r[1]+3,r[2]-6,r[3]-6,20,0xff123f4a);if(i==4)PaperArt.pet(d,1,r[0]+12,r[1]+11,53,50);else PaperArt.prop(d,icons[i],r[0]+12,r[1]+11,53,50);d.text(labels[i],r[0]+77,r[1]+46,24,0xfff0e8cc);}}
}
