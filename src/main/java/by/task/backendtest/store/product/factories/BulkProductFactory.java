package by.task.backendtest.store.product.factories;

import by.task.backendtest.store.product.BulkProduct;
import by.task.backendtest.store.product.Product;

public class BulkProductFactory implements ProductFactory {
    @Override
    public Product createProduct(int id, String name, double price, boolean isPromotional) {
        return new BulkProduct(id, name, price, isPromotional);
    }
}
