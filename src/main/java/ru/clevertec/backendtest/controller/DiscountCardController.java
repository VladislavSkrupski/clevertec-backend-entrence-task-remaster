package ru.clevertec.backendtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.backendtest.model.discountCard.DiscountCard;
import ru.clevertec.backendtest.model.product.Product;
import ru.clevertec.backendtest.service.DiscountCardService;

@RestController
public class DiscountCardController {
    private final DiscountCardService discountCardService;

    @Autowired
    public DiscountCardController(DiscountCardService discountCardService) {
        this.discountCardService = discountCardService;
    }

    @GetMapping(value = "/discountcard/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public DiscountCard getProductById(@PathVariable Integer id) {
        return discountCardService.getById(id);
    }

    @GetMapping(value = "/discountcard/xml/{id}", produces = "application/xml")
    public DiscountCard getProductByIdInXML(@PathVariable Integer id) {
        return discountCardService.getById(id);
    }

    @PostMapping(value = "/discountcard", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean createProduct(@RequestBody DiscountCard discountCard) {
        return discountCardService.create(discountCard);
    }

    @PutMapping(value = "/discountcard", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean updateById(@RequestBody DiscountCard discountCard) {
        return discountCardService.update(discountCard);
    }

    @DeleteMapping(value = "/discountcard/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return discountCardService.deleteById(id);
    }
}
