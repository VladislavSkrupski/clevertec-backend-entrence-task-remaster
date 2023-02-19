package ru.clevertec.backendtest.models.discountCard;

public class DiscountCard {
    private final int id;
    private final int discount;

    public DiscountCard(int id, int discount) {
        this.id = id;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public int getDiscount() {
        return discount;
    }
}
