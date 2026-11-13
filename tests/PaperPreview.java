import com.nova.godzillagotchi.*;import java.awt.*;import java.awt.image.*;import javax.imageio.*;import java.io.*;
/** Visual QA of the production renderer, including atlas boundaries and paper destruction. */
public class PaperPreview {
 public static void main(String[] args)throws Exception{SkinPreview p=new SkinPreview(args[0]);BufferedImage sheet=new BufferedImage(1440,1920,BufferedImage.TYPE_INT_RGB);Graphics2D g=sheet.createGraphics();
 for(int i=0;i<12;i++){SkinRenderer.State s=new SkinRenderer.State();s.pet=new Pet(0);s.city=new City();s.now=10000;s.night=i%2==1;
 if(i==0){s.effect="bath";s.effectEnd=11500;s.message="Paper bubbles!";s.pet.lastHappy=s.now;}
 if(i==1){s.effect="roar";s.effectEnd=12000;s.message="SKREEONK!";}
 if(i==2){s.pet.hatched=false;s.pet.eggTaps=3;}
 if(i>=3&&i<9){s.battle=EnemyPreview.counter(i-3,true);s.battleNow=10000+s.battle.enemyHitMs()+40;}
 if(i==9){s.effect="fish";s.fishType=2;s.effectEnd=11700;s.message="Sardines!";}
 if(i==10){s.rampage=new Rampage(0);s.rampage.hp=60;s.rampage.time=3800;s.rampage.vehicles[0]=new Rampage.Vehicle(0,3200);s.rampage.vehicles[1]=new Rampage.Vehicle(1,1300);}
 if(i==11){s.rampage=new Rampage(0);s.rampage.hp=0;s.rampage.time=1000;s.rampage.hitAt=500;s.rampage.move=3;s.rampage.phase=2;s.rampage.eventAt=-400;}
 p.render(s,args[1]+"-paper-"+i+".png");g.drawImage(p.frame(s,480),(i%3)*480,(i/3)*480,null);
 }g.dispose();ImageIO.write(sheet,"png",new File(args[1]+"-paper-overview.png"));System.out.println("Rendered paper care, roar, egg, six enemies, fish, tears and falling scraps.");}
}
