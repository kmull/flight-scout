package pl.kmuller.flight_scout.enums;

import lombok.Getter;

@Getter
public enum CabinClass {

    ECONOMY("Ekonomiczna"),
    PREMIUM_ECONOMY("Ekonomiczny Premium"),
    BUSINESS("Biznesowa"),
    FIRST("Pierwsza klasa");


    private final String description;

    CabinClass(String description) {
        this.description = description;
    }
}
