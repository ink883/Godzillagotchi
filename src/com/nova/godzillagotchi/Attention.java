package com.nova.godzillagotchi;
import android.app.*;import android.app.job.*;import android.content.*;import java.util.Calendar;
/** Optional, inexact local checks. No server, exact alarm, or always-running service. */
public final class Attention extends JobService {
 public static volatile boolean foreground=false;
 public static void schedule(Context c){SharedPreferences p=c.getSharedPreferences("pet",0);JobScheduler jobs=(JobScheduler)c.getSystemService(Context.JOB_SCHEDULER_SERVICE);if(!p.getBoolean("reminders",false)){jobs.cancel(47);((NotificationManager)c.getSystemService(Context.NOTIFICATION_SERVICE)).cancel(47);return;}jobs.schedule(new JobInfo.Builder(47,new ComponentName(c,Attention.class)).setPeriodic(3600000).setPersisted(true).build());}
 public boolean onStartJob(JobParameters params){SharedPreferences p=getSharedPreferences("pet",0);long now=System.currentTimeMillis();int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);if(foreground||!p.getBoolean("reminders",false)||CareRules.quiet(hour,p.getInt("quiet_start",21),p.getInt("quiet_end",8))||now-p.getLong("last_reminder",0)<14400000)return false;
  Pet t=new Pet(p.getLong("updated",now));t.food=p.getFloat("food",80);t.joy=p.getFloat("joy",80);t.energy=p.getFloat("energy",85);t.clean=p.getFloat("clean",90);t.asleep=p.getBoolean("asleep",false);CareStore.load(t,p,now);t.tick(now);if(!CareRules.needsAttention(t))return false;
  NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);if(!nm.areNotificationsEnabled())return false;nm.createNotificationChannel(new NotificationChannel("care","Pet attention calls",NotificationManager.IMPORTANCE_DEFAULT));PendingIntent open=PendingIntent.getActivity(this,0,new Intent(this,MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
  try{nm.notify(47,new Notification.Builder(this,"care").setSmallIcon(R.drawable.attention).setContentTitle(t.name+" needs a little care").setContentText(t.need(false)).setContentIntent(open).setAutoCancel(true).build());p.edit().putLong("last_reminder",now).apply();}catch(SecurityException ignored){}return false;
 }
 public boolean onStopJob(JobParameters p){return true;}
}
