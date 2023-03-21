package ru.clevertec.backendtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.backendtest.InputArgsForReceipt;
import ru.clevertec.backendtest.model.receipt.Receipt;
import ru.clevertec.backendtest.service.ReceiptService;
import ru.clevertec.backendtest.service.util.pdf.PDFGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/check")
public class ReceiptController {
    private final ReceiptService receiptService;
    private final ApplicationContext context;

    @Autowired
    public ReceiptController(ReceiptService receiptService, ApplicationContext context) {
        this.receiptService = receiptService;
        this.context = context;
    }


    //http://localhost:8080/check?id=3&qty=1&id=2&qty=5&id=5&qty=1&card=1234
    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Receipt getReceiptView(
            @RequestParam(name = "id") List<Integer> ids,
            @RequestParam(name = "qty") List<Integer> quantities,
            @RequestParam(required = false, defaultValue = "-1") Integer card
    ) {
        InputArgsForReceipt inputArgsForReceipt = InputArgsForReceipt.getInputArgsForReceipt(ids, quantities, card);
        return receiptService.getReceipt(inputArgsForReceipt);
    }

    //http://localhost:8080/check/view?id=3&qty=1&id=2&qty=5&id=5&qty=1&card=1234
    @GetMapping(value = "/view", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String getReceipt(
            @RequestParam(name = "id") List<Integer> ids,
            @RequestParam(name = "qty") List<Integer> quantities,
            @RequestParam(required = false, defaultValue = "-1") Integer card
    ) {
        InputArgsForReceipt inputArgsForReceipt = InputArgsForReceipt.getInputArgsForReceipt(ids, quantities, card);
        return receiptService.getReceipt(inputArgsForReceipt).toString();
    }

    //http://localhost:8080/check/pdf?id=3&qty=1&id=2&qty=5&id=5&qty=1&card=1234
    //http://localhost:8080/check/pdf?id=1&qty=5&id=2&qty=5&id=3&qty=1&id=4&qty=5&id=5&qty=5&id=6&qty=5&id=7&qty=5&id=8&qty=5&id=9&qty=5&id=10&qty=5&id=11&qty=5&id=12&qty=5&id=13&qty=5&id=14&qty=5&id=15&qty=5&id=16&qty=5&id=17&qty=5&id=18&qty=5&id=19&qty=5&id=20&qty=5&id=21&qty=5&id=22&qty=5&id=23&qty=5&id=24&qty=5&id=25&qty=5&card=1234
    @GetMapping(value = "/pdf")
    public ResponseEntity<InputStreamResource> getReceiptPDF(
            @RequestParam(name = "id") List<Integer> ids,
            @RequestParam(name = "qty") List<Integer> quantities,
            @RequestParam(required = false, defaultValue = "-1") Integer card
    ) {
        InputArgsForReceipt inputArgsForReceipt = InputArgsForReceipt.getInputArgsForReceipt(ids, quantities, card);
        PDFGenerator pdfGenerator = context.getBean(PDFGenerator.class);
        String fileName;
        File file;
        InputStreamResource resource;
        try {
            fileName = pdfGenerator.savePDF(receiptService.getReceipt(inputArgsForReceipt));
            file = new File(fileName);
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok()
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
