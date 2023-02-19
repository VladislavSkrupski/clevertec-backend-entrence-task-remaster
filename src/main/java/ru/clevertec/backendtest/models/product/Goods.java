package ru.clevertec.backendtest.models.product;

public interface Goods {
    int getId();

    String getName();

    double getPrice();

    boolean isPromotional();

    String getUnit();
}
