package ru.clevertec.backendtest.model.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Units {
    BULK("кг"),
    LIQUID("л"),
    PIECE("шт.");

    private final String unit;
}
