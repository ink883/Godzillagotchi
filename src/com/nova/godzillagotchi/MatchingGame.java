package com.nova.godzillagotchi;
public final class MatchingGame {
 public final int[] cards={0,0,1,1,2,2};public final boolean[] matched=new boolean[6];public int first=-1,second=-1,pairs=0;public boolean rewarded=false;
 public MatchingGame(long seed){java.util.Random r=new java.util.Random(seed);for(int i=5;i>0;i--){int j=r.nextInt(i+1),v=cards[i];cards[i]=cards[j];cards[j]=v;}}
 public void tap(int i){if(i<0||i>=6||matched[i]||pairs==3)return;if(second>=0){first=second=-1;}if(i==first)return;if(first<0){first=i;return;}second=i;if(cards[first]==cards[second]){matched[first]=matched[second]=true;pairs++;first=second=-1;}}
 public boolean visible(int i){return matched[i]||i==first||i==second;}
 public boolean reward(Pet p,long now){if(pairs!=3||rewarded)return false;rewarded=true;return p.gameReward(now);}
}
