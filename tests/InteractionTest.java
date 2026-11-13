import com.nova.godzillagotchi.*;
import java.awt.image.*;import java.awt.*;import javax.imageio.*;import java.io.*;
public class InteractionTest {
 static void check(boolean b,String m){if(!b)throw new AssertionError(m);}
 public static void main(String[] args)throws Exception{
  int hits=0;for(int i=0;i<100;i++)if(SoundCues.screamForRoll(i))hits++;check(hits==5,"exact 5 percent roll range");
  Pet pet=new Pet(0);pet.hatched=true;pet.messes=3;pet.clean=20;int xp=pet.xp;
  for(int i=0;i<3;i++)check(MessLayout.hit(3,101+i*65,401)==i,"each drawn pile hittable");
  check(MessLayout.hit(3,130,401)==-1&&MessLayout.hit(3,100,376)==-1,"gaps and sky do not clean");
  for(int i=0;i<3;i++)check(pet.cleanMess(),"remove individual pile");check(!pet.cleanMess()&&pet.clean==50&&pet.xp==xp,"no empty cleanup rewards");check(MessLayout.hit(0,101,401)==-1,"removed mess cannot be tapped");
  SkinPreview p=new SkinPreview("res/drawable-nodpi");BufferedImage sheet=new BufferedImage(1440,1440,BufferedImage.TYPE_INT_RGB);Graphics2D g=sheet.createGraphics();
  for(int theme=0;theme<4;theme++)for(int who=0;who<6;who++)for(int pose=0;pose<2;pose++){
   SkinRenderer.State s=new SkinRenderer.State();s.pet=new Pet(0);s.pet.hatched=true;s.city=new City();s.theme=theme;s.battle=EnemyPreview.counter(who,true);s.battleNow=10000+(pose==0?0:350);s.now=s.battleNow;
   g.drawImage(p.frame(s,180),(theme*2+pose)*180,who*240,null);
  }g.dispose();ImageIO.write(sheet,"png",new File("../Godzillagotchi-3.3-opponents.png"));
  System.out.println("PASS: exact scream probability, individual mess targets, no empty cleanup reward, all opponents in four themes");
 }
}
