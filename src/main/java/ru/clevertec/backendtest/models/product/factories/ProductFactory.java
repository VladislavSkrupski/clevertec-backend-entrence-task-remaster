package ru.clevertec.backendtest.models.product.factories;

import ru.clevertec.backendtest.models.product.Product;

public interface ProductFactory {
    Product createProduct(int id, String name, double price, boolean isPromotional);

}
