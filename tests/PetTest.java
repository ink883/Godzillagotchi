import com.nova.godzillagotchi.Pet;
public class PetTest {
 static void check(boolean b,String s){if(!b)throw new AssertionError(s);}
 public static void main(String[] args){
 Pet p=new Pet(0);p.tick(3600000);check(Math.abs(p.food-76)<.001&&Math.abs(p.energy-82)<.001,"one hour decay");
 p.asleep=true;p.lightsOff=true;p.tick(7200000);check(Math.abs(p.energy-96)<.001,"sleep recovery");p.tick(10800000);check(p.energy==100,"cap at 100");
 p=new Pet(0);p.tick(1000L*3600000);check(p.food==0&&Math.abs(p.energy-13)<.001,"absence cap and nonnegative stats");
 p=new Pet(1000);p.tick(0);check(p.food==80,"clock rollback");
 p.food=99;p.feed();check(p.food==100,"food cap");p.energy=14;check(!p.roar()&&p.energy==14,"roar guard");p.energy=15;check(p.roar()&&p.energy==0,"roar cost");
 p.xp=249;check(p.stage().equals("HATCHLING"),"hatchling");p.xp=250;check(p.stage().equals("JUNIOR KAIJU"),"junior");p.xp=3600;check(p.stage().equals("KING OF MONSTERS"),"king");
 p.energy=50;p.joy=90;p.xp=0;p.play();check(p.energy==38&&p.joy==100&&p.xp==12,"minigame reward");
 System.out.println("PASS: decay, offline cap, sleep, clock rollback, stat bounds, roar costs, growth, play rewards");
 }
}
