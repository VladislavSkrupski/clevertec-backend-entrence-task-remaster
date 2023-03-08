package ru.clevertec.backendtest.service.impl;

import ru.clevertec.backendtest.InputArgsForReceipt;
import ru.clevertec.backendtest.model.receipt.Receipt;
import ru.clevertec.backendtest.model.receipt.ReceiptBuilder;
import ru.clevertec.backendtest.model.receipt.ReceiptDirector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.clevertec.backendtest.service.ReceiptService;

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
