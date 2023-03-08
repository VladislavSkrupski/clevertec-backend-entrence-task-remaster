package ru.clevertec.backendtest.DAO;

import ru.clevertec.backendtest.model.product.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    Product findById(int id);
}
