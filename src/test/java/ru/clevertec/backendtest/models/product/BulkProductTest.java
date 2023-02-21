package ru.clevertec.backendtest.models.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class BulkProductTest {

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, 0, 1, Integer.MAX_VALUE})
    void getIdShouldReturnCorrectId(int id) {
        Product bulkProduct = new BulkProduct(id, "Test", 0, false);

        assertThat(bulkProduct.getId()).isEqualTo(id);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "Test", "TestProduct", "TestProduct ", "TestProduct  "})
    void getNameShouldReturnCorrectName(String name) {
        Product bulkProduct = new BulkProduct(0, name, 0, false);

        assertThat(bulkProduct.getName()).isEqualTo(name);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.MIN_VALUE, 0, 10.0f, Double.MAX_VALUE})
    void getPriceShouldReturnCorrectPrice(double price) {
        Product bulkProduct = new BulkProduct(0, "Test", price, false);

        assertThat(bulkProduct.getPrice()).isEqualTo(price);
    }

    @Test
    void getUnitShouldReturnCorrectUnit() {
        Product bulkProduct = new BulkProduct(0, "Test", 0, false);

        assertThat(bulkProduct.getUnit()).isEqualTo(Units.BULK.getUnit());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void isPromotionalShouldReturnCorrectPromotional(boolean promotional) {
        Product bulkProduct = new BulkProduct(0, "Test", 0, promotional);

        assertThat(bulkProduct.isPromotional()).isEqualTo(promotional);
    }
}