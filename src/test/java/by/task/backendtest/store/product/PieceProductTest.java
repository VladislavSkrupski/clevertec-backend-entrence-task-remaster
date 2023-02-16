package by.task.backendtest.store.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PieceProductTest {
    /**
     * Method under test: {@link PieceProduct#PieceProduct(int, String, double, boolean)}
     */
    @Test
    void testConstructor() {
        PieceProduct actualPieceProduct = new PieceProduct(1, "Name", 10.0d, true);

        assertEquals(1, actualPieceProduct.getId());
        assertTrue(actualPieceProduct.isPromotional());
        assertEquals(10.0d, actualPieceProduct.getPrice());
        assertEquals("Name", actualPieceProduct.getName());
    }

    /**
     * Method under test: {@link PieceProduct#getUnit()}
     */
    @Test
    void testGetUnit() {
        assertEquals("шт.", (new PieceProduct(1, "Name", 10.0d, true)).getUnit());
    }
}

