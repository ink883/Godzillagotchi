package com.nova.godzillagotchi;
/** Shared drawing, input and native-control geometry in a 720-square canvas. */
public final class SkinLayout {
 public static final float SCENE_X=50,SCENE_Y=108,SCENE_W=620,SCENE_H=337;
 public static float[] button(int i){return new float[]{20+(i%3)*230,534+(i/3)*84,220,74};}
 public static float[] fightButton(int i){return new float[]{20+(i%2)*345,534+(i/2)*84,335,74};}
 public static float[] cityExitButton(){return new float[]{486,114,164,44};}
 public static float scale(int w,int h){return Math.min(w,h)/720f;}
 public static float offsetX(int w,int h){return (w-720*scale(w,h))/2;}
 public static float offsetY(int w,int h){return (h-720*scale(w,h))/2;}
 public static float cityX(float x){return (x-SCENE_X)*360/SCENE_W;}
 public static float cityY(float y){return (y-SCENE_Y)*250/SCENE_H;}
}
