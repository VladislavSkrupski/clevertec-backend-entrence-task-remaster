package ru.clevertec.backendtest.models.product.factories;

import org.junit.jupiter.api.Test;
import ru.clevertec.backendtest.models.product.PieceProduct;
import ru.clevertec.backendtest.models.product.Product;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PieceProductFactoryTest {

    @Test
    void createProductShouldBeTrueWhenProductIsPieceProduct() {
        Product product = new PieceProductFactory().createProduct(223, "piece", 21, true);

        assertTrue(product instanceof PieceProduct);
    }
}