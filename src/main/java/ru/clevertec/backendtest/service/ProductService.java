package ru.clevertec.backendtest.service;

import ru.clevertec.backendtest.models.product.Product;

import java.util.List;

public interface ProductService {
    Product getById(int id);

    List<Product> getAllById(List<Integer> ids);

    List<Product> getAll();
}
