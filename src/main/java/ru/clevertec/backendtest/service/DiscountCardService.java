package ru.clevertec.backendtest.service;

import ru.clevertec.backendtest.models.discountCard.DiscountCard;

public interface DiscountCardService {
    DiscountCard getById(int id);
}
