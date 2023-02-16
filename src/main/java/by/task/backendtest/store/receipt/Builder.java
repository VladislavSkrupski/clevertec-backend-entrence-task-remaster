package by.task.backendtest.store.receipt;

import by.task.backendtest.store.discountCard.DiscountCard;
import by.task.backendtest.store.product.Goods;

import java.util.List;

public interface Builder {
    String CASHIER_ID = "K021";
    String STORE_ID = "S025";
    String STORE_ADDRESS = "Minsk, str. Nemiga, 5";

    ReceiptBuilder setCashierId();

    ReceiptBuilder setStoreId();

    ReceiptBuilder setStoreAddress();

    ReceiptBuilder setDate();

    ReceiptBuilder setItems(List<Goods> Items);

    ReceiptBuilder setDiscountCard(DiscountCard discountCard);

    ReceiptBuilder setTotalCost();

    ReceiptBuilder setTotalCostWithDiscount();

    Receipt build();

}
