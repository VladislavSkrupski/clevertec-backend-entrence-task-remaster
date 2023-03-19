package ru.clevertec.backendtest.controller;

import ru.clevertec.backendtest.InputArgsForReceipt;
import ru.clevertec.backendtest.service.ReceiptService;
import ru.clevertec.backendtest.model.receipt.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReceiptController {
    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    //http://localhost:8080/view/check?id=3&qty=1&id=2&qty=5&id=5&qty=1&card=1234
    @GetMapping(value = "/view/check", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String getReceipt(
            @RequestParam(name = "id") List<Integer> ids,
            @RequestParam(name = "qty") List<Integer> quantities,
            @RequestParam(required = false, defaultValue = "-1") Integer card
    ) {
        InputArgsForReceipt inputArgsForReceipt = InputArgsForReceipt.getInputArgsForReceipt(ids, quantities, card);
        return receiptService.getReceipt(inputArgsForReceipt).toString();
    }

    //http://localhost:8080/check?id=3&qty=1&id=2&qty=5&id=5&qty=1&card=1234
    @GetMapping(value = "/check", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Receipt getReceiptView(
            @RequestParam(name = "id") List<Integer> ids,
            @RequestParam(name = "qty") List<Integer> quantities,
            @RequestParam(required = false, defaultValue = "-1") Integer card
    ) {
        InputArgsForReceipt inputArgsForReceipt = InputArgsForReceipt.getInputArgsForReceipt(ids, quantities, card);
        return receiptService.getReceipt(inputArgsForReceipt);
    }
}
