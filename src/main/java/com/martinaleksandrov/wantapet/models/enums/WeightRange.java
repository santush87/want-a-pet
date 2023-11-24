package com.martinaleksandrov.wantapet.models.enums;

public enum WeightRange {
    BETWEEN_0KG_AND_5KG("Between 0kg and 5kg"),
    BETWEEN_6KG_AND_10KG("Between 6kg and 10kg"),
    BETWEEN_11KG_AND_25KG("Between 11kg and 25kg"),
    UP_25KG("More than 25kg");

    public final String name;

    private WeightRange(String weight) {
        this.name = weight;
    }

    public String getName() {
        return name;
    }
}
