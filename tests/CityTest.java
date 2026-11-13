import com.nova.godzillagotchi.City;
import com.nova.godzillagotchi.ScreenLayout;
public class CityTest {
 static void check(boolean b,String s){if(!b)throw new AssertionError(s);}
 public static void main(String[] args){
 City c=new City();
 for(int i=0;i<10;i++){check(c.target(City.left(i)+27,City.top(i)+10)==i,"specific building "+i);check(c.target(City.left(i)+55,City.top(i)+10)==-1,"gap "+i);check(c.target(City.left(i)+27,City.top(i)-1)==-1,"sky "+i);}
 check(c.hit(7,100),"hit building seven");check(c.count==1&&c.destroyed[7]&&!c.destroyed[0],"only touched building changes");check(c.target(City.left(7)+27,City.top(7)+10)==-1,"rubble cannot be hit");check(!c.hit(7,110)&&!c.hit(-1,110)&&!c.hit(10,110)&&c.count==1,"no duplicate rewards");
 for(int i=0;i<10;i++)c.hit(i,200);check(c.count==10,"all ten unique");c.reset();check(c.count==0&&!c.destroyed[7]&&c.hitAt[7]==0,"new round reset");
 check(City.isNight(0)&&City.isNight(5)&&!City.isNight(6)&&!City.isNight(17)&&City.isNight(18)&&City.isNight(23),"day night boundaries");
 for(int height:new int[]{260,280,300,320,360,480,640,800,1000}){ScreenLayout g=new ScreenLayout(height);check(g.sceneHeight>0,"visible scene");check(g.buttonsTop+2*g.buttonHeight+g.gap+g.pad<=height,"all buttons fit "+height);check(g.metersTop>=g.statusTop+g.statusHeight,"status meter separation");check(g.buttonsTop>=g.metersTop+g.meterHeight*2,"meter button separation");}
 System.out.println("PASS: all building targets, gaps, sky, independent destruction, duplicates, round reset, day/night boundaries, screen bounds at 9 heights");
 }
}
