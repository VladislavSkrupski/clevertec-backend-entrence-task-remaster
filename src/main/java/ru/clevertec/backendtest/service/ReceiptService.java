package ru.clevertec.backendtest.service;

import ru.clevertec.backendtest.InputArgsForReceipt;
import ru.clevertec.backendtest.model.receipt.Receipt;

public interface ReceiptService {
    Receipt getReceipt(InputArgsForReceipt inputArgsForReceipt);
}
