package com.martinaleksandrov.wantapet.models.enums;

public enum WeightRange {
    BETWEEN_0KG_AND_5KG("0-5kg"),
    BETWEEN_6KG_AND_10KG("6-10kg"),
    BETWEEN_11KG_AND_25KG("11-25kg"),
    UP_25KG(">25kg");

    private final String name;
    WeightRange(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
