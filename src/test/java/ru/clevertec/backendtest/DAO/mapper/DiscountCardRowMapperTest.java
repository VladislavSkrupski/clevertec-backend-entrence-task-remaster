package ru.clevertec.backendtest.DAO.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.backendtest.DAO.mapper.DiscountCardRowMapper;
import ru.clevertec.backendtest.model.discountCard.DiscountCard;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscountCardRowMapperTest {

    @Mock
    private ResultSet resultSet;

    @Test
    void mapRowShouldReturnDiscountCardObjectWhenResultSetIsValid() throws SQLException {
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getInt("discount_percent")).thenReturn(10);
        DiscountCard discountCard = new DiscountCardRowMapper().mapRow(resultSet, 0);

        assertAll(
                () -> {
                    assert discountCard != null;
                    assertThat(discountCard.getId()).isEqualTo(1);
                },
                () -> {
                    assert discountCard != null;
                    assertThat(discountCard.getDiscount()).isEqualTo(10);
                }
        );
    }

    @Test
    void mapRowShouldThrowSQLExceptionWhenResultSetThrowsException() throws SQLException {
        when(resultSet.getInt(anyString())).thenThrow(new SQLException());

        assertThrows(SQLException.class, () -> new DiscountCardRowMapper().mapRow(resultSet, 0));
    }
}