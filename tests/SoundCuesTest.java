import com.nova.godzillagotchi.*;
public class SoundCuesTest {
 static void check(boolean value,String message){if(!value)throw new AssertionError(message);}
 public static void main(String[] args){SoundCues cues=new SoundCues();
  cues.start("fish",4300);check(cues.poll("fish",4300,2499,true)==-1,"no munch before fish reaches mouth");check(cues.poll("fish",4300,2500,true)==SoundCues.MUNCH,"munch at bite");check(cues.poll("fish",4300,2550,true)==-1,"munch only once");
  cues.start("bath",2800);check(cues.poll("bath",2800,1000,true)==SoundCues.BUBBLES,"bubbles start with wash");check(cues.poll("bath",2800,1100,true)==-1,"no repeated bubble starts");
  cues.start("fish",4300);cues.cancel();check(cues.poll("fish",4300,2500,true)==-1,"background/reset cancels pending bite");
  cues.start("fish",4300);check(cues.poll("bath",3000,2500,true)==-1,"new action invalidates old feeding cue");
  cues.start("fish",4300);check(cues.poll("fish",4300,2500,false)==-1,"no care sound in combat/asleep/background");check(cues.poll("fish",4300,2600,true)==-1,"return does not replay cancelled sound");
  cues.start("fish",4300);check(cues.poll("fish",4300,4300,true)==-1,"expired visual never produces late audio");
  cues.start("fish",4300);cues.start("fish",5300);check(cues.poll("fish",5300,2500,true)==-1,"replacement meal gets own bite time");check(cues.poll("fish",5300,3500,true)==SoundCues.MUNCH,"replacement meal plays once");
  for(int who=0;who<6;who++){Battle battle=new Battle(who);check(SoundCues.enemy(who)==who+2,"distinct entrance roar mapping");battle.attack(0,1000);int before=battle.phase;battle.tick(1380);check(SoundCues.enemyTransition(before,battle)==-1,"player hit is not enemy wind-up");before=battle.phase;battle.tick(2100);check(SoundCues.enemyTransition(before,battle)==-1,"normal attack is silent");battle.turn=3;check(SoundCues.enemyTransition(before,battle)==who+2,"special attack roar");before=battle.phase;battle.tick(2550);check(SoundCues.enemyTransition(before,battle)==-1,"hit cannot repeat roar");battle.pause(2600);battle.resume(9000);check(SoundCues.enemyTransition(battle.phase,battle)==-1,"resume cannot repeat roar");
   battle=new Battle(who);battle.enemyHp=1;battle.attack(0,0);battle.tick(380);before=battle.phase;battle.tick(1100);check(battle.finished&&SoundCues.enemyTransition(before,battle)==-1,"defeated opponent does not roar");}
  check(SoundCues.enemy(-1)==-1&&SoundCues.enemy(6)==-1,"invalid opponents ignored");System.out.println("PASS: bite-time munch, wash timing, one-shot cues, cancellation/expiry, six enemy roars, counter transition and no defeat/resume replay");
 }
}
