package com.nova.godzillagotchi;
/** Per-frame asset routing; no global mutable theme and no changes to simulation. */
public final class ThemePainter implements SkinRenderer.Painter {
 final SkinRenderer.Painter d;final int theme;final boolean night;
 public static final int LCD_BG=0xffb8c19a,LCD_INK=0xff20271c;
 public ThemePainter(SkinRenderer.Painter target,int id,boolean dark){d=target;theme=Theme.valid(id);night=dark;}
 public void image(String n,float sx,float sy,float sw,float sh,float x,float y,float w,float h){
  if(n.equals("shell")){d.image(n,sx,sy,sw,sh,x,y,w,h);return;}
  if(n.equals("paper_background")){if(theme==Theme.CLASSIC)d.image("city_atlas",.04f,night?0:.5f,.92f,.5f,x,y,w,h);else if(theme==Theme.VINYL)d.image("vinyl_background",0,night?.5f:0,1,.5f,x,y,w,h);else if(theme==Theme.LCD)ThemeShapes.background(d,night,x,y,w,h);else d.image(n,sx,sy,sw,sh,x,y,w,h);return;}
  float[][] rows=Theme.source(n);if(rows==null||theme==Theme.PUPPET){d.image(n,sx,sy,sw,sh,x,y,w,h);return;}
  int i=Theme.index(rows,sx,sy,sw,sh);float[] old=rows[i];Theme.Sprite sprite=Theme.sprite(theme,n,i);
  boolean full=Math.abs(sw-old[2])<.002&&Math.abs(sh-old[3])<.002;
  if(sprite==null){if(n.equals("paper_props"))ThemeShapes.prop(d,i,x,y,w,h);else if(n.equals("paper_scenery")){if(full)ThemeShapes.scenery(d,theme==Theme.LCD,i,night,x,y,w,h);else {int color=theme==Theme.LCD?LCD_INK:0xff49748a;d.rect(x,y,w,h,color);d.rect(x+w*.2f,y+h*.2f,w*.35f,h*.25f,theme==Theme.LCD?LCD_BG:0xffa3dfeb);}}return;}
  float[] q=sprite.bounds;
  // Whole poses preserve natural proportions; facade fragments preserve their source subregion.
  if(full&&!(n.equals("paper_props")&&i==11)){float dw=w,dh=w*q[3]/q[2];if(dh>h){dh=h;dw=h*q[2]/q[3];}d.image(sprite.name,q[0],q[1],q[2],q[3],x+(w-dw)/2,y+h-dh,dw,dh);}
  else d.image(sprite.name,q[0]+(sx-old[0])/old[2]*q[2],q[1]+(sy-old[1])/old[3]*q[3],sw/old[2]*q[2],sh/old[3]*q[3],x,y,w,h);
 }
 int color(int c){if(theme!=Theme.LCD)return c;int a=c>>>24,r=(c>>16)&255,g=(c>>8)&255,b=c&255,lum=(r*3+g*6+b)/10;if(a<240&&lum<90)return (Math.min(a,22)<<24)|(LCD_INK&0xffffff);int rgb=lum<90?LCD_BG:lum<150?0xff899575:LCD_INK;return (a<<24)|(rgb&0xffffff);}
 public void rect(float x,float y,float w,float h,int c){d.rect(x,y,w,h,color(c));}
 public void round(float x,float y,float w,float h,float r,int c){if(theme==Theme.LCD&&y>=530&&h>=60){d.round(x,y,w,h,r,h==74||h==84?0xff899575:LCD_BG);return;}int lum=(((c>>16)&255)*3+((c>>8)&255)*6+(c&255))/10;d.round(x,y,w,h,r,theme==Theme.LCD&&w>90&&h>=20&&lum<90?LCD_BG:color(c));}
 public void circle(float x,float y,float r,int c){d.circle(x,y,r,color(c));}
 public void polygon(float[] points,int c){d.polygon(points,color(c));}
 public void text(String t,float x,float y,float size,int c){d.text(t,x,y,size,theme==Theme.LCD?((c==0xff8098a2||c==0xff6e8998)?0xff69765c:LCD_INK):c);}
 public void beginDisplay(boolean inverted){d.beginDisplay(inverted);}public void endDisplay(){d.endDisplay();}
 public void save(){d.save();}public void restore(){d.restore();}public void rotate(float angle,float x,float y){d.rotate(angle,x,y);}public void clipRound(float x,float y,float w,float h,float r){d.clipRound(x,y,w,h,r);}
}
