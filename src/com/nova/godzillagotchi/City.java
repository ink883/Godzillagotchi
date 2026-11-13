package com.nova.godzillagotchi;
public class City {
 public final boolean[] destroyed=new boolean[10];public final long[] hitAt=new long[10];public int count;
 public void reset(){count=0;java.util.Arrays.fill(destroyed,false);java.util.Arrays.fill(hitAt,0);}
 public static int left(int i){return 10+(i%5)*69;}
 public static int bottom(int i){return 122+(i/5)*108;}
 public static int top(int i){return bottom(i)-(56+(i*13)%29);}
 public int target(float x,float y){for(int i=0;i<10;i++)if(!destroyed[i]&&x>=left(i)&&x<left(i)+54&&y>=top(i)&&y<bottom(i))return i;return -1;}
 public boolean hit(int i,long now){if(i<0||i>=10||destroyed[i])return false;destroyed[i]=true;hitAt[i]=now;count++;return true;}
 public static boolean isNight(int hour){return hour<6||hour>=18;}
}
