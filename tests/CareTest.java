import com.nova.godzillagotchi.*;
public class CareTest {
 static void check(boolean b,String m){if(!b)throw new AssertionError(m);}
 public static void main(String[] args){
 Pet p=new Pet(1000);p.hatched=false;p.tick(99999999);check(p.food==80&&p.messes==0,"eggs do not decay");for(int i=0;i<4;i++)check(!p.hatch(100000000+i),"wait for five taps");check(p.hatch(100000010)&&!p.hatch(100000020),"hatch once");check(p.birthday==100000010,"birthday starts at hatch");
 p=new Pet(0);p.tick(3*3600000+1000);check(p.messes==1,"mess after three hours");p.tick(24*3600000);check(p.messes==3,"mess cap");p.wash();check(p.messes==0&&p.poopHours==0,"wash clears mess");
 p=new Pet(0);p.food=10;p.clean=10;p.tick(4*3600000+1000);check(p.sick&&p.expression(99999999)==2,"poor care causes illness");check(p.medicine()&&!p.sick&&p.poorHours==0&&!p.medicine(),"medicine cures only when needed");
 Pet dark=new Pet(0),light=new Pet(0);dark.energy=light.energy=10;dark.asleep=light.asleep=true;dark.lightsOff=true;dark.tick(3600000);light.tick(3600000);check(Math.abs(dark.energy-24)<.001&&Math.abs(light.energy-18)<.001,"lights improve sleep");
 p=new Pet(0);check(p.bond(0)&&!p.bond(59000)&&p.bond(60000)&&p.xp==2,"bond cooldown");p.asleep=true;check(!p.bond(120000),"no waking via pats");
 p=new Pet(0);p.favorite=1;p.joy=30;p.feed(0);check(p.joy==30,"ordinary fish");p.feed(1);check(p.joy==35&&p.meals==2&&p.weight>25,"favorite food and weight");
 p=new Pet(0);p.food=p.clean=p.joy=100;p.tick(6*3600000+1000);check(p.xp==1,"care contributes growth");p.xp=750;p.bonds=8;check(p.personality().equals("PLAYFUL")&&p.form().equals("Heart crest"),"bonding affects appearance");p.bonds=0;p.sleepHours=20;check(p.form().equals("Moon crest"),"rest affects appearance");p.sleepHours=0;p.wellHours=24;check(p.form().equals("Guardian crown"),"care affects appearance");
 p=new Pet(0);MatchingGame g=new MatchingGame(7);for(int value=0;value<3;value++)for(int i=0;i<6;i++)if(g.cards[i]==value)g.tap(i);check(g.pairs==3&&g.reward(p,0)&&!g.reward(p,1),"matching game rewards once");check(!p.gameReward(599999)&&p.gameReward(600000),"game cooldown");
 check(CareRules.quiet(21,21,8)&&CareRules.quiet(7,21,8)&&!CareRules.quiet(8,21,8)&&!CareRules.quiet(20,21,8),"overnight quiet boundaries");check(CareRules.quiet(12,10,14)&&!CareRules.quiet(14,10,14)&&!CareRules.quiet(1,1,1),"daytime and disabled quiet hours");p=new Pet(0);check(!CareRules.needsAttention(p),"no unneeded call");p.food=10;check(CareRules.needsAttention(p),"hungry call");p.asleep=true;check(!CareRules.needsAttention(p),"no sleeping calls");
 System.out.println("PASS: hatching, messes, illness, medicine, lights, bonding, food, growth, forms, matching, reminder rules");
 }
}
