package by.task.backendtest.service;

import by.task.backendtest.InputArgsForReceipt;
import by.task.backendtest.store.receipt.Receipt;
import by.task.backendtest.store.receipt.ReceiptBuilder;
import by.task.backendtest.store.receipt.ReceiptDirector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
