package ru.clevertec.backendtest.model.product.factories;

import ru.clevertec.backendtest.model.product.BulkProduct;
import ru.clevertec.backendtest.model.product.Product;

public class BulkProductFactory implements ProductFactory {
    @Override
    public Product createProduct(int id, String name, double price, boolean isPromotional) {
        return new BulkProduct(id, name, price, isPromotional);
    }
}
