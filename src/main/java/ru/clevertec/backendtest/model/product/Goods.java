package ru.clevertec.backendtest.model.product;

public interface Goods {
    int getId();

    String getName();

    double getPrice();

    boolean isPromotional();

    String getUnit();
}
