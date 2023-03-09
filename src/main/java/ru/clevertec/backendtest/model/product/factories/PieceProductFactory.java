package ru.clevertec.backendtest.model.product.factories;

import ru.clevertec.backendtest.model.product.PieceProduct;
import ru.clevertec.backendtest.model.product.Product;

public class PieceProductFactory implements ProductFactory {
    @Override
    public Product createProduct(int id, String name, double price, boolean isPromotional) {
        return new PieceProduct(id, name, price, isPromotional);
    }
}
