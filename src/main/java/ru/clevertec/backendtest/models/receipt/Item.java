package ru.clevertec.backendtest.models.receipt;

import ru.clevertec.backendtest.models.product.Goods;

public class Item implements Goods {
    private final static int PROMOTION_DISCOUNT = 10;
    private final static int PROMOTION_AMOUNT = 5;
    private final double amount;
    private final Goods wrappedProduct;
    private final double cost;

    public Item(Goods wrappedProduct, double amount) {
        this.wrappedProduct = wrappedProduct;
        this.amount = amount;
        this.cost = setCost();
    }

    public double getAmount() {
        return amount;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public int getId() {
        return wrappedProduct.getId();
    }

    @Override
    public String getName() {
        return wrappedProduct.getName();
    }

    @Override
    public double getPrice() {
        return wrappedProduct.getPrice();
    }

    @Override
    public boolean isPromotional() {
        return wrappedProduct.isPromotional();
    }

    @Override
    public String getUnit() {
        return wrappedProduct.getUnit();
    }

    public int getPromotionDiscount() {
        return PROMOTION_DISCOUNT;
    }

    public int getPromotionAmount() {
        return PROMOTION_AMOUNT;
    }

    private double setCost() {
        if (this.wrappedProduct.isPromotional() && this.amount >= PROMOTION_AMOUNT) {
            return (this.amount * this.getPrice() * (100 - PROMOTION_DISCOUNT) / 100);
        } else {
            return this.amount * this.getPrice();
        }
    }
}
