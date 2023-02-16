package by.task.backendtest.store.product.factories;

import by.task.backendtest.store.product.LiquidProduct;
import by.task.backendtest.store.product.Product;

public class LiquidProductFactory implements ProductFactory {
    @Override
    public Product createProduct(int id, String name, double price, boolean isPromotional) {
        return new LiquidProduct(id, name, price, isPromotional);
    }
}
