package com.nova.godzillagotchi;
/** Stable save IDs; zero retains the original city tower. */
public final class Landmarks {
 public static final String[] NAMES={"CITY TOWER","JAPANESE SHIRO","EIFFEL TOWER","LEANING TOWER OF PISA","TRUMP TOWER","BURJ KHALIFA","TAJ MAHAL"};
 public static int valid(int type){return type>=0&&type<NAMES.length?type:0;}
 public static void migrate(Rampage r,boolean hasLandmark){if(!hasLandmark){r.landmark=0;r.building=r.finished&&r.building>=8?1:0;r.hp=Math.min(150,Math.max(0,r.hp));}else r.building=Math.min(1,Math.max(0,r.building));}
}
