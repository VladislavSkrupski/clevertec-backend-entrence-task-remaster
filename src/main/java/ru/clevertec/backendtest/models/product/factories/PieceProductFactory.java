package ru.clevertec.backendtest.models.product.factories;

import ru.clevertec.backendtest.models.product.PieceProduct;
import ru.clevertec.backendtest.models.product.Product;

public class PieceProductFactory implements ProductFactory {
    @Override
    public Product createProduct(int id, String name, double price, boolean isPromotional) {
        return new PieceProduct(id, name, price, isPromotional);
    }
}
