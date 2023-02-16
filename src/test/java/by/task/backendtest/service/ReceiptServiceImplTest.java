package by.task.backendtest.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import by.task.backendtest.InputArgsForReceipt;
import by.task.backendtest.store.discountCard.DiscountCard;
import by.task.backendtest.store.product.Goods;
import by.task.backendtest.store.receipt.Builder;
import by.task.backendtest.store.receipt.Receipt;
import by.task.backendtest.store.receipt.ReceiptDirector;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReceiptServiceImpl.class, InputArgsForReceipt.class})
@ExtendWith(SpringExtension.class)
class ReceiptServiceImplTest {
    @Autowired
    private InputArgsForReceipt inputArgsForReceipt;

    @MockBean
    private ReceiptDirector receiptDirector;

    @Autowired
    private ReceiptServiceImpl receiptServiceImpl;

    /**
     * Method under test: {@link ReceiptServiceImpl#getReceipt(InputArgsForReceipt)}
     */
    @Test
    void testGetReceipt() {
        GregorianCalendar date = new GregorianCalendar(1, 1, 1);

        ArrayList<Goods> items = new ArrayList<>();
        Receipt receipt = new Receipt("Cashier ID", "42", "42 Main St", date, items, new DiscountCard(1, 3), 10.0d, 10.0d);

        when(receiptDirector.buildReceipt((Builder) any(), (InputArgsForReceipt) any())).thenReturn(receipt);
        assertSame(receipt, receiptServiceImpl.getReceipt(inputArgsForReceipt));
        verify(receiptDirector).buildReceipt((Builder) any(), (InputArgsForReceipt) any());
    }
}

