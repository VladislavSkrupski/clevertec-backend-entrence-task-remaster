package by.task.backendtest;

import by.task.backendtest.store.receipt.Receipt;
import by.task.backendtest.store.receipt.ReceiptBuilder;
import by.task.backendtest.store.receipt.ReceiptDirector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.Buffer;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

@Component
public class ConsoleWorker {
    @Autowired
    private ReceiptDirector director;
    @Autowired
    private InputArgsForReceipt inputArgsForReceipt;

    @Autowired
    public void printToConsole() {
        ReceiptBuilder receiptBuilder = new ReceiptBuilder();
        if (inputArgsForReceipt.getProductAmountMap().size() > 0)
            System.out.println(director.buildReceipt(receiptBuilder, inputArgsForReceipt).print());
        printToFile();
    }

    public void printToFile() {
        ReceiptBuilder receiptBuilder = new ReceiptBuilder();
        if (inputArgsForReceipt.getProductAmountMap().size() > 0) {
            Receipt receipt = director.buildReceipt(receiptBuilder, inputArgsForReceipt);
            String fileName = "receipt-" + new SimpleDateFormat("d-MM-yyyy--HH-mm-ss").format(receipt.getDate().getTime()) + ".txt";
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                writer.write(receipt.print());
                writer.close();
            } catch (IOException e) {
                System.out.println("Can't write file " + fileName);
            }
        }
    }

    @Autowired
    private InputArgsForReceipt readArgs(ApplicationArguments args, InputArgsForReceipt inputArgsForReceipt) {
        for (String argument : args.getSourceArgs()) {
            if (argument.matches("\\d+-\\d+")) {
                inputArgsForReceipt.setProductAmountMap(argument);
            } else if (argument.matches("[Cc]ard-\\d+")) {
                inputArgsForReceipt.setDiscountCardId(argument);
            } else {
                return readLine(readFromFile(argument), inputArgsForReceipt);
            }
        }
        return inputArgsForReceipt;
    }

    private String[] readFromFile(String filePath) {
        String line = "";
        try {
            Scanner scanner = new Scanner(new File(filePath));
            line = scanner.nextLine();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: file not found at path: " + filePath);
        }
        return line.split(" ");
    }

    private InputArgsForReceipt readLine(String[] arguments, InputArgsForReceipt inputArgsForReceipt) {
        for (String argument : arguments) {
            if (argument.matches("\\d+-\\d+")) {
                inputArgsForReceipt.setProductAmountMap(argument);
            } else if (argument.matches("[Cc]ard-\\d+")) {
                inputArgsForReceipt.setDiscountCardId(argument);
            }
        }
        return inputArgsForReceipt;
    }
}
