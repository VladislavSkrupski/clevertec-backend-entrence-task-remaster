package by.task.backendtest.store.receipt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.task.backendtest.store.discountCard.DiscountCard;
import by.task.backendtest.store.product.BulkProduct;
import by.task.backendtest.store.product.Goods;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class ReceiptBuilderTest {
    /**
     * Method under test: {@link ReceiptBuilder#setDate()}
     */
    @Test
    void testSetDate() {
        ReceiptBuilder receiptBuilder = new ReceiptBuilder();
        assertSame(receiptBuilder, receiptBuilder.setDate());
    }

    /**
     * Method under test: {@link ReceiptBuilder#setItems(List)}
     */
    @Test
    void testSetItems() {
        ReceiptBuilder receiptBuilder = new ReceiptBuilder();
        assertSame(receiptBuilder, receiptBuilder.setItems(new ArrayList<>()));
    }

    /**
     * Method under test: {@link ReceiptBuilder#setDiscountCard(DiscountCard)}
     */
    @Test
    void testSetDiscountCard() {
        ReceiptBuilder receiptBuilder = new ReceiptBuilder();
        assertSame(receiptBuilder, receiptBuilder.setDiscountCard(new DiscountCard(1, 3)));
    }

    /**
     * Method under test: {@link ReceiptBuilder#setDiscountCard(DiscountCard)}
     */
    @Test
    void testSetDiscountCard2() {
        ReceiptBuilder receiptBuilder = new ReceiptBuilder();
        assertSame(receiptBuilder, receiptBuilder.setDiscountCard(null));
    }

    /**
     * Method under test: {@link ReceiptBuilder#setTotalCost()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetTotalCost() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Iterable.iterator()" because "iterable" is null
        //       at by.task.backendtest.store.receipt.ReceiptBuilder.setTotalCost(ReceiptBuilder.java:65)
        //   See https://diff.blue/R013 to resolve this issue.

        (new ReceiptBuilder()).setTotalCost();
    }

    /**
     * Method under test: {@link ReceiptBuilder#setTotalCost()}
     */
    @Test
    void testSetTotalCost2() {
        ReceiptBuilder receiptBuilder = new ReceiptBuilder();
        receiptBuilder.setItems(new ArrayList<>());
        assertSame(receiptBuilder, receiptBuilder.setTotalCost());
    }

    /**
     * Method under test: {@link ReceiptBuilder#setTotalCost()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetTotalCost3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "by.task.backendtest.store.receipt.Item.getCost()" because "item" is null
        //       at by.task.backendtest.store.receipt.ReceiptBuilder.setTotalCost(ReceiptBuilder.java:66)
        //   See https://diff.blue/R013 to resolve this issue.

        ArrayList<Goods> goodsList = new ArrayList<>();
        goodsList.add(null);

        ReceiptBuilder receiptBuilder = new ReceiptBuilder();
        receiptBuilder.setItems(goodsList);
        receiptBuilder.setTotalCost();
    }

    /**
     * Method under test: {@link ReceiptBuilder#setTotalCost()}
     */
    @Test
    void testSetTotalCost4() {
        ArrayList<Goods> goodsList = new ArrayList<>();
        goodsList.add(new Item(new BulkProduct(1, "Name", 10.0d, true), 10));

        ReceiptBuilder receiptBuilder = new ReceiptBuilder();
        receiptBuilder.setItems(goodsList);
        assertSame(receiptBuilder, receiptBuilder.setTotalCost());
    }

    /**
     * Method under test: {@link ReceiptBuilder#setTotalCostWithDiscount()}
     */
    @Test
    void testSetTotalCostWithDiscount() {
        ReceiptBuilder receiptBuilder = new ReceiptBuilder();
        assertSame(receiptBuilder, receiptBuilder.setTotalCostWithDiscount());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ReceiptBuilder#build()}
     *   <li>default or parameterless constructor of {@link ReceiptBuilder}
     * </ul>
     */
    @Test
    void testBuild() {
        ReceiptBuilder receiptBuilder = new ReceiptBuilder();
        ArrayList<Goods> goodsList = new ArrayList<>();
        Receipt actualBuildResult = receiptBuilder.setItems(goodsList).build();
        assertNull(actualBuildResult.getCashierID());
        assertFalse(actualBuildResult.hasDiscount());
        assertEquals(0.0d, actualBuildResult.getTotalCostWithDiscount());
        assertEquals(0.0d, actualBuildResult.getTotalCost());
        assertNull(actualBuildResult.getStoreId());
        assertNull(actualBuildResult.getStoreAddress());
        List<Goods> items = actualBuildResult.getItems();
        assertEquals(goodsList, items);
        assertTrue(items.isEmpty());
        assertNull(actualBuildResult.getDiscountCard());
        assertNull(actualBuildResult.getDate());
    }
}

