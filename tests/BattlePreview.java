import com.nova.godzillagotchi.*;
public class BattlePreview {
 public static void main(String[] args)throws Exception{
 SkinPreview p=new SkinPreview(args[0]);SkinRenderer.State s=new SkinRenderer.State();s.pet=new Pet(10000);s.city=new City();s.now=10000;s.night=true;
 for(int i=0;i<6;i++){s.battle=new Battle(i);s.battleNow=10000;p.render(s,args[1]+"-opponent-"+i+".png");}
 s.battle=new Battle(2);s.battle.attack(3,10000);s.battleNow=10200;p.render(s,args[1]+"-breath.png");s.battle.tick(10700);s.battleNow=10800;p.render(s,args[1]+"-counter.png");s.battle.enemyHp=1;s.battle.attack(0,11000);s.battle.claimReward(s.pet);s.battleNow=12000;p.render(s,args[1]+"-victory.png");
 System.out.println("Rendered six opponents, breath, counterattack, and victory at 720x720.");
 }
}
