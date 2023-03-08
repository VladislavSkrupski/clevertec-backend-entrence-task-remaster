package ru.clevertec.backendtest.model.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class PieceProductTest {

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, 0, 1, Integer.MAX_VALUE})
    void getIdShouldReturnCorrectId(int id) {
        Product pieceProduct = new PieceProduct(id, "Test", 0, false);

        assertThat(pieceProduct.getId()).isEqualTo(id);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "Test", "TestProduct", "TestProduct ", "TestProduct  "})
    void getNameShouldReturnCorrectName(String name) {
        Product pieceProduct = new PieceProduct(0, name, 0, false);

        assertThat(pieceProduct.getName()).isEqualTo(name);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.MIN_VALUE, 0, 10.0f, Double.MAX_VALUE})
    void getPriceShouldReturnCorrectPrice(double price) {
        Product pieceProduct = new PieceProduct(1, "Test", price, false);

        assertThat(pieceProduct.getPrice()).isEqualTo(price);
    }

    @Test
    void getUnitShouldReturnCorrectUnit() {
        Product pieceProduct = new PieceProduct(1, "Test", 0, false);

        assertThat(pieceProduct.getUnit()).isEqualTo(Units.PIECE.getUnit());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void isPromotionalShouldReturnCorrectPromotional(boolean promotional) {
        Product pieceProduct = new PieceProduct(1, "Test", 0, promotional);

        assertThat(pieceProduct.isPromotional()).isEqualTo(promotional);
    }
}