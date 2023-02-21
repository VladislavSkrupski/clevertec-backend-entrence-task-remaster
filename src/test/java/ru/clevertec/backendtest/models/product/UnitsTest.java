package ru.clevertec.backendtest.models.product;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UnitsTest {
    @ParameterizedTest
    @EnumSource(Units.class)
    void getUnitShouldReturnCorrectUnitValue(Units units) {
        assertTrue(List.of("кг", "л", "шт.").contains(units.getUnit()));
    }
}