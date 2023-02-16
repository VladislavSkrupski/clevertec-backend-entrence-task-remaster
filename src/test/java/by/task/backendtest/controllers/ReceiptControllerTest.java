package by.task.backendtest.controllers;

import by.task.backendtest.InputArgsForReceipt;
import by.task.backendtest.service.ReceiptService;
import by.task.backendtest.store.discountCard.DiscountCard;
import by.task.backendtest.store.product.Goods;
import by.task.backendtest.store.receipt.Receipt;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ReceiptController.class})
@ExtendWith(SpringExtension.class)
class ReceiptControllerTest {

    @MockBean
    private ReceiptService receiptService;

    @Test
    void getReceipt() {

    }

    /**
     * Method under test: {@link ReceiptController#getReceipt(List, List, Integer)}
     */
    @Test
    void testGetReceipt() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ReceiptService receiptService = mock(ReceiptService.class);
        GregorianCalendar date = new GregorianCalendar(1, 1, 1);

        ArrayList<Goods> items = new ArrayList<>();
        when(receiptService.getReceipt((InputArgsForReceipt) any()))
                .thenReturn(new Receipt("Cashier ID", "42", "42 Main St", date, items, new DiscountCard(1, 3), 10.0d, 10.0d));
        ReceiptController receiptController = new ReceiptController(receiptService);
        ArrayList<Integer> ids = new ArrayList<>();
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
                receiptController.getReceipt(ids, new ArrayList<>(), 1));
        verify(receiptService).getReceipt((InputArgsForReceipt) any());
    }

    /**
     * Method under test: {@link ReceiptController#getReceipt(List, List, Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetReceipt2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.GregorianCalendar.getTime()" because "this.date" is null
        //       at by.task.backendtest.store.receipt.Receipt.print(Receipt.java:85)
        //       at by.task.backendtest.controllers.ReceiptController.getReceipt(ReceiptController.java:33)
        //   See https://diff.blue/R013 to resolve this issue.

        ReceiptService receiptService = mock(ReceiptService.class);
        ArrayList<Goods> items = new ArrayList<>();
        when(receiptService.getReceipt((InputArgsForReceipt) any()))
                .thenReturn(new Receipt("Cashier ID", "42", "42 Main St", null, items, new DiscountCard(1, 3), 10.0d, 10.0d));
        ReceiptController receiptController = new ReceiptController(receiptService);
        ArrayList<Integer> ids = new ArrayList<>();
        receiptController.getReceipt(ids, new ArrayList<>(), 1);
    }

    /**
     * Method under test: {@link ReceiptController#getReceipt(List, List, Integer)}
     */
    @Test
    void testGetReceipt3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ReceiptService receiptService = mock(ReceiptService.class);
        GregorianCalendar date = new GregorianCalendar(1, 1, 1);

        when(receiptService.getReceipt((InputArgsForReceipt) any()))
                .thenReturn(new Receipt("Cashier ID", "42", "42 Main St", date, new ArrayList<>(), null, 10.0d, 10.0d));
        ReceiptController receiptController = new ReceiptController(receiptService);
        ArrayList<Integer> ids = new ArrayList<>();
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
                        + "DISCOUNT                                                       0%\n"
                        + "TOTAL                                                       10,00\n",
                receiptController.getReceipt(ids, new ArrayList<>(), 1));
        verify(receiptService).getReceipt((InputArgsForReceipt) any());
    }

    /**
     * Method under test: {@link ReceiptController#getReceipt(List, List, Integer)}
     */
    @Test
    void testGetReceipt4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        Receipt receipt = mock(Receipt.class);
        when(receipt.print()).thenReturn("Print");
        ReceiptService receiptService = mock(ReceiptService.class);
        when(receiptService.getReceipt((InputArgsForReceipt) any())).thenReturn(receipt);
        ReceiptController receiptController = new ReceiptController(receiptService);
        ArrayList<Integer> ids = new ArrayList<>();
        assertEquals("Print", receiptController.getReceipt(ids, new ArrayList<>(), 1));
        verify(receiptService).getReceipt((InputArgsForReceipt) any());
        verify(receipt).print();
    }

    /**
     * Method under test: {@link ReceiptController#getReceipt(List, List, Integer)}
     */
    @Test
    void testGetReceipt5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        Receipt receipt = mock(Receipt.class);
        when(receipt.print()).thenReturn("Print");
        ReceiptService receiptService = mock(ReceiptService.class);
        when(receiptService.getReceipt((InputArgsForReceipt) any())).thenReturn(receipt);
        ReceiptController receiptController = new ReceiptController(receiptService);
        ArrayList<Integer> ids = new ArrayList<>();
        assertEquals("Print", receiptController.getReceipt(ids, new ArrayList<>(), -1));
        verify(receiptService).getReceipt((InputArgsForReceipt) any());
        verify(receipt).print();
    }

    /**
     * Method under test: {@link ReceiptController#getReceipt(List, List, Integer)}
     */
    @Test
    void testGetReceipt6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        Receipt receipt = mock(Receipt.class);
        when(receipt.print()).thenReturn("Print");
        ReceiptService receiptService = mock(ReceiptService.class);
        when(receiptService.getReceipt((InputArgsForReceipt) any())).thenReturn(receipt);
        ReceiptController receiptController = new ReceiptController(receiptService);

        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(2);

        ArrayList<Integer> integerList1 = new ArrayList<>();
        integerList1.add(2);
        assertEquals("Print", receiptController.getReceipt(integerList, integerList1, -1));
        verify(receiptService).getReceipt((InputArgsForReceipt) any());
        verify(receipt).print();
    }

    /**
     * Method under test: {@link ReceiptController#getReceiptView(List, List, Integer)}
     */
    @Test
    void testGetReceiptView() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ReceiptService receiptService = mock(ReceiptService.class);
        GregorianCalendar date = new GregorianCalendar(1, 1, 1);

        ArrayList<Goods> items = new ArrayList<>();
        Receipt receipt = new Receipt("Cashier ID", "42", "42 Main St", date, items, new DiscountCard(1, 3), 10.0d,
                10.0d);

        when(receiptService.getReceipt((InputArgsForReceipt) any())).thenReturn(receipt);
        ReceiptController receiptController = new ReceiptController(receiptService);
        ArrayList<Integer> ids = new ArrayList<>();
        assertSame(receipt, receiptController.getReceiptView(ids, new ArrayList<>(), 1));
        verify(receiptService).getReceipt((InputArgsForReceipt) any());
    }

    /**
     * Method under test: {@link ReceiptController#getReceiptView(List, List, Integer)}
     */
    @Test
    void testGetReceiptView2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ReceiptService receiptService = mock(ReceiptService.class);
        GregorianCalendar date = new GregorianCalendar(1, 1, 1);

        ArrayList<Goods> items = new ArrayList<>();
        Receipt receipt = new Receipt("Cashier ID", "42", "42 Main St", date, items, new DiscountCard(1, 3), 10.0d,
                10.0d);

        when(receiptService.getReceipt((InputArgsForReceipt) any())).thenReturn(receipt);
        ReceiptController receiptController = new ReceiptController(receiptService);
        ArrayList<Integer> ids = new ArrayList<>();
        assertSame(receipt, receiptController.getReceiptView(ids, new ArrayList<>(), -1));
        verify(receiptService).getReceipt((InputArgsForReceipt) any());
    }

    /**
     * Method under test: {@link ReceiptController#getReceiptView(List, List, Integer)}
     */
    @Test
    void testGetReceiptView3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ReceiptService receiptService = mock(ReceiptService.class);
        GregorianCalendar date = new GregorianCalendar(1, 1, 1);

        ArrayList<Goods> items = new ArrayList<>();
        Receipt receipt = new Receipt("Cashier ID", "42", "42 Main St", date, items, new DiscountCard(1, 3), 10.0d,
                10.0d);

        when(receiptService.getReceipt((InputArgsForReceipt) any())).thenReturn(receipt);
        ReceiptController receiptController = new ReceiptController(receiptService);

        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);

        ArrayList<Integer> integerList1 = new ArrayList<>();
        integerList1.add(1);
        integerList1.add(1);
        integerList1.add(1);
        integerList1.add(1);
        integerList1.add(1);
        integerList1.add(1);
        integerList1.add(1);
        integerList1.add(1);
        integerList1.add(1);
        assertSame(receipt, receiptController.getReceiptView(integerList, integerList1, 1));
        verify(receiptService).getReceipt((InputArgsForReceipt) any());
    }

    /**
     * Method under test: {@link ReceiptController#getReceiptView(List, List, Integer)}
     */
    @Test
    void testGetReceiptView4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        ReceiptService receiptService = mock(ReceiptService.class);
        GregorianCalendar date = new GregorianCalendar(1, 1, 1);

        ArrayList<Goods> items = new ArrayList<>();
        Receipt receipt = new Receipt("Cashier ID", "42", "42 Main St", date, items, new DiscountCard(1, 3), 10.0d,
                10.0d);

        when(receiptService.getReceipt((InputArgsForReceipt) any())).thenReturn(receipt);
        ReceiptController receiptController = new ReceiptController(receiptService);

        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(2);
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);

        ArrayList<Integer> integerList1 = new ArrayList<>();
        integerList1.add(1);
        integerList1.add(1);
        integerList1.add(1);
        integerList1.add(1);
        integerList1.add(1);
        integerList1.add(1);
        integerList1.add(1);
        integerList1.add(1);
        integerList1.add(1);
        assertSame(receipt, receiptController.getReceiptView(integerList, integerList1, 1));
        verify(receiptService).getReceipt((InputArgsForReceipt) any());
    }

    /**
     * Method under test: {@link ReceiptController#getInputArgsForReceipt(List, List, int)}
     */
    @Test
    void testGetInputArgsForReceipt() {
        ArrayList<Integer> ids = new ArrayList<>();
        InputArgsForReceipt actualInputArgsForReceipt = ReceiptController.getInputArgsForReceipt(ids, new ArrayList<>(),
                1);
        assertEquals(1, actualInputArgsForReceipt.getDiscountCardId());
        assertTrue(actualInputArgsForReceipt.getProductAmountMap().isEmpty());
    }

    /**
     * Method under test: {@link ReceiptController#getInputArgsForReceipt(List, List, int)}
     */
    @Test
    void testGetInputArgsForReceipt2() {
        ArrayList<Integer> ids = new ArrayList<>();
        InputArgsForReceipt actualInputArgsForReceipt = ReceiptController.getInputArgsForReceipt(ids, new ArrayList<>(),
                -1);
        assertEquals(-1, actualInputArgsForReceipt.getDiscountCardId());
        assertTrue(actualInputArgsForReceipt.getProductAmountMap().isEmpty());
    }

    /**
     * Method under test: {@link ReceiptController#getInputArgsForReceipt(List, List, int)}
     */
    @Test
    void testGetInputArgsForReceipt3() {
        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(2);

        ArrayList<Integer> integerList1 = new ArrayList<>();
        integerList1.add(2);
        InputArgsForReceipt actualInputArgsForReceipt = ReceiptController.getInputArgsForReceipt(integerList,
                integerList1, 1);
        assertEquals(1, actualInputArgsForReceipt.getDiscountCardId());
        assertEquals(1, actualInputArgsForReceipt.getProductAmountMap().size());
    }


}