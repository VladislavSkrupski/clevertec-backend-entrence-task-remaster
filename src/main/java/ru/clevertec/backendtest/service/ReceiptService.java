package ru.clevertec.backendtest.service;

import ru.clevertec.backendtest.InputArgsForReceipt;
import ru.clevertec.backendtest.models.receipt.Receipt;

public interface ReceiptService {
    Receipt getReceipt(InputArgsForReceipt inputArgsForReceipt);
}
