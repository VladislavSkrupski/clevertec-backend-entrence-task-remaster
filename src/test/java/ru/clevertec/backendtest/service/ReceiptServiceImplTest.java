package ru.clevertec.backendtest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.backendtest.InputArgsForReceipt;
import ru.clevertec.backendtest.models.receipt.Receipt;
import ru.clevertec.backendtest.models.receipt.ReceiptBuilder;
import ru.clevertec.backendtest.models.receipt.ReceiptDirector;
import ru.clevertec.backendtest.utils.CollectionsOfTestObjects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static ru.clevertec.backendtest.utils.CollectionsOfTestObjects.*;

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