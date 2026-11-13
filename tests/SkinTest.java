import com.nova.godzillagotchi.*;
public class SkinTest {
 static void check(boolean b,String m){if(!b)throw new AssertionError(m);}
 public static void main(String[] args){
 for(int[] size:new int[][]{{720,720},{720,672},{360,360},{1080,1920},{1280,720}}){float scale=SkinLayout.scale(size[0],size[1]),ox=SkinLayout.offsetX(size[0],size[1]),oy=SkinLayout.offsetY(size[0],size[1]);for(int i=0;i<6;i++){float[] b=SkinLayout.button(i);check(ox+b[0]*scale>=0&&oy+b[1]*scale>=0&&ox+(b[0]+b[2])*scale<=size[0]&&oy+(b[1]+b[3])*scale<=size[1],"button inside window");check(b[1]>SkinLayout.SCENE_Y+SkinLayout.SCENE_H,"buttons outside habitat");}}
 City c=new City();for(int i=0;i<10;i++){float x=SkinLayout.SCENE_X+(City.left(i)+27)*SkinLayout.SCENE_W/360;float y=SkinLayout.SCENE_Y+(City.top(i)+10)*SkinLayout.SCENE_H/250;check(c.target(SkinLayout.cityX(x),SkinLayout.cityY(y))==i,"rendered target and input geometry agree "+i);}
 check(SkinLayout.scale(720,720)==1&&SkinLayout.offsetX(720,720)==0&&SkinLayout.offsetY(720,720)==0,"native pixel mapping");System.out.println("PASS: skin controls fit five screen shapes, scene input matches all ten buildings, exact 720 pixel mapping");
 }
}
