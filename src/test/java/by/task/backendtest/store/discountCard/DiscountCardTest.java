package by.task.backendtest.store.discountCard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DiscountCardTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link DiscountCard#DiscountCard(int, int)}
     *   <li>{@link DiscountCard#getDiscount()}
     *   <li>{@link DiscountCard#getId()}
     * </ul>
     */
    @Test
    void testConstructor() {
        DiscountCard actualDiscountCard = new DiscountCard(1, 3);

        assertEquals(3, actualDiscountCard.getDiscount());
        assertEquals(1, actualDiscountCard.getId());
    }
}

