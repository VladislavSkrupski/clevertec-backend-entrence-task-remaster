package by.task.backendtest.service;

import by.task.backendtest.store.product.Product;

import java.util.List;

public interface ProductService {
    Product getById(int id);

    List<Product> getAllById(List<Integer> ids);

    List<Product> getAll();
}
