package ru.clevertec.backendtest.service;

import ru.clevertec.backendtest.model.product.Product;
import ru.clevertec.backendtest.model.product.ProductAdapter;

import java.util.List;

public interface ProductService {
    Product getById(int id);

    List<Product> getAllById(List<Integer> ids);

    List<Product> getAll();

    boolean create(ProductAdapter product);

    boolean update(ProductAdapter product);

    boolean deleteById(int id);
}
