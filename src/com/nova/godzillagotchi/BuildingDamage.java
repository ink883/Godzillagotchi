package com.nova.godzillagotchi;

/** Material-specific damage for the vinyl and LCD city targets. */
final class BuildingDamage {
    private static final int LCD_ON = 0xffe8f2db;

    static void draw(SkinRenderer.Painter d, SkinRenderer.State s) {
        Rampage r = s.rampage;
        if (r.building >= Rampage.BUILDINGS) return;
        float age = Math.max(0, r.time - r.hitAt);
        float w = 166, h = 253;
        float[] crop = null;
        String atlas = null;
        if (r.landmark > 0) {
            crop = LandmarkAssets.BOUNDS[s.theme][r.landmark - 1];
            atlas = LandmarkAssets.ASSETS[s.theme];
            float aspect = crop[2] / crop[3];
            w = Math.min(204, 253 * aspect);
            h = w / aspect;
        } else if (s.theme == Theme.VINYL) {
            atlas = "vinyl_scenery";
            crop = ThemeAssets.VINYL_SCENERY[0];
            float aspect = crop[2] / crop[3]; // The vinyl atlas is square.
            w = Math.min(w, h * aspect);
            h = w / aspect;
        }
        float x = 527 - w / 2, y = 426 - h;
        if (s.theme == Theme.VINYL) vinyl(d, r, atlas, crop, x, y, w, h, age);
        else lcd(d, r, atlas, crop, x, y, w, h, age);
        d.round(444, 433, 166, 7, 2, 0xff132d3e);
        if (r.hp > 0) d.round(444, 433, 166 * r.hp / 150f, 7, 2, 0xffffb571);
    }

    private static void vinyl(SkinRenderer.Painter d, Rampage r, String atlas, float[] crop,
                              float x, float y, float w, float h, float age) {
        if (r.hp == 0) {
            // Bottom section lands first. Upper sections pop free and tumble out.
            for (int row = 2; row >= 0; row--) {
                float t = Math.max(0, age / 1000f - (2 - row) * .065f);
                float direction = row == 1 ? 1 : -1;
                float dx = direction * t * (row == 2 ? 32 : 94);
                float dy = t * t * 320 - t * (row == 2 ? 12 : 105);
                float top = y + h * row / 3 + dy;
                d.save();
                d.rotate(direction * t * (row == 2 ? 65 : 155), x + w / 2 + dx, top + h / 6);
                d.image(atlas, crop[0], crop[1] + crop[3] * row / 3, crop[2], crop[3] / 3,
                        x + dx, top, w, h / 3);
                d.restore();
            }
            vinylChips(d, x, y, w, h, age, true);
            return;
        }

        float recoil = age < 370 ? (float)Math.sin(age / 47) * (1 - age / 370) : 0;
        float crush = (150 - r.hp) / 150f;
        float squash = (.15f * crush + .12f * Math.max(0, recoil)) * h;
        float spread = .30f * squash;
        d.save();
        d.rotate(14 * recoil + 9 * crush, x + w / 2, y + h);
        if (crush == 0) {
            d.image(atlas, crop[0], crop[1], crop[2], crop[3], x, y, w, h);
        } else {
            // The lower shell stays planted while the upper shell buckles at its seam.
            float waist = .60f;
            float shift = 12 * crush + 7 * recoil;
            float upperH = h * waist - squash;
            d.image(atlas, crop[0], crop[1] + crop[3] * waist, crop[2], crop[3] * (1 - waist),
                    x, y + h * waist, w, h * (1 - waist));
            d.image(atlas, crop[0], crop[1], crop[2], crop[3] * waist,
                    x + shift - spread / 2, y + h * waist - upperH - 2, w + spread, upperH);
        }
        d.restore();
        vinylChips(d, x, y, w, h, age, false);
    }

    private static void vinylChips(SkinRenderer.Painter d, float x, float y, float w, float h,
                                   float age, boolean collapsing) {
        if (age >= 520) return;
        float t = age / 520;
        int count = collapsing ? 8 : 4;
        for (int i = 0; i < count; i++) {
            float px = x + w * (.27f + .12f * (i % 4)) + (i - count / 2f) * t * 29;
            float py = y + h * .65f - t * (42 + 16 * (i % 3)) + t * t * 68;
            float size = (6 + i % 3 * 2) * (1 - .65f * t);
            d.round(px, py, size, size * .7f, 2, BattleAnimation.fade(0xffffc284, 1 - t));
            d.round(px, py, size * .66f, 2, 1, BattleAnimation.fade(0xffffedcc, 1 - t));
        }
    }

    private static void lcd(SkinRenderer.Painter d, Rampage r, String atlas, float[] crop,
                            float x, float y, float w, float h, float age) {
        boolean collapse = r.hp == 0;
        if (r.hp == 150) {
            wholeLcd(d, atlas, crop, x, y, w, h);
            return;
        }
        int missing = Math.max(1, (150 - r.hp) * 23 / 150);
        int blink = (int)(age / 90);
        int cols = 5, rows = 8;
        float cw = w / cols, ch = h / rows;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int rank = (row * cols + col) * 17 % (cols * rows);
                float left = x + col * cw, top = y + row * ch;
                if (collapse) {
                    if (age >= (row * 95 + (rank % 4) * 55)) continue;
                    if (blink % 2 == 1 && rank % 3 == 0) continue;
                } else if (rank < missing || (blink < 4 && blink % 2 == 0 && rank % 4 == 0)) {
                    continue;
                }
                if (atlas == null) {
                    d.save();
                    d.clipRound(left, top - (row == 0 ? 5 : 0), cw, ch + (row == 0 ? 5 : 0), 0);
                    wholeLcd(d, null, null, x, y, w, h);
                    d.restore();
                } else {
                    d.image(atlas, crop[0] + col * crop[2] / cols, crop[1] + row * crop[3] / rows,
                            crop[2] / cols, crop[3] / rows, left, top, cw, ch);
                }
            }
        }
        int frame = (int)(age / 120);
        if (frame < (collapse ? 8 : 4)) {
            float reach = 12 + frame * 9;
            float midX = x + w * .5f, midY = y + h * .62f;
            d.rect(midX - reach, midY, 8, 8, LCD_ON);
            d.rect(midX + reach, midY, 8, 8, LCD_ON);
            d.rect(midX, midY - reach, 8, 8, LCD_ON);
            d.rect(midX, midY + reach, 8, 8, LCD_ON);
        }
    }

    private static void wholeLcd(SkinRenderer.Painter d, String atlas, float[] crop,
                                 float x, float y, float w, float h) {
        if (atlas != null) {
            d.image(atlas, crop[0], crop[1], crop[2], crop[3], x, y, w, h);
            return;
        }
        // ThemePainter maps light paint to lit LCD ink and dark paint to the substrate.
        d.rect(x, y, w, h, LCD_ON);
        d.rect(x + w * .38f, y - 5, w * .24f, 5, LCD_ON);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 5; col++) {
                d.rect(x + w * (.065f + col * .18f), y + h * (.035f + row * .125f),
                        w * .09f, h * .055f, 0xff203330);
            }
        }
    }
}
