package com.nova.godzillagotchi;
/** Inner glass contour measured against the original shell in 720-square coordinates. */
public final class DisplayFrame {
 public static final float[] EDGE={86,80,630,80,641,83,650,91,658,108,664,136,669,180,673,245,675,330,675,433,673,470,667,489,655,498,639,500,77,500,63,498,53,491,47,478,44,453,43,400,43,320,44,245,47,190,52,140,57,112,64,94,73,84};
 public static boolean inverted(SkinRenderer.State s){return s.theme==Theme.LCD&&s.pet.lightsOff;}
 public static int invert(int c){return (c&0xff000000)|(clamp(216-((c>>16)&255))<<16)|(clamp(232-((c>>8)&255))<<8)|clamp(182-(c&255));}
 private static int clamp(int c){return Math.max(0,Math.min(255,c));}
}
