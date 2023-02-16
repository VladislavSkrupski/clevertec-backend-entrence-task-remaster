package by.task.backendtest.DAO;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import by.task.backendtest.store.discountCard.DiscountCard;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DiscountCardRepositoryImpl.class})
@ExtendWith(SpringExtension.class)
class DiscountCardRepositoryImplTest {
    @Autowired
    private DiscountCardRepositoryImpl discountCardRepositoryImpl;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    /**
     * Method under test: {@link DiscountCardRepositoryImpl#findAll()}
     */
    @Test
    void testFindAll() throws DataAccessException {
        ArrayList<DiscountCard> discountCardList = new ArrayList<>();
        when(jdbcTemplate.query((String) any(), (RowMapper<DiscountCard>) any())).thenReturn(discountCardList);
        List<DiscountCard> actualFindAllResult = discountCardRepositoryImpl.findAll();
        assertSame(discountCardList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(jdbcTemplate).query((String) any(), (RowMapper<DiscountCard>) any());
    }

    /**
     * Method under test: {@link DiscountCardRepositoryImpl#findAll()}
     */
    @Test
    void testFindAll2() throws DataAccessException {
        when(jdbcTemplate.query((String) any(), (RowMapper<DiscountCard>) any()))
                .thenThrow(new EmptyResultDataAccessException(3));
        assertThrows(EmptyResultDataAccessException.class, () -> discountCardRepositoryImpl.findAll());
        verify(jdbcTemplate).query((String) any(), (RowMapper<DiscountCard>) any());
    }

    /**
     * Method under test: {@link DiscountCardRepositoryImpl#findById(int)}
     */
    @Test
    void testFindById() throws DataAccessException {
        DiscountCard discountCard = new DiscountCard(1, 3);

        when(jdbcTemplate.queryForObject((String) any(), (RowMapper<DiscountCard>) any())).thenReturn(discountCard);
        assertSame(discountCard, discountCardRepositoryImpl.findById(1));
        verify(jdbcTemplate).queryForObject((String) any(), (RowMapper<DiscountCard>) any());
    }

    /**
     * Method under test: {@link DiscountCardRepositoryImpl#findById(int)}
     */
    @Test
    void testFindById2() throws DataAccessException {
        when(jdbcTemplate.queryForObject((String) any(), (RowMapper<DiscountCard>) any()))
                .thenThrow(new EmptyResultDataAccessException(3));
        assertNull(discountCardRepositoryImpl.findById(1));
        verify(jdbcTemplate).queryForObject((String) any(), (RowMapper<DiscountCard>) any());
    }
}

