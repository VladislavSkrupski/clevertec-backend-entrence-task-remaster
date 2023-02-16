package by.task.backendtest.store.receipt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.task.backendtest.store.discountCard.DiscountCard;
import by.task.backendtest.store.product.Goods;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class ReceiptTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Receipt#Receipt(String, String, String, GregorianCalendar, List, DiscountCard, double, double)}
     *   <li>{@link Receipt#getCashierID()}
     *   <li>{@link Receipt#getDate()}
     *   <li>{@link Receipt#getDiscountCard()}
     *   <li>{@link Receipt#getItems()}
     *   <li>{@link Receipt#getStoreAddress()}
     *   <li>{@link Receipt#getStoreId()}
     *   <li>{@link Receipt#getTotalCost()}
     *   <li>{@link Receipt#getTotalCostWithDiscount()}
     *   <li>{@link Receipt#hasDiscount()}
     * </ul>
     */
    @Test
    void testConstructor() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(1, 1, 1);

        ArrayList<Goods> goodsList = new ArrayList<>();
        DiscountCard discountCard = new DiscountCard(1, 3);

        Receipt actualReceipt = new Receipt("Cashier ID", "42", "42 Main St", gregorianCalendar, goodsList, discountCard,
                10.0d, 10.0d);

        assertEquals("Cashier ID", actualReceipt.getCashierID());
        assertSame(gregorianCalendar, actualReceipt.getDate());
        assertSame(discountCard, actualReceipt.getDiscountCard());
        assertSame(goodsList, actualReceipt.getItems());
        assertEquals("42 Main St", actualReceipt.getStoreAddress());
        assertEquals("42", actualReceipt.getStoreId());
        assertEquals(10.0d, actualReceipt.getTotalCost());
        assertEquals(10.0d, actualReceipt.getTotalCostWithDiscount());
        assertTrue(actualReceipt.hasDiscount());
    }

    /**
     * Method under test: {@link Receipt#Receipt(String, String, String, GregorianCalendar, List, DiscountCard, double, double)}
     */
    @Test
    void testConstructor2() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(1, 1, 1);

        ArrayList<Goods> items = new ArrayList<>();
        DiscountCard discountCard = new DiscountCard(1, 3);

        Receipt actualReceipt = new Receipt("Cashier ID", "42", "42 Main St", gregorianCalendar, items, discountCard,
                10.0d, 10.0d);

        assertEquals("Cashier ID", actualReceipt.getCashierID());
        assertTrue(actualReceipt.hasDiscount());
        assertEquals(10.0d, actualReceipt.getTotalCostWithDiscount());
        assertEquals(10.0d, actualReceipt.getTotalCost());
        assertEquals("42", actualReceipt.getStoreId());
        assertEquals("42 Main St", actualReceipt.getStoreAddress());
        assertTrue(actualReceipt.getItems().isEmpty());
        assertSame(discountCard, actualReceipt.getDiscountCard());
        assertSame(gregorianCalendar, actualReceipt.getDate());
    }

    /**
     * Method under test: {@link Receipt#Receipt(String, String, String, GregorianCalendar, List, DiscountCard, double, double)}
     */
    @Test
    void testConstructor3() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(1, 1, 1);

        Receipt actualReceipt = new Receipt("Cashier ID", "42", "42 Main St", gregorianCalendar, new ArrayList<>(), null,
                10.0d, 10.0d);

        assertEquals("Cashier ID", actualReceipt.getCashierID());
        assertFalse(actualReceipt.hasDiscount());
        assertEquals(10.0d, actualReceipt.getTotalCostWithDiscount());
        assertEquals(10.0d, actualReceipt.getTotalCost());
        assertEquals("42", actualReceipt.getStoreId());
        assertEquals("42 Main St", actualReceipt.getStoreAddress());
        assertTrue(actualReceipt.getItems().isEmpty());
        assertNull(actualReceipt.getDiscountCard());
        assertSame(gregorianCalendar, actualReceipt.getDate());
    }

    /**
     * Method under test: {@link Receipt#print()}
     */
    @Test
    void testPrint() {
        GregorianCalendar date = new GregorianCalendar(1, 1, 1);

        ArrayList<Goods> items = new ArrayList<>();
        assertEquals(
                "\n" + "                             RECEIPT\n" + "                            Store: 42\n"
                        + "                       Address: 42 Main St\n"
                        + "Cashier: Cashier ID                              Date: 01/02/0001\n"
                        + "                                                   Time: 00:00:00\n"
                        + "-----------------------------------------------------------------\n"
                        + "Name                 Unit       QTY     Price     Promo      Cost\n"
                        + "-----------------------------------------------------------------\n"
                        + "=================================================================\n"
                        + "SUM                                                         10,00\n"
                        + "DISCOUNT                                                       3%\n"
                        + "TOTAL                                                       10,00\n",
                (new Receipt("Cashier ID", "42", "42 Main St", date, items, new DiscountCard(1, 3), 10.0d, 10.0d)).print());
    }

    /**
     * Method under test: {@link Receipt#print()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPrint2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.GregorianCalendar.getTime()" because "this.date" is null
        //       at by.task.backendtest.store.receipt.Receipt.print(Receipt.java:85)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Goods> items = new ArrayList<>();
        (new Receipt("Cashier ID", "42", "42 Main St", null, items, new DiscountCard(1, 3), 10.0d, 10.0d)).print();
    }
}

