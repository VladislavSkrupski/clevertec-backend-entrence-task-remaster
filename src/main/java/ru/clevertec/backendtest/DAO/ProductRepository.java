package ru.clevertec.backendtest.DAO;

import ru.clevertec.backendtest.model.product.Product;
import ru.clevertec.backendtest.model.product.ProductAdapter;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    Product findById(int id);

    boolean create(ProductAdapter product);

    boolean update(ProductAdapter product);

    boolean deleteById(int id);
}
