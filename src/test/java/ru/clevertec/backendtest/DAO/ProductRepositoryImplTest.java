package ru.clevertec.backendtest.DAO;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.clevertec.backendtest.models.product.Product;
import ru.clevertec.backendtest.utils.CollectionsOfTestObjects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Nested
    class FindAllTest {
        @Test
        void findAllShouldReturnFakeListOfProductsFromDB() {
            when(jdbcTemplate.query(anyString(), any(ProductRowMapper.class)))
                    .thenReturn(CollectionsOfTestObjects.listOfProductsFromDB());

            int actualProductsListSize = new ProductRepositoryImpl(jdbcTemplate).findAll().size();
            int expectedProductsListSize = CollectionsOfTestObjects.listOfProductsFromDB().size();

            assertThat(actualProductsListSize).isEqualTo(expectedProductsListSize);
        }

        @Test
        void findAllShouldThrowDataAccessExceptionWhenDBIsDown() {
            when(jdbcTemplate.query(anyString(), any(ProductRowMapper.class)))
                    .thenThrow(new DataAccessResourceFailureException(""));

            assertThrows(
                    DataAccessResourceFailureException.class,
                    () -> new ProductRepositoryImpl(jdbcTemplate).findAll()
            );
        }
    }

    @Nested
    class FindByIdTest {
        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14})
        void findByIdShouldReturnFakeProductFromDB(int index) {
            when(jdbcTemplate.queryForObject(anyString(), any(ProductRowMapper.class)))
                    .thenReturn(CollectionsOfTestObjects.listOfProductsFromDB().get(index));

            Product actual = new ProductRepositoryImpl(jdbcTemplate).findById(index);
            Product expected = CollectionsOfTestObjects.listOfProductsFromDB().get(index);

            assertAll(
                    () -> assertThat(actual.getId()).isEqualTo(expected.getId()),
                    () -> assertThat(actual.getName()).isEqualTo(expected.getName()),
                    () -> assertThat(actual.getPrice()).isEqualTo(expected.getPrice()),
                    () -> assertThat(actual.isPromotional()).isEqualTo(expected.isPromotional())
            );
        }

        @Test
        void findByIdShouldReturnNullWhenProductIsNotFound() {
            when(jdbcTemplate.queryForObject(anyString(), any(ProductRowMapper.class)))
                    .thenThrow(new DataAccessResourceFailureException(""));

            assertThat(new ProductRepositoryImpl(jdbcTemplate).findById(100)).isNull();
        }
    }
}