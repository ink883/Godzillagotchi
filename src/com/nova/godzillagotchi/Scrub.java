package com.nova.godzillagotchi;

/** Each dirt patch needs a short rubbing motion inside its contact radius. */
public final class Scrub {
    private static final float[][] SPOTS = {
        {.77f,.32f},{.65f,.45f},{.55f,.59f},{.43f,.72f},{.63f,.80f}
    };
    private static final float RUB_DISTANCE = 32;
    final float size;
    private final float[] rubbed = new float[SPOTS.length];
    private final long[] lifted = new long[SPOTS.length];
    int removed;
    float x, y;
    boolean held;
    long lastRub = -1000;

    public Scrub(float size) { this.size = size; }
    float spotX(int i) { return 355 + (SPOTS[i][0] - .5f) * size; }
    float spotY(int i) { return 417 + (SPOTS[i][1] - 1) * size; }
    boolean finished() { return removed == SPOTS.length; }

    void begin(float x, float y) {
        this.x = x;
        this.y = y;
        held = true;
    }

    void move(float toX, float toY, long now) {
        if (!held || finished()) return;
        float dx = toX - x, dy = toY - y;
        float distance = (float)Math.hypot(dx, dy);
        int steps = (int)Math.ceil(distance / 5);
        for (int step = 1; step <= steps; step++) {
            float px = x + dx * step / steps, py = y + dy * step / steps;
            int nearest = -1;
            float best = 27 * 27;
            for (int i = 0; i < SPOTS.length; i++) {
                if (rubbed[i] >= RUB_DISTANCE) continue;
                float sx = px - spotX(i), sy = py - spotY(i);
                float square = sx * sx + sy * sy;
                if (square < best) { best = square; nearest = i; }
            }
            if (nearest < 0) continue;
            lastRub = now;
            rubbed[nearest] += distance / steps;
            if (rubbed[nearest] >= RUB_DISTANCE) {
                lifted[nearest] = now;
                removed++;
            }
        }
        x = toX;
        y = toY;
    }

    void draw(SkinRenderer.Painter d, long now) {
        for (int i = 0; i < SPOTS.length; i++) {
            float sx = spotX(i), sy = spotY(i);
            if (rubbed[i] < RUB_DISTANCE) {
                float scale = 1 - .58f * rubbed[i] / RUB_DISTANCE;
                d.circle(sx, sy, 11 * scale, 0xff937652);
                d.circle(sx - 6 * scale, sy - 5 * scale, 5 * scale, 0xff69533e);
                d.circle(sx + 8 * scale, sy + 1, 6 * scale, 0xffaa926a);
            } else {
                float t = (now - lifted[i]) / 550f;
                if (t < 1) for (int j = 0; j < 5; j++) {
                    d.circle(sx + (j - 2) * t * 22, sy - 24 * t + 34 * t * t + j % 2 * 6,
                            4 * (1 - t), 0xffaa926a);
                }
            }
        }
        if (held && now - lastRub < 220) {
            for (int i = 0; i < 3; i++) {
                float offset = (now / 12 + i * 9) % 27;
                PaperArt.prop(d,5,x-18+i*11,y-10-offset,13,13);
            }
        }
    }

    String status() { return "Rub the muddy spots   " + removed + "/" + SPOTS.length; }
}
