package ru.clevertec.backendtest.DAO;

import ru.clevertec.backendtest.model.discountCard.DiscountCard;
import ru.clevertec.backendtest.model.product.Product;

import java.util.List;

public interface DiscountCardRepository {
    List<DiscountCard> findAll();

    DiscountCard findById(int id);

    boolean create(DiscountCard discountCard);

    boolean update(DiscountCard discountCard);

    boolean deleteById(int id);
}
