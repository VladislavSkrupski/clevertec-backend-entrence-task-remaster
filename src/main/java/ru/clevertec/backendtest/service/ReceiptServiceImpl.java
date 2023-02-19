package ru.clevertec.backendtest.service;

import ru.clevertec.backendtest.InputArgsForReceipt;
import ru.clevertec.backendtest.models.receipt.Receipt;
import ru.clevertec.backendtest.models.receipt.ReceiptBuilder;
import ru.clevertec.backendtest.models.receipt.ReceiptDirector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Service
@Component
public class ReceiptServiceImpl implements ReceiptService {
    ReceiptDirector receiptDirector;

    @Autowired
    public ReceiptServiceImpl(ReceiptDirector receiptDirector) {
        this.receiptDirector = receiptDirector;
    }

    @Override
    public Receipt getReceipt(InputArgsForReceipt inputArgsForReceipt) {
        ReceiptBuilder receiptBuilder = new ReceiptBuilder();
        return receiptDirector.buildReceipt(receiptBuilder, inputArgsForReceipt);
    }
}
