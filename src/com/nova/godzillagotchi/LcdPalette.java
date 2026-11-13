package com.nova.godzillagotchi;
/** The LCD display maps landmark ink and substrate to the hardware's two colors at load time. */
public final class LcdPalette {
 public static void apply(int[] pixels){for(int i=0;i<pixels.length;i++){int c=pixels[i],lum=(((c>>16)&255)*3+((c>>8)&255)*6+(c&255))/10;pixels[i]=(c&0xff000000)|((lum>=128?ThemePainter.LCD_BG:ThemePainter.LCD_INK)&0xffffff);}}
}
