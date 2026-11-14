package com.nova.godzillagotchi;

/** Completed outings shape a preference; requests are brief and optional. */
public final class Outings {
    public static final int NONE = -1, HARBOR = 0, CITY = 1, BATTLE = 2;
    static final String[] NAMES = {"Harbor fishing", "City stomp", "Monster battle"};
    static final String[] REQUESTS = {"Let's go fishing!", "Let's go downtown!", "Let's fight a monster!"};
    private static final long INTERVAL = 10 * 60_000L;
    private static final long WINDOW = 2 * 60_000L;
    final int[] completed = new int[3];
    long firstRequestAt;
    int accepted = NONE;
    boolean bonusPending;

    public int favorite() {
        int best = NONE;
        for (int i = 0; i < completed.length; i++) {
            if (completed[i] >= 3 && (best == NONE || completed[i] > completed[best])) best = i;
        }
        if (best == NONE) return NONE;
        for (int i = 0; i < completed.length; i++) {
            if (i != best && completed[i] == completed[best]) return NONE;
        }
        return best;
    }

    public int request(Pet pet, long now) {
        if (!pet.hatched || pet.asleep || pet.sick || pet.lightsOff || pet.food < 35 ||
                pet.clean < 35 || pet.energy < 35 || pet.messes > 0) return NONE;
        if (firstRequestAt <= 0 || now < firstRequestAt || (now - firstRequestAt) % INTERVAL >= WINDOW) return NONE;
        return favorite();
    }

    void start(int type, boolean requested, long now) {
        bonusPending = false;
        accepted = requested ? type : NONE;
        if (requested) firstRequestAt = now + INTERVAL;
    }

    void finish(int type, Pet pet, long now) {
        if (completed[type] < Integer.MAX_VALUE) completed[type]++;
        if (firstRequestAt == 0 && favorite() != NONE) firstRequestAt = now + 30_000;
        bonusPending = accepted == type;
        accepted = NONE;
        if (bonusPending) pet.joy = Pet.clip(pet.joy + 5);
    }
}
