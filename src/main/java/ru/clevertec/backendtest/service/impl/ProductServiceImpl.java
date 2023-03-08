package ru.clevertec.backendtest.service.impl;

import ru.clevertec.backendtest.DAO.ProductRepository;
import ru.clevertec.backendtest.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.backendtest.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllById(List<Integer> ids) {
        List<Product> products = new ArrayList<>();
        for (int id : ids) {
            products.add(getById(id));
        }
        return products;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
