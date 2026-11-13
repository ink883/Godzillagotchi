import com.nova.godzillagotchi.*;import java.awt.*;import java.awt.image.*;import java.io.*;import javax.imageio.*;
public class DisplayTest {
 static void check(boolean b,String m){if(!b)throw new AssertionError(m);}
 public static void main(String[] args)throws Exception {
  SkinPreview p=new SkinPreview("res/drawable-nodpi");SkinRenderer.State s=new SkinRenderer.State();s.pet=new Pet(0);s.pet.hatched=true;s.pet.asleep=true;s.pet.xp=800;s.city=new City();s.now=10000;s.night=true;s.theme=Theme.LCD;
  check(!DisplayFrame.inverted(s),"night time alone does not invert");BufferedImage on=p.frame(s,720);s.pet.lightsOff=true;check(DisplayFrame.inverted(s),"lights off enables LCD inversion");BufferedImage off=p.frame(s,720);
  for(int y=0;y<720;y++)for(int x=0;x<720;x++)if(y<78||y>=503||x<40||x>=680)check(on.getRGB(x,y)==off.getRGB(x,y),"shell and buttons unchanged");
  check(on.getRGB(45,320)==on.getRGB(100,320)&&on.getRGB(673,320)==on.getRGB(100,320),"live background covers both old scene strips");
  check(DisplayFrame.invert(ThemePainter.LCD_BG)==ThemePainter.LCD_INK&&DisplayFrame.invert(ThemePainter.LCD_INK)==ThemePainter.LCD_BG,"LCD colors swap exactly");
  for(int theme=0;theme<4;theme++){s.theme=theme;check(DisplayFrame.inverted(s)==(theme==Theme.LCD),"other themes retain normal lighting");p.frame(s,360);}
  s.theme=Theme.LCD;s.pet.lightsOff=false;BufferedImage restored=p.frame(s,720);for(int y=0;y<720;y++)for(int x=0;x<720;x++)check(on.getRGB(x,y)==restored.getRGB(x,y),"turning light back on restores frame");
  BufferedImage pair=new BufferedImage(1440,720,BufferedImage.TYPE_INT_RGB);Graphics2D g=pair.createGraphics();g.drawImage(on,0,0,null);g.drawImage(off,720,0,null);g.dispose();ImageIO.write(pair,"png",new File("../Godzillagotchi-3.6-lcd.png"));System.out.println("PASS: LCD light switch, exact palette inversion, shell/button isolation, other themes and reversible lighting");
 }
}
