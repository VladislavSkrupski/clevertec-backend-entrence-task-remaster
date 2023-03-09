package ru.clevertec.backendtest.service;

import ru.clevertec.backendtest.model.discountCard.DiscountCard;

public interface DiscountCardService {
    DiscountCard getById(int id);

    boolean create(DiscountCard discountCard);

    boolean update(DiscountCard discountCard);

    boolean deleteById(int id);
}
