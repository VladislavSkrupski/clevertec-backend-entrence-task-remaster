package ru.clevertec.backendtest.models.product.factories;

import org.junit.jupiter.api.Test;
import ru.clevertec.backendtest.models.product.LiquidProduct;
import ru.clevertec.backendtest.models.product.Product;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LiquidProductFactoryTest {

    @Test
    void createProductShouldBeTrueWhenProductIsLiquidProduct() {
        Product product = new LiquidProductFactory().createProduct(2134, "liquid", 1.0, false);

        assertTrue(product instanceof LiquidProduct);
    }
}