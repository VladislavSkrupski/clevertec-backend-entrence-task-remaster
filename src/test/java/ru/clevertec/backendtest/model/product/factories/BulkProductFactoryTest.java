package ru.clevertec.backendtest.model.product.factories;

import org.junit.jupiter.api.Test;
import ru.clevertec.backendtest.model.product.BulkProduct;
import ru.clevertec.backendtest.model.product.Product;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BulkProductFactoryTest {

    @Test
    void createProductShouldBeTrueWhenProductIsBulkProduct() {
        Product product = new BulkProductFactory().createProduct(1, "bulk", 12, true);

        assertTrue(product instanceof BulkProduct);
    }
}