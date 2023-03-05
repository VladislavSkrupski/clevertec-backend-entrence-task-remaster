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
import ru.clevertec.backendtest.models.discountCard.DiscountCard;
import ru.clevertec.backendtest.utils.CollectionsOfTestObjects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscountCardRepositoryImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Nested
    class FindAllTest {
        @Test
        void findAllShouldReturnFakeListOfDiscountCardsFromDB() {
            when(jdbcTemplate.query(anyString(), any(DiscountCardRowMapper.class)))
                    .thenReturn(CollectionsOfTestObjects.listOfDiscountCardsFromDB());

            int actualDiscountCardsListSize = new DiscountCardRepositoryImpl(jdbcTemplate).findAll().size();
            int expectedDiscountCardsListSize = CollectionsOfTestObjects.listOfDiscountCardsFromDB().size();

            assertThat(actualDiscountCardsListSize).isEqualTo(expectedDiscountCardsListSize);
        }

        @Test
        void findAllShouldThrowDataAccessExceptionWhenDBIsDown() {
            when(jdbcTemplate.query(anyString(), any(DiscountCardRowMapper.class)))
                    .thenThrow(new DataAccessResourceFailureException(""));

            assertThrows(
                    DataAccessResourceFailureException.class,
                    () -> new DiscountCardRepositoryImpl(jdbcTemplate).findAll()
            );
        }
    }


    @Nested
    class FindByIdTest {
        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
        void findByIdShouldReturnFakeDiscountCardFromDB(int index) {
            when(jdbcTemplate.queryForObject(anyString(), any(DiscountCardRowMapper.class)))
                    .thenReturn(CollectionsOfTestObjects.listOfDiscountCardsFromDB().get(index));

            DiscountCard actual = new DiscountCardRepositoryImpl(jdbcTemplate).findById(index);
            DiscountCard expected = CollectionsOfTestObjects.listOfDiscountCardsFromDB().get(index);

            assertAll(
                    () -> assertThat(actual.getId()).isEqualTo(expected.getId()),
                    () -> assertThat(actual.getDiscount()).isEqualTo(expected.getDiscount())
            );
        }

        @Test
        void findByIdShouldReturnNullWhenCardNotFound() {
            when(jdbcTemplate.queryForObject(anyString(), any(DiscountCardRowMapper.class)))
                    .thenThrow(new DataAccessResourceFailureException(""));

            assertThat(new DiscountCardRepositoryImpl(jdbcTemplate).findById(10)).isNull();
        }
    }
}