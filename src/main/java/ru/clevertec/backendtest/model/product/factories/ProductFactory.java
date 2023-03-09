package ru.clevertec.backendtest.model.product.factories;

import ru.clevertec.backendtest.model.product.Product;

public interface ProductFactory {
    Product createProduct(int id, String name, double price, boolean isPromotional);

}
