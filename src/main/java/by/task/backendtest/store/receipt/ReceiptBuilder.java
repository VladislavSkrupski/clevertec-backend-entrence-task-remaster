package by.task.backendtest.store.receipt;

import by.task.backendtest.store.discountCard.DiscountCard;
import by.task.backendtest.store.product.Goods;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


public class ReceiptBuilder implements Builder {
    private String cashierID;
    private String storeId;
    private String storeAddress;
    private GregorianCalendar date;
    private List<Goods> items;
    private DiscountCard discountCard;
    private boolean hasDiscount;
    private double totalCost;
    private double totalCostWithDiscount;

    public ReceiptBuilder() {
    }

    @Override
    public ReceiptBuilder setCashierId() {
        this.cashierID = CASHIER_ID;
        return this;
    }

    @Override
    public ReceiptBuilder setStoreId() {
        this.storeId = STORE_ID;
        return this;
    }

    @Override
    public ReceiptBuilder setStoreAddress() {
        this.storeAddress = STORE_ADDRESS;
        return this;
    }

    @Override
    public ReceiptBuilder setDate() {
        this.date = new GregorianCalendar();
        return this;
    }

    @Override
    public ReceiptBuilder setItems(List<Goods> items) {
        this.items = new ArrayList<>(items);
        return this;
    }

    @Override
    public ReceiptBuilder setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
        if (this.discountCard != null) hasDiscount = true;
        return this;
    }

    @Override
    public ReceiptBuilder setTotalCost() {
        double totalCost = 0;
        for (Goods item : items) {
            totalCost += ((Item) item).getCost();
        }
        this.totalCost = totalCost;
        return this;
    }

    @Override
    public ReceiptBuilder setTotalCostWithDiscount() {
        if (hasDiscount) {
            this.totalCostWithDiscount = (this.totalCost * (100 - this.discountCard.getDiscount()) / 100);
        } else {
            this.totalCostWithDiscount = this.totalCost;
        }
        return this;
    }

    @Override
    public Receipt build() {
        return new Receipt(
                cashierID,
                storeId,
                storeAddress,
                date,
                items,
                discountCard,
                totalCost,
                totalCostWithDiscount
        );
    }
}
