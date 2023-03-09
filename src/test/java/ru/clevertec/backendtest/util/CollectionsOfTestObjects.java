package ru.clevertec.backendtest.util;

import ru.clevertec.backendtest.model.discountCard.DiscountCard;
import ru.clevertec.backendtest.model.product.*;
import ru.clevertec.backendtest.model.receipt.Item;
import ru.clevertec.backendtest.model.receipt.Receipt;
import ru.clevertec.backendtest.model.receipt.ReceiptBuilder;

import java.util.ArrayList;
import java.util.List;

public interface CollectionsOfTestObjects {

    static List<Product> listOfProductsForTest() {
        return new ArrayList<>(
                List.of(
                        new BulkProduct(1, "bulk1", 10, true),
                        new LiquidProduct(2, "liquid1", 10, true),
                        new PieceProduct(3, "piece1", 10, false)
                )
        );
    }

    static List<Goods> listOfGoodsForTestReceipt() {
        return new ArrayList<>(
                List.of(
                        new Item(listOfProductsForTest().get(0), 5.0),
                        new Item(listOfProductsForTest().get(1), 2.0),
                        new Item(listOfProductsForTest().get(2), 10.0)
                )
        );
    }

    static DiscountCard discountCardForTestReceipt() {
        return new DiscountCard(1234, 10);
    }

    static Receipt receiptForTestReceipt() {
        return new ReceiptBuilder()
                .setCashierId()
                .setStoreId()
                .setStoreAddress()
                .setDate()
                .setItems(listOfGoodsForTestReceipt())
                .setDiscountCard(discountCardForTestReceipt())
                .setTotalCost()
                .setTotalCostWithDiscount()
                .build();
    }

    static List<DiscountCard> listOfDiscountCardsFromDB() {
        return new ArrayList<>(
                List.of(
                        new DiscountCard(1234, 10),
                        new DiscountCard(1234, 10),
                        new DiscountCard(4583, 10),
                        new DiscountCard(1, 10),
                        new DiscountCard(12345, 10),
                        new DiscountCard(123456, 10),
                        new DiscountCard(1111, 10)
                )
        );
    }

    static List<Product> listOfProductsFromDB() {
        return new ArrayList<>(
                List.of(
                        new PieceProduct(1, "Цемент, 20 кг", 20.00, false),
                        new PieceProduct(2, "Шуруповёрт", 300.00, true),
                        new BulkProduct(3, "Шуруп 4х30", 10.43, true),
                        new LiquidProduct(4, "Масло", 31.20, false),
                        new PieceProduct(5, "Клей, 5мл", 5.43, true),
                        new LiquidProduct(6, "Краска", 25.00, true),
                        new BulkProduct(7, "Песок", 4.00, true),
                        new PieceProduct(8, "Пила", 151.90, false),
                        new PieceProduct(9, "Гайка М36", 3.00, false),
                        new BulkProduct(10, "Гвозди 4х100мм", 30.00, false),
                        new PieceProduct(11, "Табурет", 59.34, true),
                        new PieceProduct(12, "Лампа", 41.50, false),
                        new PieceProduct(13, "Перчатки, пара", 2.45, true),
                        new PieceProduct(14, "Замок", 15.36, false),
                        new PieceProduct(15, "Огнемёт XM42-M", 2300.00, false)
                )
        );
    }
}
