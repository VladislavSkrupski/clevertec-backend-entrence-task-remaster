package by.task.backendtest.DAO;

import by.task.backendtest.store.product.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    Product findById(int id);
}
