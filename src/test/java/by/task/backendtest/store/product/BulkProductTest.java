package by.task.backendtest.store.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BulkProductTest {
    /**
     * Method under test: {@link BulkProduct#BulkProduct(int, String, double, boolean)}
     */
    @Test
    void testConstructor() {
        BulkProduct actualBulkProduct = new BulkProduct(1, "Name", 10.0d, true);

        assertEquals(1, actualBulkProduct.getId());
        assertTrue(actualBulkProduct.isPromotional());
        assertEquals(10.0d, actualBulkProduct.getPrice());
        assertEquals("Name", actualBulkProduct.getName());
    }

    /**
     * Method under test: {@link BulkProduct#getUnit()}
     */
    @Test
    void testGetUnit() {
        assertEquals("кг", (new BulkProduct(1, "Name", 10.0d, true)).getUnit());
    }
}

