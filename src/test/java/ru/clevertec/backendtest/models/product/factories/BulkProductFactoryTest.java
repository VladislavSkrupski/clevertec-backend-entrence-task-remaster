package ru.clevertec.backendtest.models.product.factories;

import org.junit.jupiter.api.Test;
import ru.clevertec.backendtest.models.product.BulkProduct;
import ru.clevertec.backendtest.models.product.Product;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BulkProductFactoryTest {

    @Test
    void createProductShouldBeTrueWhenProductIsBulkProduct() {
        Product product = new BulkProductFactory().createProduct(1, "bulk", 12, true);

        assertTrue(product instanceof BulkProduct);
    }
}