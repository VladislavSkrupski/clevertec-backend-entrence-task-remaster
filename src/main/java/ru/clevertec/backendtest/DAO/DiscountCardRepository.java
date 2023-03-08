package ru.clevertec.backendtest.DAO;

import ru.clevertec.backendtest.model.discountCard.DiscountCard;

import java.util.List;

public interface DiscountCardRepository {
    List<DiscountCard> findAll();

    DiscountCard findById(int id);
}
