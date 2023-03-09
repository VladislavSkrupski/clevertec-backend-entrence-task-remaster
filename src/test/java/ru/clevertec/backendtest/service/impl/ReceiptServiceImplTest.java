package ru.clevertec.backendtest.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.backendtest.InputArgsForReceipt;
import ru.clevertec.backendtest.model.receipt.Receipt;
import ru.clevertec.backendtest.model.receipt.ReceiptBuilder;
import ru.clevertec.backendtest.model.receipt.ReceiptDirector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static ru.clevertec.backendtest.util.CollectionsOfTestObjects.*;

@ExtendWith(MockitoExtension.class)
class ReceiptServiceImplTest {

    @Mock
    private ReceiptDirector receiptDirector;

    @Mock
    private InputArgsForReceipt inputArgsForReceipt;

    @Test
    void getReceiptShouldReturnReceiptWithCorrectData() {
        when(receiptDirector.buildReceipt(any(ReceiptBuilder.class), any(InputArgsForReceipt.class)))
                .thenReturn(receiptForTestReceipt());

        Receipt receipt = new ReceiptServiceImpl(receiptDirector).getReceipt(inputArgsForReceipt);

        assertAll(
                () -> assertThat(receipt.getCashierID()).isEqualTo(receiptForTestReceipt().getCashierID()),
                () -> assertThat(receipt.getStoreId()).isEqualTo(receiptForTestReceipt().getStoreId()),
                () -> assertThat(receipt.getStoreAddress()).isEqualTo(receiptForTestReceipt().getStoreAddress()),
                () -> assertThat(receipt.getItems()).hasSize(receiptForTestReceipt().getItems().size()),
                () -> assertThat(receipt.getTotalCost()).isEqualTo(receiptForTestReceipt().getTotalCost()),
                () -> assertThat(receipt.getTotalCostWithDiscount()).isEqualTo(receiptForTestReceipt().getTotalCostWithDiscount())
        );
    }
}