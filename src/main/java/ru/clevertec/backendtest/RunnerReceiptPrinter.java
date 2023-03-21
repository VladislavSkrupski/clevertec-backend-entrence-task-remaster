package ru.clevertec.backendtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RunnerReceiptPrinter {

    private static ApplicationContext context;

    @Autowired
    public RunnerReceiptPrinter(ApplicationContext applicationContext) {
        context=applicationContext;
    }

    public static void main(String[] args) {
        SpringApplication.run(RunnerReceiptPrinter.class, args);
        ConsoleWorker consoleWorker = context.getBean(ConsoleWorker.class);
        consoleWorker.printToConsole();
        consoleWorker.printToFile();
    }
}
