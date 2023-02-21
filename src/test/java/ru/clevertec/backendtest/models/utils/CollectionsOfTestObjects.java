package ru.clevertec.backendtest.models.utils;

import ru.clevertec.backendtest.models.discountCard.DiscountCard;
import ru.clevertec.backendtest.models.product.BulkProduct;
import ru.clevertec.backendtest.models.product.Goods;
import ru.clevertec.backendtest.models.product.LiquidProduct;
import ru.clevertec.backendtest.models.product.PieceProduct;
import ru.clevertec.backendtest.models.receipt.Item;

import java.util.ArrayList;
import java.util.List;

public interface CollectionsOfTestObjects {
    static List<Goods> listOfGoodsForTestReceipt() {
        return new ArrayList<>(
                List.of(new Item(new BulkProduct(1, "bulk1", 10, true), 5),
                        new Item(new LiquidProduct(2, "liquid1", 10, true), 2),
                        new Item(new PieceProduct(3, "piece1", 10, false), 10)
                )
        );
    }

    static DiscountCard discountCardForTestReceipt() {
        return new DiscountCard(1234, 10);
    }
}
