package com.example.api_fin.expenses.model;

public enum Category {
    FIXED_EXPENSE("Gasto fijo"),
    FOOD("Comida"),
    CAR("Coche"),
    HEALTH("Salud"),
    GIFT("Regalo"),
    TRAVEL("Viajes"),
    UNEXPECTED("Imprevistos"),
    LEISURE("Ocio"),
    OTHER("Otros");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
