package ru.clevertec.backendtest.DAO.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.backendtest.DAO.mapper.ProductRowMapper;
import ru.clevertec.backendtest.model.product.Product;
import ru.clevertec.backendtest.model.product.Units;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductRowMapperTest {

    @Mock
    private ResultSet resultSet;

    @AfterEach
    void tearDown() {
        resultSet = null;
    }

    @ParameterizedTest
    @ValueSource(strings = {"PIECE", "BULK", "LIQUID"})
    void mapRowShouldReturnProductObjectWhenResultSetIsValid(String unit) throws SQLException {
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("product_name")).thenReturn("test");
        when(resultSet.getDouble("price")).thenReturn(10.0);
        when(resultSet.getBoolean("is_promotional")).thenReturn(true);
        when(resultSet.getString("product_unit")).thenReturn(unit);
        Product product = new ProductRowMapper().mapRow(resultSet, 0);
        assertAll(
                () -> {
                    assert product != null;
                    assertThat(product.getId()).isEqualTo(1);
                },
                () -> {
                    assert product != null;
                    assertThat(product.getName()).isEqualTo("test");
                },
                () -> {
                    assert product != null;
                    assertThat(product.getPrice()).isEqualTo(10.0);
                },
                () -> {
                    assert product != null;
                    assertThat(product.isPromotional()).isTrue();
                },
                () -> {
                    assert product != null;
                    assertThat(product.getUnit()).isIn(Units.BULK.getUnit(), Units.PIECE.getUnit(), Units.LIQUID.getUnit());
                }
        );
    }

    @Test
    void mapRowShouldThrowIllegalArgumentExceptionWhenUnitIsNotValid() throws SQLException {
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("product_name")).thenReturn("test");
        when(resultSet.getDouble("price")).thenReturn(10.0);
        when(resultSet.getBoolean("is_promotional")).thenReturn(true);
        when(resultSet.getString("product_unit")).thenReturn("ANY_OTHER");

        assertThrows(IllegalArgumentException.class, () -> new ProductRowMapper().mapRow(resultSet, 0));
    }

    @Test
    void mapRowShouldThrowSQLExceptionWhenResultSetThrowsSQLException() throws SQLException {
        when(resultSet.getInt(anyString())).thenThrow(new SQLException());

        assertThrows(SQLException.class, () -> new ProductRowMapper().mapRow(resultSet, 0));
    }
}