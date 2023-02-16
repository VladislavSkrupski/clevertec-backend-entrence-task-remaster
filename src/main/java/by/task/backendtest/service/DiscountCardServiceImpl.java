package by.task.backendtest.service;

import by.task.backendtest.DAO.DiscountCardRepository;

import by.task.backendtest.store.discountCard.DiscountCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
