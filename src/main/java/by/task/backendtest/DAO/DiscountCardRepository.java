package by.task.backendtest.DAO;

import by.task.backendtest.store.discountCard.DiscountCard;

import java.util.List;

public interface DiscountCardRepository {
    List<DiscountCard> findAll();

    DiscountCard findById(int id);
}
