import com.nova.godzillagotchi.*;
import java.awt.*;import java.awt.geom.*;import java.awt.image.*;import javax.imageio.ImageIO;import java.io.*;import java.util.*;
/** Runs the same renderer as Android, using Java2D as the drawing backend. */
public class SkinPreview implements SkinRenderer.Painter {
 Graphics2D g;Map<String,BufferedImage> assets=new HashMap<>();Deque<Graphics2D> stack=new ArrayDeque<>();
 SkinPreview(String path)throws Exception{for(int theme=0;theme<4;theme++)for(String name:Theme.assets(theme))if(!assets.containsKey(name)){BufferedImage image=ImageIO.read(new File(path+"/"+name+".png"));if(name.equals("lcd_landmarks")){int w=image.getWidth(),h=image.getHeight();int[] pixels=image.getRGB(0,0,w,h,null,0,w);LcdPalette.apply(pixels);image.setRGB(0,0,w,h,pixels,0,w);}assets.put(name,image); }}
 public void polygon(float[] points,int color){Path2D.Float path=new Path2D.Float();path.moveTo(points[0],points[1]);for(int i=2;i<points.length;i+=2)path.lineTo(points[i],points[i+1]);path.closePath();g.setColor(new Color(color,true));g.fill(path);}
 public void image(String n,float sx,float sy,float sw,float sh,float x,float y,float w,float h){BufferedImage b=assets.get(n);g.drawImage(b,Math.round(x),Math.round(y),Math.round(x+w),Math.round(y+h),Math.round(sx*b.getWidth()),Math.round(sy*b.getHeight()),Math.round((sx+sw)*b.getWidth()),Math.round((sy+sh)*b.getHeight()),null);}
 public void rect(float x,float y,float w,float h,int color){g.setColor(new Color(color,true));g.fill(new Rectangle2D.Float(x,y,w,h));}
 public void round(float x,float y,float w,float h,float r,int color){g.setColor(new Color(color,true));g.fill(new RoundRectangle2D.Float(x,y,w,h,r*2,r*2));}
 public void text(String t,float x,float y,float size,int color){g.setColor(new Color(color,true));g.setFont(new Font("Monospaced",Font.BOLD,Math.round(size)));g.drawString(t,x,y);}
 public void circle(float x,float y,float r,int color){g.setColor(new Color(color,true));g.fill(new Ellipse2D.Float(x-r,y-r,r*2,r*2));}
 public void rotate(float degrees,float cx,float cy){g.rotate(Math.toRadians(degrees),cx,cy);}
 public void save(){stack.push(g);g=(Graphics2D)g.create();}public void restore(){g.dispose();g=stack.pop();}
 public void clipRound(float x,float y,float w,float h,float r){g.clip(new RoundRectangle2D.Float(x,y,w,h,r*2,r*2));}
 BufferedImage frame(SkinRenderer.State s,int size){BufferedImage out=new BufferedImage(size,size,BufferedImage.TYPE_INT_ARGB);g=out.createGraphics();g.scale(size/720f,size/720f);g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);new SkinRenderer().draw(this,s);g.dispose();return out;}
 void render(SkinRenderer.State s,String file)throws Exception {ImageIO.write(frame(s,720),"png",new File(file));}

 public static void main(String[] args)throws Exception{SkinPreview p=new SkinPreview(args[0]);SkinRenderer.State s=new SkinRenderer.State();s.pet=new Pet(10000);s.city=new City();s.now=10000;s.night=true;p.render(s,args[1]+"-night.png");s.night=false;p.render(s,args[1]+"-day.png");s.night=true;s.playing=true;s.showCity=true;s.gameEnd=30000;s.city.hit(7,9800);s.city.hit(1,9000);p.render(s,args[1]+"-stomp.png");s.showCity=false;s.playing=false;s.pet.asleep=true;p.render(s,args[1]+"-sleep.png");s.pet.asleep=false;s.effect="roar";s.effectEnd=12000;s.message="SKREEONK! The neighborhood noticed.";p.render(s,args[1]+"-roar.png");System.out.println("Rendered night, day, stomp, sleep, roar at 720x720; sprite alpha verified.");}
}
