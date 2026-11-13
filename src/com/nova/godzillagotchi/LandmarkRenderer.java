package com.nova.godzillagotchi;
/** Damage separates the facade into paper-like torn sections; collapse drops those same sections. */
public final class LandmarkRenderer {
 public static boolean hit(Rampage r,int theme,float px,float py){if(r.landmark==0)return px>=440&&px<=614&&py>=170&&py<=426;float[] q=LandmarkAssets.BOUNDS[Theme.valid(theme)][r.landmark-1];float aspect=q[2]/q[3]*(theme==Theme.PUPPET?1.5f:1),w=Math.min(204,253*aspect),h=w/aspect;return px>=527-w/2&&px<=527+w/2&&py>=426-h&&py<=426;}
 public static void draw(SkinRenderer.Painter d,SkinRenderer.State s,float shakenX){Rampage r=s.rampage;if(r.building>=Rampage.BUILDINGS)return;
  int theme=Theme.valid(s.theme);String asset=LandmarkAssets.ASSETS[theme];float[] q=LandmarkAssets.BOUNDS[theme][r.landmark-1];float aspect=q[2]/q[3]*(theme==Theme.PUPPET?1.5f:1);
  float w=Math.min(204,253*aspect),h=w/aspect,x=527-w/2+(shakenX-444),y=426-h,age=r.time-r.hitAt;
  int damage=r.hp==150?0:r.hp>75?1:2;
  if(damage==0)d.image(asset,q[0],q[1],q[2],q[3],x,y,w,h);
  else {int cols=3,rows=5;for(int row=0;row<rows;row++)for(int col=0;col<cols;col++){int i=row*cols+col;float sx=x+col*w/cols,sy=y+row*h/rows,cw=w/cols,ch=h/rows;
   float split=damage*.8f;sx+=(col-1)*split;sy+=row*split*.3f;
   float fall=r.hp==0?Math.max(0,Math.min(1.6f,age/1000f)-(4-row)*.055f):0;sx+=(float)Math.sin(fall*5+i)*fall*24;sy+=fall*fall*220;
   d.save();d.rotate((i%2==0?1:-1)*fall*(65+i*7),sx+cw/2,sy+ch/2);d.image(asset,q[0]+q[2]*col/cols,q[1]+q[3]*row/rows,q[2]/cols,q[3]/rows,sx,sy,cw,ch);
   if(theme==Theme.PUPPET&&r.hp>0&&col<2){float[] tear={sx+cw-1,sy+ch*.25f,sx+cw-4,sy+ch*.4f,sx+cw-1,sy+ch*.54f,sx+cw-5,sy+ch*.7f,sx+cw,sy+ch*.9f,sx+cw+1,sy+ch*.25f};d.polygon(tear,0xffefe0ba);}d.restore();
  }}
  d.round(444,433,166,7,2,0xff132d3e);d.round(444,433,166*r.hp/150f,7,2,0xffffb571);
 }
}
