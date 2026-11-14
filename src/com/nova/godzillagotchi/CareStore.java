package com.nova.godzillagotchi;
import android.content.SharedPreferences;
/** Extension fields are versioned separately so installed pets never return to eggs. */
public final class CareStore {
 public static Pet newEgg(long now){Pet egg=new Pet(now);egg.hatched=false;return egg;}
 public static SharedPreferences.Editor resetEditor(SharedPreferences p){boolean reminders=p.getBoolean("reminders",false);int start=p.getInt("quiet_start",21),end=p.getInt("quiet_end",8),theme=Theme.load(p);return p.edit().clear().putBoolean("reminders",reminders).putInt("quiet_start",start).putInt("quiet_end",end).putInt("theme",theme);}
 public static void load(Pet t,SharedPreferences p,long now){boolean legacy=p.contains("updated");t.hatched=p.getBoolean("care_hatched",legacy);t.adopted=p.getBoolean("care_adopted",legacy);t.name=p.getString("care_name","Godzilla");t.birthday=p.getLong("care_birthday",now);t.eggTaps=p.getInt("care_egg",0);t.lightsOff=p.getBoolean("care_dark",false);t.sick=p.getBoolean("care_sick",false);t.messes=p.getInt("care_mess",0);t.favorite=p.getInt("care_favorite",t.favorite);t.meals=p.getInt("care_meals",0);t.baths=p.getInt("care_baths",0);t.bonds=p.getInt("care_bonds",0);t.games=p.getInt("care_games",0);t.medicines=p.getInt("care_meds",0);t.weight=p.getFloat("care_weight",25);t.poopHours=p.getFloat("care_poop_hours",0);t.poorHours=p.getFloat("care_poor_hours",0);t.wellHours=p.getFloat("care_well_hours",0);t.sleepHours=p.getFloat("care_sleep_hours",0);t.lastBond=p.getLong("care_last_bond",-60000);t.lastGame=p.getLong("care_last_game",-600000);
  for(int type=0;type<3;type++) t.restoreHarborFish(type,p.getInt("harbor_fish_"+type,0));
  for(int i=0;i<3;i++)t.outings.completed[i]=Math.max(0,p.getInt("outing_count_"+i,0));
  t.outings.firstRequestAt=p.getLong("outing_request_at",0);
  t.outings.accepted=p.getInt("outing_accepted",Outings.NONE);
  t.outings.bonusPending=p.getBoolean("outing_bonus_pending",false);
 }
 public static void save(Pet t,SharedPreferences.Editor e){for(int type=0;type<3;type++) e.putInt("harbor_fish_"+type,t.harborFish(type));
  for(int i=0;i<3;i++)e.putInt("outing_count_"+i,t.outings.completed[i]);
  e.putLong("outing_request_at",t.outings.firstRequestAt).putInt("outing_accepted",t.outings.accepted).putBoolean("outing_bonus_pending",t.outings.bonusPending);
  e.putBoolean("care_hatched",t.hatched).putBoolean("care_adopted",t.adopted).putString("care_name",t.name).putLong("care_birthday",t.birthday).putInt("care_egg",t.eggTaps).putBoolean("care_dark",t.lightsOff).putBoolean("care_sick",t.sick).putInt("care_mess",t.messes).putInt("care_favorite",t.favorite).putInt("care_meals",t.meals).putInt("care_baths",t.baths).putInt("care_bonds",t.bonds).putInt("care_games",t.games).putInt("care_meds",t.medicines).putFloat("care_weight",(float)t.weight).putFloat("care_poop_hours",(float)t.poopHours).putFloat("care_poor_hours",(float)t.poorHours).putFloat("care_well_hours",(float)t.wellHours).putFloat("care_sleep_hours",(float)t.sleepHours).putLong("care_last_bond",t.lastBond).putLong("care_last_game",t.lastGame);}
}
