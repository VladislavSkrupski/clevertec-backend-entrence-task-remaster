package by.task.backendtest.store.receipt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import by.task.backendtest.InputArgsForReceipt;
import by.task.backendtest.service.DiscountCardService;
import by.task.backendtest.service.ProductService;
import by.task.backendtest.store.discountCard.DiscountCard;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReceiptDirector.class, InputArgsForReceipt.class})
@ExtendWith(SpringExtension.class)
class ReceiptDirectorTest {
    @MockBean
    private DiscountCardService discountCardService;

    @Autowired
    private InputArgsForReceipt inputArgsForReceipt;

    @MockBean
    private ProductService productService;

    @Autowired
    private ReceiptDirector receiptDirector;

    /**
     * Method under test: {@link ReceiptDirector#buildReceipt(Builder, InputArgsForReceipt)}
     */
    @Test
    void testBuildReceipt() {
        DiscountCard discountCard = new DiscountCard(1, 3);

        when(discountCardService.getById(anyInt())).thenReturn(discountCard);
        ReceiptBuilder builder = new ReceiptBuilder();
        Receipt actualBuildReceiptResult = receiptDirector.buildReceipt(builder, inputArgsForReceipt);
        assertEquals(Builder.CASHIER_ID, actualBuildReceiptResult.getCashierID());
        assertTrue(actualBuildReceiptResult.hasDiscount());
        assertEquals(0.0d, actualBuildReceiptResult.getTotalCostWithDiscount());
        assertEquals(0.0d, actualBuildReceiptResult.getTotalCost());
        assertEquals(Builder.STORE_ID, actualBuildReceiptResult.getStoreId());
        assertEquals(Builder.STORE_ADDRESS, actualBuildReceiptResult.getStoreAddress());
        assertTrue(actualBuildReceiptResult.getItems().isEmpty());
        assertSame(discountCard, actualBuildReceiptResult.getDiscountCard());
        verify(discountCardService).getById(anyInt());
    }

    /**
     * Method under test: {@link ReceiptDirector#buildReceipt(Builder, InputArgsForReceipt)}
     */
    @Test
    void testBuildReceipt2() {
        when(discountCardService.getById(anyInt())).thenReturn(null);
        ReceiptBuilder builder = new ReceiptBuilder();
        Receipt actualBuildReceiptResult = receiptDirector.buildReceipt(builder, inputArgsForReceipt);
        assertEquals(Builder.CASHIER_ID, actualBuildReceiptResult.getCashierID());
        assertFalse(actualBuildReceiptResult.hasDiscount());
        assertEquals(0.0d, actualBuildReceiptResult.getTotalCostWithDiscount());
        assertEquals(0.0d, actualBuildReceiptResult.getTotalCost());
        assertEquals(Builder.STORE_ID, actualBuildReceiptResult.getStoreId());
        assertEquals(Builder.STORE_ADDRESS, actualBuildReceiptResult.getStoreAddress());
        assertTrue(actualBuildReceiptResult.getItems().isEmpty());
        assertNull(actualBuildReceiptResult.getDiscountCard());
        verify(discountCardService).getById(anyInt());
    }

    /**
     * Method under test: {@link ReceiptDirector#buildReceipt(Builder, InputArgsForReceipt)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testBuildReceipt3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "by.task.backendtest.store.receipt.Builder.setCashierId()" because "builder" is null
        //       at by.task.backendtest.store.receipt.ReceiptDirector.buildReceipt(ReceiptDirector.java:28)
        //   See https://diff.blue/R013 to resolve this issue.

        when(discountCardService.getById(anyInt())).thenReturn(new DiscountCard(1, 3));
        receiptDirector.buildReceipt(null, inputArgsForReceipt);
    }

    /**
     * Method under test: {@link ReceiptDirector#buildReceipt(Builder, InputArgsForReceipt)}
     */
    @Test
    void testBuildReceipt4() {
        DiscountCard discountCard = new DiscountCard(1, 3);

        when(discountCardService.getById(anyInt())).thenReturn(discountCard);
        ReceiptBuilder receiptBuilder = mock(ReceiptBuilder.class);
        when(receiptBuilder.setCashierId()).thenReturn(new ReceiptBuilder());
        Receipt actualBuildReceiptResult = receiptDirector.buildReceipt(receiptBuilder, inputArgsForReceipt);
        assertNull(actualBuildReceiptResult.getCashierID());
        assertTrue(actualBuildReceiptResult.hasDiscount());
        assertEquals(0.0d, actualBuildReceiptResult.getTotalCostWithDiscount());
        assertEquals(0.0d, actualBuildReceiptResult.getTotalCost());
        assertEquals(Builder.STORE_ID, actualBuildReceiptResult.getStoreId());
        assertEquals(Builder.STORE_ADDRESS, actualBuildReceiptResult.getStoreAddress());
        assertTrue(actualBuildReceiptResult.getItems().isEmpty());
        assertSame(discountCard, actualBuildReceiptResult.getDiscountCard());
        verify(discountCardService).getById(anyInt());
        verify(receiptBuilder).setCashierId();
    }

    /**
     * Method under test: {@link ReceiptDirector#buildReceipt(Builder, InputArgsForReceipt)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testBuildReceipt5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "by.task.backendtest.store.receipt.ReceiptBuilder.setStoreId()" because the return value of "by.task.backendtest.store.receipt.Builder.setCashierId()" is null
        //       at by.task.backendtest.store.receipt.ReceiptDirector.buildReceipt(ReceiptDirector.java:29)
        //   See https://diff.blue/R013 to resolve this issue.

        when(discountCardService.getById(anyInt())).thenReturn(new DiscountCard(1, 3));
        ReceiptBuilder receiptBuilder = mock(ReceiptBuilder.class);
        when(receiptBuilder.setCashierId()).thenReturn(null);
        receiptDirector.buildReceipt(receiptBuilder, inputArgsForReceipt);
    }

    /**
     * Method under test: {@link ReceiptDirector#buildReceipt(Builder, InputArgsForReceipt)}
     */
    @Test
    void testBuildReceipt6() {
        DiscountCard discountCard = new DiscountCard(1, 3);

        when(discountCardService.getById(anyInt())).thenReturn(discountCard);
        ReceiptBuilder receiptBuilder = mock(ReceiptBuilder.class);
        when(receiptBuilder.setStoreId()).thenReturn(new ReceiptBuilder());
        ReceiptBuilder receiptBuilder1 = mock(ReceiptBuilder.class);
        when(receiptBuilder1.setCashierId()).thenReturn(receiptBuilder);
        Receipt actualBuildReceiptResult = receiptDirector.buildReceipt(receiptBuilder1, inputArgsForReceipt);
        assertNull(actualBuildReceiptResult.getCashierID());
        assertTrue(actualBuildReceiptResult.hasDiscount());
        assertEquals(0.0d, actualBuildReceiptResult.getTotalCostWithDiscount());
        assertEquals(0.0d, actualBuildReceiptResult.getTotalCost());
        assertNull(actualBuildReceiptResult.getStoreId());
        assertEquals(Builder.STORE_ADDRESS, actualBuildReceiptResult.getStoreAddress());
        assertTrue(actualBuildReceiptResult.getItems().isEmpty());
        assertSame(discountCard, actualBuildReceiptResult.getDiscountCard());
        verify(discountCardService).getById(anyInt());
        verify(receiptBuilder1).setCashierId();
        verify(receiptBuilder).setStoreId();
    }
}

