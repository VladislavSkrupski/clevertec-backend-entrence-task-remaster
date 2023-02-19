package ru.clevertec.backendtest.DAO;

import ru.clevertec.backendtest.models.discountCard.DiscountCard;
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
}
