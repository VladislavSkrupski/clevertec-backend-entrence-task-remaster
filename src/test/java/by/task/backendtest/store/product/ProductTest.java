package by.task.backendtest.store.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ProductTest {
    /**
     * Method under test: {@link Product#getId()}
     */
    @Test
    void testGetId() {
        assertEquals(1, (new BulkProduct(1, "Name", 10.0d, true)).getId());
    }

    /**
     * Method under test: {@link Product#getName()}
     */
    @Test
    void testGetName() {
        assertEquals("Name", (new BulkProduct(1, "Name", 10.0d, true)).getName());
    }

    /**
     * Method under test: {@link Product#getPrice()}
     */
    @Test
    void testGetPrice() {
        assertEquals(10.0d, (new BulkProduct(1, "Name", 10.0d, true)).getPrice());
    }

    /**
     * Method under test: {@link Product#isPromotional()}
     */
    @Test
    void testIsPromotional() {
        assertTrue((new BulkProduct(1, "Name", 10.0d, true)).isPromotional());
        assertFalse((new BulkProduct(1, "Name", 10.0d, false)).isPromotional());
    }
}

