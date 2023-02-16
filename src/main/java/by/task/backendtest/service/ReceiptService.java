package by.task.backendtest.service;

import by.task.backendtest.InputArgsForReceipt;
import by.task.backendtest.store.receipt.Receipt;

public interface ReceiptService {
    Receipt getReceipt(InputArgsForReceipt inputArgsForReceipt);
}
