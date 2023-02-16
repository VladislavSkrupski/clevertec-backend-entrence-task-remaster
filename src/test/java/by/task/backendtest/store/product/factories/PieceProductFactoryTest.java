package by.task.backendtest.store.product.factories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.task.backendtest.store.product.Product;
import org.junit.jupiter.api.Test;

class PieceProductFactoryTest {
    /**
     * Method under test: {@link PieceProductFactory#createProduct(int, String, double, boolean)}
     */
    @Test
    void testCreateProduct() {
        Product actualCreateProductResult = (new PieceProductFactory()).createProduct(1, "Name", 10.0d, true);
        assertEquals(1, actualCreateProductResult.getId());
        assertTrue(actualCreateProductResult.isPromotional());
        assertEquals(10.0d, actualCreateProductResult.getPrice());
        assertEquals("Name", actualCreateProductResult.getName());
    }
}

