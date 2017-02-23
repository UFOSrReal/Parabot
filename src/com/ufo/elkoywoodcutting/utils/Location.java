package com.ufo.elkoywoodcutting.utils;

public enum Location {

    DRAYNOR(2495),
    SEERS_VILL(2496),
    STRONG_HOLD(2497),
    ISAFDAR(2498);

    private final int cities;

    public int getCityID() {
        return cities;
    }

    Location(int cities) {
        this.cities = cities;
    }
}