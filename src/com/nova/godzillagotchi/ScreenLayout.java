package com.nova.godzillagotchi;
/** Coordinates in dp; all controls remain inside the available app window. */
public class ScreenLayout {
 public final int pad,header,sceneTop,sceneHeight,statusTop,statusHeight,metersTop,meterHeight,buttonsTop,buttonHeight,gap;
 public ScreenLayout(int height){pad=10;gap=5;header=42;statusHeight=28;meterHeight=19;buttonHeight=height<300?38:48;
 sceneTop=pad+header+gap;int reserved=sceneTop+gap+statusHeight+2*meterHeight+gap+2*buttonHeight+gap+pad;
 sceneHeight=Math.max(1,height-reserved);statusTop=sceneTop+sceneHeight+gap;metersTop=statusTop+statusHeight;buttonsTop=metersTop+2*meterHeight+gap;
 }
}
