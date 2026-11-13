import com.nova.godzillagotchi.*;import java.awt.*;import java.awt.image.*;import java.io.*;import javax.imageio.*;
/** Same production renderer for every theme and state. */
public class ThemePreview {
 public static void main(String[] args)throws Exception{SkinPreview p=new SkinPreview(args[0]);BufferedImage sheet=new BufferedImage(1440,1440,BufferedImage.TYPE_INT_RGB);Graphics2D g=sheet.createGraphics();
 for(int theme=0;theme<4;theme++)for(int state=0;state<13;state++){SkinRenderer.State s=new SkinRenderer.State();s.theme=theme;s.pet=new Pet(0);s.city=new City();s.now=10000;s.night=state%2==1;
 if(state==1){s.pet.asleep=true;}
 if(state==2){s.pet.hatched=false;s.pet.eggTaps=3;}
 if(state==3){s.effect="fish";s.fishType=1;s.effectEnd=11500;s.message="Tuna!";}
 if(state==4){s.effect="bath";s.effectEnd=11500;s.message="Squeaky clean.";}
 if(state>=5&&state<=10){s.battle=EnemyPreview.counter(state-5,true);s.battleNow=10000+s.battle.enemyHitMs()+40;}
 if(state==11){s.rampage=new Rampage(0);s.rampage.hp=65;s.rampage.time=3800;s.rampage.vehicles[0]=new Rampage.Vehicle(0,3200);s.rampage.vehicles[1]=new Rampage.Vehicle(1,1300);s.rampage.vehicles[2]=new Rampage.Vehicle(2,2800);}
 if(state==12){s.rampage=new Rampage(0);s.rampage.hp=0;s.rampage.time=1000;s.rampage.hitAt=500;s.rampage.move=3;s.rampage.phase=2;s.rampage.eventAt=-400;}
 BufferedImage frame=p.frame(s,720);ImageIO.write(frame,"png",new File(args[1]+"-theme-"+theme+"-state-"+state+".png"));if(state==0)g.drawImage(frame,theme%2*720,theme/2*720,null);
 }g.dispose();ImageIO.write(sheet,"png",new File(args[1]+"-themes.png"));System.out.println("Rendered 52 theme/state combinations and overview.");}
}
