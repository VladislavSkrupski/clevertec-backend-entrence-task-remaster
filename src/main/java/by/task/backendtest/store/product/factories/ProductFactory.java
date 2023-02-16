package by.task.backendtest.store.product.factories;

import by.task.backendtest.store.product.Product;

public interface ProductFactory {
    Product createProduct(int id, String name, double price, boolean isPromotional);

}
