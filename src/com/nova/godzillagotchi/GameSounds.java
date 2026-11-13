package com.nova.godzillagotchi;
import android.content.Context;import android.media.AudioAttributes;import android.media.SoundPool;import android.os.SystemClock;
/** Two short-effect channels. Late loads expire; pausing stops rather than resumes old sounds. */
public final class GameSounds {
 private final SoundPool pool;private final int[] samples=new int[SoundCues.ASSETS.length];private final boolean[] ready=new boolean[samples.length];
 private final int[] streams=new int[3],pending={-1,-1,-1};private final long[] expires=new long[3];private boolean active=false,released=false;
 public GameSounds(Context context){pool=new SoundPool.Builder().setMaxStreams(3).setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build()).build();pool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener(){public void onLoadComplete(SoundPool soundPool,int sample,int status){if(released)return;for(int i=0;i<samples.length;i++)if(samples[i]==sample){ready[i]=status==0;int channel=channel(i);if(pending[channel]==i){if(status==0&&active&&SystemClock.elapsedRealtime()<=expires[channel])playReady(i,channel);else pending[channel]=-1;}break;}}});for(int i=0;i<samples.length;i++){int resource=context.getResources().getIdentifier(SoundCues.ASSETS[i],"raw",context.getPackageName());if(resource==0)throw new IllegalStateException("Missing sound "+SoundCues.ASSETS[i]);samples[i]=pool.load(context,resource,1);}}
 private static int channel(int cue){return cue==SoundCues.WILHELM?2:cue<SoundCues.ENEMY_BASE?0:1;}
 public void resume(){if(!released)active=true;}
 public void play(int cue){if(released||!active||cue<0||cue>=samples.length)return;int ch=channel(cue);stop(ch);pending[ch]=cue;expires[ch]=SystemClock.elapsedRealtime()+250;if(ready[cue])playReady(cue,ch);}
 private void playReady(int cue,int ch){pending[ch]=-1;float volume=cue==SoundCues.BUBBLES?.75f:cue==SoundCues.MUNCH?.9f:.72f;streams[ch]=pool.play(samples[cue],volume,volume,1,0,1);}
 private void stop(int ch){pending[ch]=-1;if(streams[ch]!=0){pool.stop(streams[ch]);streams[ch]=0;}}
 public void stopCare(){if(!released)stop(0);}public void stopEnemy(){if(!released)stop(1);}
 public void stopAll(){if(!released){stop(0);stop(1);stop(2);}}
 public void pause(){active=false;stopAll();}
 public void release(){if(released)return;pause();released=true;pool.release();}
}
