package by.task.backendtest.DAO;

import by.task.backendtest.store.discountCard.DiscountCard;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountCardRowMapper implements RowMapper<DiscountCard> {
    @Override
    public DiscountCard mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DiscountCard(rs.getInt("id"), rs.getInt("discount_percent"));
    }
}
