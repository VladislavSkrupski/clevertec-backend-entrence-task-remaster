package ru.clevertec.backendtest.service.impl;

import ru.clevertec.backendtest.DAO.DiscountCardRepository;

import ru.clevertec.backendtest.model.discountCard.DiscountCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.backendtest.service.DiscountCardService;

@Service
public class DiscountCardServiceImpl implements DiscountCardService {
    private final DiscountCardRepository discountCardRepository;

    @Autowired
    public DiscountCardServiceImpl(DiscountCardRepository discountCardRepository) {
        this.discountCardRepository = discountCardRepository;
    }

    @Override
    public DiscountCard getById(int id) {
        return discountCardRepository.findById(id);
    }

    @Override
    public boolean create(DiscountCard discountCard) {
        return discountCardRepository.create(discountCard);
    }

    @Override
    public boolean update(DiscountCard discountCard) {
        return discountCardRepository.update(discountCard);
    }

    @Override
    public boolean deleteById(int id) {
        return discountCardRepository.deleteById(id);
    }
}
