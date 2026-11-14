package com.nova.godzillagotchi;

/** Removes the faint matte baked into the Vinyl Godzilla atlas at load time. */
public final class VinylAlpha {
    private VinylAlpha() {}

    public static void apply(int[] pixels) {
        // Empty areas have a low alpha haze, while solid parts peak around 252.
        int matte = 56;
        int solid = 248;
        for (int i = 0; i < pixels.length; i++) {
            int argb = pixels[i];
            int alpha = argb >>> 24;
            if (alpha <= matte) {
                pixels[i] = 0;
            } else {
                int corrected = Math.min(255, ((alpha - matte) * 255 + 96) / (solid - matte));
                pixels[i] = (corrected << 24) | (argb & 0x00ffffff);
            }
        }
    }
}
