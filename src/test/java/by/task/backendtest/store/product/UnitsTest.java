package by.task.backendtest.store.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UnitsTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Units#valueOf(String)}
     *   <li>{@link Units#getUnit()}
     * </ul>
     */
    @Test
    void testValueOf() {
        assertEquals("кг", Units.valueOf("BULK").getUnit());
    }
}

