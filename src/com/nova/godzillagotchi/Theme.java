package com.nova.godzillagotchi;
import android.content.SharedPreferences;
/** Cosmetic settings never alter pet state or encounter timing. IDs are stable save values. */
public final class Theme {
 public static final int CLASSIC=0,PUPPET=1,VINYL=2,LCD=3;
 public static final String[] NAMES={"Classic","Puppet Theater","Vinyl Toy","LCD"};
 public static int valid(int id){return id>=0&&id<NAMES.length?id:PUPPET;}
 public static int load(SharedPreferences p){return valid(p.getInt("theme",PUPPET));}
 public static void save(SharedPreferences p,int id){p.edit().putInt("theme",valid(id)).apply();}
 public static String[] assets(int id){switch(valid(id)){
 case CLASSIC:return new String[]{"shell","pet_sprite","pet_sleep","pet_roar","care_poses","attack_poses","opponents","enemy_attacks","fish_types","city_atlas"};
 case VINYL:return new String[]{"shell","vinyl_pet","vinyl_enemies","vinyl_props","vinyl_scenery","vinyl_background"};
 case LCD:return new String[]{"shell","lcd_pet","lcd_enemies","lcd_props"};
 default:return new String[]{"shell","paper_pet","paper_states","paper_attacks","paper_opponents","paper_enemy_attacks","paper_background","paper_scenery","paper_props"};}}
 public static final class Sprite {public final String name;public final float[] bounds;Sprite(String n,float[] b){name=n;bounds=b;}}
 static float[][] source(String n){if(n.equals("paper_pet"))return PaperArt.PET;if(n.equals("paper_states"))return PaperArt.STATES;if(n.equals("paper_attacks"))return PaperArt.ATTACKS;if(n.equals("paper_opponents"))return PaperArt.OPPONENTS;if(n.equals("paper_enemy_attacks"))return PaperArt.ENEMY_ATTACKS;if(n.equals("paper_props"))return PaperArt.PROPS;if(n.equals("paper_scenery"))return PaperArt.SCENERY;return null;}
 static int index(float[][] rows,float sx,float sy,float sw,float sh){float cx=sx+sw/2,cy=sy+sh/2;for(int i=0;i<rows.length;i++){float[] q=rows[i];if(cx>=q[0]-.001f&&cx<=q[0]+q[2]+.001f&&cy>=q[1]-.001f&&cy<=q[1]+q[3]+.001f)return i;}throw new IllegalArgumentException("Unknown sprite bounds "+sx+","+sy);}
 public static Sprite sprite(int theme,String n,int i){if(theme==PUPPET)return new Sprite(n,source(n)[i]);
  if(theme==CLASSIC){if(n.equals("paper_pet"))return new Sprite("pet_sprite",new float[]{.07f,.11f,.84f,.82f});
   if(n.equals("paper_states")){if(i==0)return new Sprite("pet_sleep",new float[]{.06f,.10f,.90f,.86f});if(i==1)return new Sprite("pet_roar",new float[]{.05f,.08f,.84f,.84f});int p=i-2;return new Sprite("care_poses",new float[]{p%2*.5f+.035f,p/2*.5f+.025f,.43f,.45f});}
   if(n.equals("paper_attacks"))return new Sprite("attack_poses",ThemeAssets.CLASSIC_ATTACKS[i]);if(n.equals("paper_opponents"))return new Sprite("opponents",ThemeAssets.CLASSIC_ENEMIES[i]);if(n.equals("paper_enemy_attacks"))return new Sprite("enemy_attacks",ThemeAssets.CLASSIC_ENEMY_ATTACKS[i]);if(n.equals("paper_props")&&i<3)return new Sprite("fish_types",ThemeAssets.CLASSIC_FISH[i]);return null;
  }
  boolean lcd=theme==LCD;String prefix=lcd?"lcd_":"vinyl_";float[][] pets=lcd?ThemeAssets.LCD_PET:ThemeAssets.VINYL_PET,enemies=lcd?ThemeAssets.LCD_ENEMIES:ThemeAssets.VINYL_ENEMIES;
  if(n.equals("paper_pet"))return new Sprite(prefix+"pet",pets[0]);if(n.equals("paper_states"))return new Sprite(prefix+"pet",pets[i+1]);if(n.equals("paper_attacks"))return new Sprite(prefix+"pet",pets[i+7]);if(n.equals("paper_opponents"))return new Sprite(prefix+"enemies",enemies[i]);if(n.equals("paper_enemy_attacks"))return new Sprite(prefix+"enemies",enemies[i+6]);if(n.equals("paper_props"))return new Sprite(prefix+"props",(lcd?ThemeAssets.LCD_PROPS:ThemeAssets.VINYL_PROPS)[i]);if(n.equals("paper_scenery")&&!lcd)return new Sprite("vinyl_scenery",ThemeAssets.VINYL_SCENERY[i]);return null;
 }
}
