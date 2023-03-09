package ru.clevertec.backendtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.backendtest.model.product.Product;
import ru.clevertec.backendtest.model.product.ProductAdapter;
import ru.clevertec.backendtest.service.ProductService;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/product/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Product getProductById(@PathVariable Integer id) {
        return productService.getById(id);
    }

    @GetMapping(value = "/product/xml/{id}", produces = {MediaType.APPLICATION_XML_VALUE})
    public Product getProductByIdInXML(@PathVariable Integer id) {
        return productService.getById(id);
    }

    @GetMapping(value = "/product/ids", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Product> getAllById(@RequestParam(value = "id") List<Integer> ids) {
        return productService.getAllById(ids);
    }

    @GetMapping(value = "/product/xml/ids", produces = {MediaType.APPLICATION_XML_VALUE})
    public List<Product> getAllByIdInXML(@RequestParam(value = "id") List<Integer> ids) {
        return productService.getAllById(ids);
    }

    @GetMapping(value = "/product/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping(value = "/product/xml/all", produces = {MediaType.APPLICATION_XML_VALUE})
    public List<Product> getAllInXML() {
        return productService.getAll();
    }

    @PostMapping(value = "/product", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean createProduct(@RequestBody ProductAdapter product) {
        return productService.create(product);
    }

    @PutMapping(value = "/product", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean updateById(@RequestBody ProductAdapter product) {
        return productService.update(product);
    }

    @DeleteMapping(value = "/product/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return productService.deleteById(id);
    }
}
