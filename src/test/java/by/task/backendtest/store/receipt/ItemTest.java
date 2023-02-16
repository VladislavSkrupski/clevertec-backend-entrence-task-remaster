package by.task.backendtest.store.receipt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.task.backendtest.store.product.BulkProduct;
import by.task.backendtest.store.product.Goods;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ItemTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Item#Item(Goods, int)}
     *   <li>{@link Item#getAmount()}
     *   <li>{@link Item#getCost()}
     *   <li>{@link Item#getPromotionAmount()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Item actualItem = new Item(new BulkProduct(1, "Name", 10.0d, true), 10);

        assertEquals(10.0d, actualItem.getAmount());
        assertEquals(90.0d, actualItem.getCost());
        assertEquals(5, actualItem.getPromotionAmount());
    }

    /**
     * Method under test: {@link Item#Item(Goods, int)}
     */
    @Test
    void testConstructor2() {
        Item actualItem = new Item(new BulkProduct(1, "Name", 10.0d, true), 10);

        assertEquals(10.0d, actualItem.getAmount());
        assertTrue(actualItem.isPromotional());
        assertEquals(90.0d, actualItem.getCost());
    }

    /**
     * Method under test: {@link Item#Item(Goods, int)}
     */
    @Test
    void testConstructor3() {
        Item actualItem = new Item(new BulkProduct(1, "Name", 10.0d, true), 1);

        assertEquals(1.0d, actualItem.getAmount());
        assertTrue(actualItem.isPromotional());
        assertEquals(10.0d, actualItem.getCost());
    }

    /**
     * Method under test: {@link Item#Item(Goods, int)}
     */
    @Test
    void testConstructor4() {
        Item actualItem = new Item(new BulkProduct(1, "Name", 10.0d, false), 10);

        assertEquals(10.0d, actualItem.getAmount());
        assertFalse(actualItem.isPromotional());
        assertEquals(100.0d, actualItem.getCost());
    }

    /**
     * Method under test: {@link Item#Item(Goods, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "by.task.backendtest.store.product.Goods.isPromotional()" because "this.wrappedProduct" is null
        //       at by.task.backendtest.store.receipt.Item.setCost(Item.java:60)
        //       at by.task.backendtest.store.receipt.Item.<init>(Item.java:15)
        //   See https://diff.blue/R013 to resolve this issue.

        new Item(null, 10);

    }

    /**
     * Method under test: {@link Item#Item(Goods, int)}
     */
    @Test
    void testConstructor6() {
        Item actualItem = new Item(new Item(new BulkProduct(1, "Name", 5.0d, true), 10), 10);

        assertEquals(10.0d, actualItem.getAmount());
        assertTrue(actualItem.isPromotional());
        assertEquals(45.0d, actualItem.getCost());
    }

    /**
     * Method under test: {@link Item#Item(Goods, int)}
     */
    @Test
    void testConstructor7() {
        Item actualItem = new Item(new Item(new Item(new BulkProduct(1, "Name", 5.0d, true), 10), 10), 10);

        assertEquals(10.0d, actualItem.getAmount());
        assertTrue(actualItem.isPromotional());
        assertEquals(45.0d, actualItem.getCost());
    }

    /**
     * Method under test: {@link Item#getId()}
     */
    @Test
    void testGetId() {
        assertEquals(1, (new Item(new BulkProduct(1, "Name", 10.0d, true), 10)).getId());
        assertEquals(1, (new Item(new Item(new BulkProduct(1, "Name", 10.0d, true), 10), 10)).getId());
    }

    /**
     * Method under test: {@link Item#getName()}
     */
    @Test
    void testGetName() {
        assertEquals("Name", (new Item(new BulkProduct(1, "Name", 10.0d, true), 10)).getName());
        assertEquals("Name", (new Item(new Item(new BulkProduct(1, "Name", 10.0d, true), 10), 10)).getName());
    }

    /**
     * Method under test: {@link Item#getPrice()}
     */
    @Test
    void testGetPrice() {
        assertEquals(10.0d, (new Item(new BulkProduct(1, "Name", 10.0d, true), 10)).getPrice());
        assertEquals(10.0d, (new Item(new Item(new BulkProduct(1, "Name", 10.0d, true), 10), 10)).getPrice());
    }

    /**
     * Method under test: {@link Item#isPromotional()}
     */
    @Test
    void testIsPromotional() {
        assertTrue((new Item(new BulkProduct(1, "Name", 10.0d, true), 10)).isPromotional());
        assertFalse((new Item(new BulkProduct(1, "Name", 10.0d, false), 10)).isPromotional());
        assertTrue((new Item(new Item(new BulkProduct(1, "Name", 10.0d, true), 10), 10)).isPromotional());
    }

    /**
     * Method under test: {@link Item#getUnit()}
     */
    @Test
    void testGetUnit() {
        assertEquals("кг", (new Item(new BulkProduct(1, "Name", 10.0d, true), 10)).getUnit());
        assertEquals("кг", (new Item(new Item(new BulkProduct(1, "Name", 10.0d, true), 10), 10)).getUnit());
    }

    /**
     * Method under test: {@link Item#getPromotionDiscount()}
     */
    @Test
    void testGetPromotionDiscount() {
        assertEquals(10, (new Item(new BulkProduct(1, "Name", 10.0d, true), 10)).getPromotionDiscount());
    }
}

