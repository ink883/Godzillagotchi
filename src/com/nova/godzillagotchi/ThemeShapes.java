package com.nova.godzillagotchi;
/** Original geometric care art and crisp fixed-segment LCD scenery. */
public final class ThemeShapes {
 public static void background(SkinRenderer.Painter d,boolean night,float x,float y,float w,float h){int bg=night?0xffa8b58e:ThemePainter.LCD_BG;d.rect(x,y,w,h,bg);
  int ghost=0xff9ca786;for(int i=0;i<13;i++){float bx=x+10+i*47,bh=25+i%4*12;d.rect(bx,y+h-23-bh,30,bh,ghost);for(int k=0;k<3;k++)for(int row=0;row<3;row++)d.rect(bx+4+k*8,y+h-18-bh+row*12,4,6,bg);}
  d.rect(x+8,y+h-20,w-16,3,ThemePainter.LCD_INK);d.circle(x+w-45,y+42,15,ThemePainter.LCD_INK);if(night)d.circle(x+w-38,y+36,13,bg);
 }
 public static void scenery(SkinRenderer.Painter d,boolean lcd,int i,boolean night,float x,float y,float w,float h){int ink=lcd?ThemePainter.LCD_INK:0xff49748a,light=lcd?ThemePainter.LCD_BG:(night?0xffffd488:0xffa3dfeb),dark=lcd?ThemePainter.LCD_BG:0xff142c32;
  if(i<3){d.rect(x,y,w,h,ink);d.rect(x+w*.38f,y-5,w*.24f,5,ink);if(!lcd)d.rect(x+w*.84f,y,w*.16f,h,0xff29485f);for(int row=0;row<8;row++)for(int col=0;col<5;col++){if(i>0&&(row+col)%((i==1)?5:3)==0)continue;d.rect(x+w*(.065f+col*.15f),y+h*(.07f+row*.09f),w*.078f,h*.045f,light);}if(i>0){float[] crack={x+w*.58f,y+h*.1f,x+w*.47f,y+h*.37f,x+w*.62f,y+h*.44f,x+w*.35f,y+h*.77f,x+w*.48f,y+h*.45f,x+w*.36f,y+h*.36f};d.polygon(crack,dark);}return;}
  if(i>=3){d.round(x-3,y-3,w+6,h+6,4,lcd?ThemePainter.LCD_BG:0xdd102c37);if(!lcd)ink=0xff809b73;}
  if(i==3){d.round(x,y+h*.6f,w,h*.4f,4,ink);for(int j=0;j<5;j++)d.circle(x+w*(.12f+j*.19f),y+h*.8f,h*.1f,light);d.rect(x+w*.1f,y+h*.4f,w*.8f,h*.3f,ink);d.rect(x+w*.4f,y+h*.1f,w*.35f,h*.35f,ink);d.rect(x,y+h*.2f,w*.5f,h*.13f,ink);}
  if(i==4){d.round(x,y+h*.25f,w*.62f,h*.5f,6,ink);d.rect(x+w*.6f,y+h*.4f,w*.35f,h*.12f,ink);d.rect(x+w*.9f,y+h*.2f,w*.08f,h*.45f,ink);d.rect(x+w*.28f,y+h*.08f,w*.04f,h*.23f,ink);d.rect(x+w*.04f,y,w*.75f,h*.06f,ink);d.rect(x+w*.08f,y+h*.32f,w*.19f,h*.2f,light);d.rect(x+w*.08f,y+h*.9f,w*.55f,h*.06f,ink);d.rect(x+w*.2f,y+h*.72f,w*.04f,h*.2f,ink);d.rect(x+w*.48f,y+h*.72f,w*.04f,h*.2f,ink);}
  if(i==5){d.polygon(new float[]{x,y+h*.5f,x+w*.3f,y+h*.37f,x+w*.55f,y,x+w*.65f,y+h*.35f,x+w*.87f,y+h*.35f,x+w,y+h*.1f,x+w,y+h*.9f,x+w*.87f,y+h*.65f,x+w*.65f,y+h*.65f,x+w*.55f,y+h,x+w*.3f,y+h*.63f},ink);d.rect(x+w*.22f,y+h*.44f,w*.2f,h*.12f,light);}
 }
 public static void prop(SkinRenderer.Painter d,int i,float x,float y,float w,float h){int green=0xff84b952,cream=0xffb9dfa4;
  if(i==3||i==4){d.round(x,y,w,h,Math.min(w,h)*.45f,cream);for(int k=0;k<4;k++)d.rect(x+w*(.2f+k%2*.4f),y+h*(.17f+k*.17f),w*.15f,h*.1f,green);if(i==4)d.polygon(new float[]{x+w*.55f,y+h*.2f,x+w*.43f,y+h*.43f,x+w*.62f,y+h*.5f,x+w*.4f,y+h*.79f,x+w*.49f,y+h*.5f,x+w*.32f,y+h*.44f},0xff163f43);}
  else if(i==5){d.circle(x+w/2,y+h/2,Math.min(w,h)*.45f,0xbb9cedf3);d.circle(x+w*.35f,y+h*.3f,Math.min(w,h)*.13f,0xffe2ffff);}
  else if(i==6){d.round(x,y+h*.7f,w,h*.3f,5,0xff629a47);d.round(x+w*.17f,y+h*.4f,w*.66f,h*.35f,5,green);d.round(x+w*.38f,y+h*.12f,w*.32f,h*.35f,5,cream);}
  else if(i==7){d.circle(x+w*.3f,y+h*.3f,w*.28f,0xffffadbe);d.circle(x+w*.7f,y+h*.3f,w*.28f,0xffffadbe);d.polygon(new float[]{x+w*.04f,y+h*.4f,x+w*.96f,y+h*.4f,x+w*.5f,y+h},0xffffadbe);}
  else if(i==8){d.circle(x+w/2,y+h/2,Math.min(w,h)*.45f,0xffffde8b);d.circle(x+w*.68f,y+h*.32f,Math.min(w,h)*.36f,0xff123f4a);}
  else if(i==9){d.rect(x,y,w,h,0xffe8dbac);d.rect(x+w*.49f,y,w*.03f,h,0xff817f69);for(int k=0;k<3;k++){d.rect(x+w*.1f,y+h*(.25f+k*.2f),w*.3f,h*.04f,0xff817f69);d.rect(x+w*.6f,y+h*(.25f+k*.2f),w*.3f,h*.04f,0xff817f69);}}
  else if(i==10){d.round(x+w*.15f,y+h*.2f,w*.7f,h*.8f,4,0xfff0e8cc);d.rect(x+w*.2f,y,w*.6f,h*.2f,0xff478c70);d.rect(x+w*.42f,y+h*.45f,w*.16f,h*.4f,0xff478c70);d.rect(x+w*.25f,y+h*.57f,w*.5f,h*.15f,0xff478c70);}
  else if(i==11){d.rect(x,y+h*.2f,w,h*.6f,0xff34d3ee);d.rect(x,y+h*.4f,w,h*.2f,0xffe9ffff);}
 }
}
