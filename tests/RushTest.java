import com.nova.godzillagotchi.*;import java.awt.*;import java.awt.image.*;import java.io.*;import javax.imageio.*;
public class RushTest {
 static void check(boolean v,String m){if(!v)throw new AssertionError(m);}
 public static void main(String[] args)throws Exception {
  Pet p=new Pet(0);p.hatched=false;p.energy=10;check(!p.drinkRush()&&p.energy==10,"egg cannot drink");p.hatched=true;p.asleep=true;check(!p.drinkRush()&&p.energy==10,"sleeping pet cannot drink");p.asleep=false;
  double food=p.food;int xp=p.xp,meals=p.meals;check(p.drinkRush()&&p.energy==43,"33 points, not 33 percent of remaining energy");check(p.food==food&&p.xp==xp&&p.meals==meals,"drink does not count as fish or award XP");p.energy=90;p.drinkRush();check(p.energy==100,"energy capped");p.drinkRush();check(p.energy==100,"full bar remains full");
  SoundCues cues=new SoundCues();cues.start("drink",3700);check(cues.poll("drink",3700,1799,true)==-1,"silent during can travel");check(cues.poll("drink",3700,1800,true)==SoundCues.DRINK,"sip when can reaches mouth");check(cues.poll("drink",3700,1900,true)==-1,"one drink cue");cues.start("drink",3700);check(cues.poll("drink",3700,1800,false)==-1,"background stops drink");check(cues.poll("drink",3700,1900,true)==-1,"no replay on return");
  SkinPreview preview=new SkinPreview("res/drawable-nodpi");BufferedImage sheet=new BufferedImage(1440,1080,BufferedImage.TYPE_INT_RGB);Graphics2D g=sheet.createGraphics();
  for(int theme=0;theme<4;theme++)for(int frame=0;frame<3;frame++){SkinRenderer.State s=new SkinRenderer.State();s.pet=new Pet(0);s.pet.hatched=true;s.city=new City();s.theme=theme;s.effect="drink";s.effectEnd=10000+SoundCues.DRINK_MS;s.now=10000+new int[]{0,1200,2500}[frame];s.message="Kaiju Rush! +33% energy";g.drawImage(preview.frame(s,360),theme*360,frame*360,null);}
  g.dispose();ImageIO.write(sheet,"png",new File("../Godzillagotchi-3.4-rush.png"));System.out.println("PASS: Rush energy refill/cap, hatch/sleep guards, no fish/XP effects, synchronized and cancelled drinking audio, four-theme drinking preview");
 }
}
