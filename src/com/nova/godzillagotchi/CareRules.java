package com.nova.godzillagotchi;
public final class CareRules {
 public static boolean quiet(int hour,int start,int end){return start==end?false:start<end?hour>=start&&hour<end:hour>=start||hour<end;}
 public static boolean needsAttention(Pet p){return p.hatched&&!p.asleep&&(p.sick||p.food<30||p.joy<35||p.energy<30||p.clean<30||p.messes>0);}
}
