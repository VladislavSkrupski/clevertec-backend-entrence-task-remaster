package ru.clevertec.backendtest.models.product.factories;

import ru.clevertec.backendtest.models.product.LiquidProduct;
import ru.clevertec.backendtest.models.product.Product;

public class LiquidProductFactory implements ProductFactory {
    @Override
    public Product createProduct(int id, String name, double price, boolean isPromotional) {
        return new LiquidProduct(id, name, price, isPromotional);
    }
}
