package ru.clevertec.backendtest.models.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class LiquidProductTest {

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, 0, 1, Integer.MAX_VALUE})
    void getIdShouldReturnCorrectId(int id) {
        Product liquidProduct = new LiquidProduct(id, "Test", 0, false);

        assertThat(liquidProduct.getId()).isEqualTo(id);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "Test", "TestProduct", "TestProduct ", "TestProduct  "})
    void getNameShouldReturnCorrectName(String name) {
        Product liquidProduct = new LiquidProduct(0, name, 0, false);

        assertThat(liquidProduct.getName()).isEqualTo(name);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.MIN_VALUE, 0, 10.0f, Double.MAX_VALUE})
    void getPriceShouldReturnCorrectPrice(double price) {
        Product liquidProduct = new LiquidProduct(0, "Test", price, false);

        assertThat(liquidProduct.getPrice()).isEqualTo(price);
    }

    @Test
    void getUnitShouldReturnCorrectUnit() {
        Product liquidProduct = new LiquidProduct(0, "Test", 0, false);

        assertThat(liquidProduct.getUnit()).isEqualTo(Units.LIQUID.getUnit());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void isPromotionalShouldReturnCorrectPromotional(boolean promotional) {
        Product liquidProduct = new LiquidProduct(0, "Test", 0, promotional);

        assertThat(liquidProduct.isPromotional()).isEqualTo(promotional);
    }
}