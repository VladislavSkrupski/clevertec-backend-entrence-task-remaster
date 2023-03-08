package ru.clevertec.backendtest.model.product.factories;

import ru.clevertec.backendtest.model.product.LiquidProduct;
import ru.clevertec.backendtest.model.product.Product;

public class LiquidProductFactory implements ProductFactory {
    @Override
    public Product createProduct(int id, String name, double price, boolean isPromotional) {
        return new LiquidProduct(id, name, price, isPromotional);
    }
}
