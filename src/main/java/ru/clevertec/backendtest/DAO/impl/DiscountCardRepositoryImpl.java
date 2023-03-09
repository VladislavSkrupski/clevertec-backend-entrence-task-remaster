package ru.clevertec.backendtest.DAO.impl;

import ru.clevertec.backendtest.DAO.DiscountCardRepository;
import ru.clevertec.backendtest.DAO.mapper.DiscountCardRowMapper;
import ru.clevertec.backendtest.model.discountCard.DiscountCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DiscountCardRepositoryImpl implements DiscountCardRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DiscountCardRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DiscountCard> findAll() throws DataAccessException {
        String query = "SELECT * FROM discount_card";
        return jdbcTemplate.query(query, new DiscountCardRowMapper());
    }

    @Override
    public DiscountCard findById(int id) {
        String query = "SELECT * FROM discount_card WHERE id=" + id;
        DiscountCard discountCard;
        try {
            discountCard = jdbcTemplate.queryForObject(query, new DiscountCardRowMapper());
        } catch (DataAccessException e) {
            System.out.println("ERROR: there is no discount card with id=" + id);
            discountCard = null;
        }
        return discountCard;
    }

    @Override
    public boolean create(DiscountCard discountCard) {
        String query = "INSERT INTO discount_card (id, discount_percent) values (?, ?)";
        return jdbcTemplate.update(
                query,
                discountCard.getId(),
                discountCard.getDiscount()
        ) > 0;
    }

    @Override
    public boolean update(DiscountCard discountCard) {
        String query = "UPDATE discount_card SET discount_percent=? WHERE id=?";
        return jdbcTemplate.update(
                query,
                discountCard.getDiscount(),
                discountCard.getId()
        ) > 0;
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM discount_card WHERE id=?";
        return jdbcTemplate.update(query, id) > 0;
    }
}
