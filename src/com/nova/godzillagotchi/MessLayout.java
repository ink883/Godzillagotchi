package com.nova.godzillagotchi;
/** Shared drawing and touch geometry in the 720-square scene. */
public final class MessLayout {
 public static float x(int i){return 77+i*65;}
 public static int hit(int count,float x,float y){if(y<377||y>425)return -1;for(int i=0;i<count;i++)if(x>=x(i)&&x<=x(i)+48)return i;return -1;}
}
