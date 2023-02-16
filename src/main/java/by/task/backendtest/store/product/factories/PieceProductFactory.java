package by.task.backendtest.store.product.factories;

import by.task.backendtest.store.product.PieceProduct;
import by.task.backendtest.store.product.Product;

public class PieceProductFactory implements ProductFactory {
    @Override
    public Product createProduct(int id, String name, double price, boolean isPromotional) {
        return new PieceProduct(id, name, price, isPromotional);
    }
}
