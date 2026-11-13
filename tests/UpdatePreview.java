import com.nova.godzillagotchi.*;
public class UpdatePreview {
 public static void main(String[] args)throws Exception{SkinPreview p=new SkinPreview(args[0]);SkinRenderer.State s=new SkinRenderer.State();s.pet=new Pet(0);s.city=new City();s.night=true;s.now=10000;s.effect="roar";s.effectEnd=12500;s.message="SKREEONK! The neighborhood noticed.";p.render(s,args[1]+"-roar.png");s.effect="";s.effectEnd=0;for(int i=0;i<5;i++){s.pet.xp=Pet.LEVEL_XP[i];p.render(s,args[1]+"-level-"+(i+1)+".png");}
 s.pet.xp=0;s.rampage=new Rampage(0);s.rampage.tick(0);s.rampage.tick(4000);s.rampage.vehicles[1]=new Rampage.Vehicle(1,2000);s.rampage.vehicles[2]=new Rampage.Vehicle(2,3000);s.rampage.attack(1);s.rampage.tick(4470);p.render(s,args[1]+"-city.png");s.rampage=null;s.battle=new Battle(0);s.battle.attack(0,10000);s.battle.tick(10380);s.battleNow=10380;p.render(s,args[1]+"-battle.png");System.out.println("Rendered roar, five growth sizes, city and battle at 720 square.");}
}
