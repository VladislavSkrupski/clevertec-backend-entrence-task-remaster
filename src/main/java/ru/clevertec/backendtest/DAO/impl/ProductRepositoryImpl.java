package ru.clevertec.backendtest.DAO.impl;

import ru.clevertec.backendtest.DAO.ProductRepository;
import ru.clevertec.backendtest.DAO.mapper.ProductRowMapper;
import ru.clevertec.backendtest.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAll() throws DataAccessException {
        String query = "SELECT * FROM product";
        return jdbcTemplate.query(query, new ProductRowMapper());
    }

    @Override
    public Product findById(int id) throws DataAccessException {
        String query = "SELECT * FROM product WHERE id=" + id;
        Product product;
        try {
            product = jdbcTemplate.queryForObject(query, new ProductRowMapper());
        } catch (DataAccessException e) {
            System.out.println("ERROR: there is no product with id=" + id);
            product = null;
        }
        return product;
    }
}
