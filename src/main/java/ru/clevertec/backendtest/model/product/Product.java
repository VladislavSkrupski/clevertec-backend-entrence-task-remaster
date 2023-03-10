package ru.clevertec.backendtest.model.product;

import jakarta.validation.constraints.Pattern;

public abstract class Product implements Goods {
    private final int id;
    @Pattern(regexp = "^\\S+[a-zA-Zа-яА-ЯёЁ\\s\\d-()/,]{3,}\\S+$")
    private final String name;
    private final double price;
    private final boolean isPromotional;

    public Product(int id, String name, double price, boolean isPromotional) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isPromotional = isPromotional;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public boolean isPromotional() {
        return isPromotional;
    }
}
