package ru.clevertec.backendtest.model.product;

public enum Units {
    BULK("кг"),
    LIQUID("л"),
    PIECE("шт.");

    private final String unit;

    Units(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}
