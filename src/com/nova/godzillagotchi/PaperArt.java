package com.nova.godzillagotchi;
/** Source bounds of the original, unmodified paper artwork. */
public final class PaperArt {
 public static final float[][] PET={{0.034290f,0.042265f,0.913876f,0.923445f}};
 public static final float[][] STATES={{0.026316f,0.105263f,0.288676f,0.332536f},{0.340510f,0.068581f,0.311802f,0.381978f},{0.676236f,0.073365f,0.285486f,0.377193f},{0.026316f,0.570175f,0.291866f,0.360447f},{0.339713f,0.570175f,0.294258f,0.359649f},{0.667464f,0.562201f,0.304625f,0.366029f}};
 public static final float[][] ATTACKS={{0.052632f,0.086922f,0.405104f,0.381180f},{0.553429f,0.083732f,0.387560f,0.385167f},{0.059011f,0.548644f,0.388357f,0.362041f},{0.529506f,0.546252f,0.399522f,0.370016f}};
 public static final float[][] OPPONENTS={{0.031898f,0.117225f,0.270335f,0.344498f},{0.334131f,0.140351f,0.332536f,0.318182f},{0.666667f,0.088517f,0.311802f,0.374003f},{0.013557f,0.576555f,0.305423f,0.323764f},{0.346890f,0.576555f,0.319777f,0.322169f},{0.666667f,0.649123f,0.318182f,0.250399f}};
 public static final float[][] ENEMY_ATTACKS={{0.011164f,0.147528f,0.329346f,0.309410f},{0.382775f,0.123604f,0.251196f,0.334928f},{0.640351f,0.125997f,0.352472f,0.334131f},{0.023923f,0.564593f,0.295853f,0.296651f},{0.335726f,0.590112f,0.338118f,0.275917f},{0.700159f,0.602871f,0.259171f,0.246411f}};
 public static final float[][] SCENERY={{0.070973f,0.056619f,0.208931f,0.535885f},{0.386762f,0.050239f,0.210526f,0.542265f},{0.705742f,0.051037f,0.226475f,0.541467f},{0.034290f,0.714514f,0.281499f,0.174641f},{0.364434f,0.709729f,0.271930f,0.170654f},{0.673844f,0.708931f,0.292663f,0.165072f}};
 public static final float[][] PROPS={{0.021531f,0.125199f,0.226475f,0.124402f},{0.268740f,0.098884f,0.221691f,0.174641f},{0.533493f,0.089314f,0.184211f,0.196970f},{0.782297f,0.086124f,0.173046f,0.209729f},{0.043860f,0.389952f,0.177033f,0.213716f},{0.275917f,0.395534f,0.197767f,0.197767f},{0.516746f,0.395534f,0.208931f,0.197767f},{0.764753f,0.405901f,0.209729f,0.185008f},{0.035885f,0.700159f,0.185805f,0.215311f},{0.258373f,0.730462f,0.234450f,0.155502f},{0.548644f,0.690590f,0.200957f,0.227273f},{0.749601f,0.750399f,0.235247f,0.116427f}};
 public static void draw(SkinRenderer.Painter d,String name,float[] r,float x,float y,float w,float h){d.image(name,r[0],r[1],r[2],r[3],x,y,w,h);}
 public static void fit(SkinRenderer.Painter d,String name,float[] r,float x,float y,float w,float h){float scale=Math.min(w/r[2],h/r[3]),dw=r[2]*scale,dh=r[3]*scale;draw(d,name,r,x+(w-dw)/2,y+h-dh,dw,dh);}
 public static void prop(SkinRenderer.Painter d,int i,float x,float y,float w,float h){fit(d,"paper_props",PROPS[i],x,y,w,h);}
 public static void pet(SkinRenderer.Painter d,int pose,float x,float y,float w,float h){fit(d,pose<0?"paper_pet":"paper_states",pose<0?PET[0]:STATES[pose],x,y,w,h);}
}
