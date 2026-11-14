package com.nova.godzillagotchi;

final class HarborRenderer {
    static final int CRATE_X = 471, CRATE_Y = 332, CRATE_W = 96, CRATE_H = 68;
    private static final int INK = 0xfff0e8cc;

    static boolean crateHit(float x, float y) {
        return x >= CRATE_X && x <= CRATE_X + CRATE_W && y >= CRATE_Y && y <= CRATE_Y + CRATE_H;
    }

    static void draw(SkinRenderer.Painter d, SkinRenderer.State s) {
        Harbor harbor = s.harbor;
        long now = s.harborNow;
        d.rect(40, 267, 640, 178, s.night ? 0xff1c4250 : 0xff3e8595);
        for (int i = 0; i < 10; i++) {
            float x = 60 + (i * 101 + now / 45) % 577;
            d.round(x, 280 + i % 3 * 30, 32, 3, 2, 0xff7bb2ba);
        }
        d.polygon(new float[]{40,337,361,337,450,445,40,445}, 0xff9e815f);
        d.rect(40, 337, 319, 7, 0xff594739);
        for (int i = 0; i < 6; i++) {
            d.polygon(new float[]{60+i*53,344,65+i*53,344,80+i*59,445,75+i*59,445},0xff695342);
        }

        boolean walking = harbor.traveling(now);
        float size = 185 * s.pet.sizeScale();
        float x = walking ? 62 + 175 * harbor.progress(now) : 237;
        float step = walking ? (float)Math.sin(now / 92.0) : 0;
        d.save();
        d.rotate(step * 5, x + size / 2, 394);
        PaperArt.pet(d, -1, x, 398 - size - Math.abs(step) * 8, size, size);
        d.restore();

        if (harbor.crateVisible(now)) {
            d.round(CRATE_X, CRATE_Y, CRATE_W, CRATE_H, 5, 0xff372a24);
            d.rect(CRATE_X + 5, CRATE_Y + 5, CRATE_W - 10, CRATE_H - 10, 0xffb58a54);
            d.rect(CRATE_X + 5, CRATE_Y + 17, CRATE_W - 10, 4, 0xff775035);
            d.rect(CRATE_X + 5, CRATE_Y + 44, CRATE_W - 10, 4, 0xff775035);
            d.text("?", CRATE_X + 36, CRATE_Y + 44, 31, INK);
        } else if (!walking && !harbor.finished()) {
            d.round(438, 249, 184, 102, 14, 0xff174b63);
            PaperArt.prop(d, harbor.fish(), 451, 256, 158, 87);
        }

        d.rect(40, 445, 640, 57, 0xff082330);
        d.text(harbor.status(now), 75, 465, 15, INK);
        if (harbor.ready(now)) {
            d.round(75, 474, 570, 15, 4, 0xff395960);
            d.rect(75 + (harbor.target() - .13f) * 570, 474, .26f * 570, 15, 0xff9dc873);
            d.rect(75 + harbor.marker(now) * 570 - 3, 472, 6, 19, INK);
        }
    }

    static void buttons(SkinRenderer.Painter d, Harbor harbor, long now) {
        d.rect(0, 527, 720, 193, 0xff06182a);
        if (!harbor.traveling(now) && !harbor.finished()) {
            button(d, 0, "CATCH", harbor.ready(now));
        } else if (harbor.crateVisible(now)) {
            button(d, 0, "OPEN CRATE", true);
        }
        button(d, 1, "RETURN HOME", true);
    }

    private static void button(SkinRenderer.Painter d, int index, String text, boolean enabled) {
        float[] r = SkinLayout.fightButton(index);
        d.round(r[0], r[1], r[2], r[3], 24, enabled ? 0xff38869a : 0xff294b5b);
        d.round(r[0]+3, r[1]+3, r[2]-6, r[3]-6, 21, 0xff104555);
        d.text(text, r[0]+30, r[1]+47, 25, enabled ? INK : 0xff8098a2);
    }
}
