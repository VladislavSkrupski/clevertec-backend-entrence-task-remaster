package ru.clevertec.backendtest.models.product.factories;

import ru.clevertec.backendtest.models.product.BulkProduct;
import ru.clevertec.backendtest.models.product.Product;

public class BulkProductFactory implements ProductFactory {
    @Override
    public Product createProduct(int id, String name, double price, boolean isPromotional) {
        return new BulkProduct(id, name, price, isPromotional);
    }
}
