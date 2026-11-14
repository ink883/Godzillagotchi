package com.nova.godzillagotchi;

/** A short walk, one optional find, and three casts. Times are in foreground milliseconds. */
public final class Harbor {
    static final String[] FISH = {"Salmon", "Tuna", "Sardines"};
    private static final long WALK_MS = 6800;
    private static final long REEL_DELAY_MS = 850;
    private static final float[] TARGETS = {.47f, .66f, .36f};
    private final int firstFish;
    private final long startedAt;
    private boolean crateOpened;
    private long lastCast;
    private int attempts;
    private int catches;
    private String result = "";

    public Harbor(long now, int firstFish) {
        this.startedAt = now;
        this.firstFish = firstFish;
    }

    public float progress(long now) {
        return Math.min(1, Math.max(0, (now - startedAt) / (float) WALK_MS));
    }

    public boolean traveling(long now) { return progress(now) < 1; }
    public boolean crateVisible(long now) { return !crateOpened && progress(now) >= .36f && traveling(now); }
    public boolean finished() { return attempts == TARGETS.length; }
    public int attempts() { return attempts; }
    public float target() { return TARGETS[Math.min(attempts, TARGETS.length - 1)]; }
    public int fish() { return (firstFish + attempts) % FISH.length; }
    public boolean ready(long now) {
        return !traveling(now) && !finished() && (attempts == 0 || now - lastCast >= REEL_DELAY_MS);
    }

    public float marker(long now) {
        long origin = attempts == 0 ? startedAt + WALK_MS : lastCast + REEL_DELAY_MS;
        float phase = Math.max(0, now - origin) % 2400 / 1200f;
        return phase <= 1 ? phase : 2 - phase;
    }

    public boolean inZone(long now) { return Math.abs(marker(now) - target()) <= .13f; }

    public boolean openCrate(long now, Pet pet) {
        if (!crateVisible(now)) return false;
        crateOpened = true;
        pet.storeHarborFish(firstFish);
        return true;
    }

    public boolean catchFish(long now, Pet pet) {
        if (!ready(now)) return false;
        int type = fish();
        if (inZone(now)) {
            catches++;
            pet.storeHarborFish(type);
            result = "Caught " + FISH[type] + "!";
        } else {
            result = "It slipped away!";
        }
        lastCast = now;
        attempts++;
        return true;
    }

    public String status(long now) {
        if (finished()) return result + "  " + catches + "/3 caught.";
        if (traveling(now)) {
            if (crateVisible(now)) return "A crate washed ashore! Tap to open.";
            return crateOpened ? FISH[firstFish] + " found! Packed for home." : "Walking to the harbor...";
        }
        if (!ready(now)) return result;
        return "Press CATCH while the marker is in the zone.";
    }
}
