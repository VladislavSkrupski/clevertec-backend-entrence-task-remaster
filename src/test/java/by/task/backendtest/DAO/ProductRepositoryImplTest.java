package by.task.backendtest.DAO;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import by.task.backendtest.store.product.BulkProduct;
import by.task.backendtest.store.product.Product;

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

@ContextConfiguration(classes = {ProductRepositoryImpl.class})
@ExtendWith(SpringExtension.class)
class ProductRepositoryImplTest {
    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductRepositoryImpl productRepositoryImpl;

    /**
     * Method under test: {@link ProductRepositoryImpl#findAll()}
     */
    @Test
    void testFindAll() throws DataAccessException {
        ArrayList<Product> productList = new ArrayList<>();
        when(jdbcTemplate.query((String) any(), (RowMapper<Product>) any())).thenReturn(productList);
        List<Product> actualFindAllResult = productRepositoryImpl.findAll();
        assertSame(productList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(jdbcTemplate).query((String) any(), (RowMapper<Product>) any());
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#findAll()}
     */
    @Test
    void testFindAll2() throws DataAccessException {
        when(jdbcTemplate.query((String) any(), (RowMapper<Product>) any()))
                .thenThrow(new EmptyResultDataAccessException(3));
        assertThrows(EmptyResultDataAccessException.class, () -> productRepositoryImpl.findAll());
        verify(jdbcTemplate).query((String) any(), (RowMapper<Product>) any());
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#findById(int)}
     */
    @Test
    void testFindById() throws DataAccessException {
        BulkProduct bulkProduct = new BulkProduct(1, "Name", 10.0d, true);

        when(jdbcTemplate.queryForObject((String) any(), (RowMapper<Product>) any())).thenReturn(bulkProduct);
        assertSame(bulkProduct, productRepositoryImpl.findById(1));
        verify(jdbcTemplate).queryForObject((String) any(), (RowMapper<Product>) any());
    }

    /**
     * Method under test: {@link ProductRepositoryImpl#findById(int)}
     */
    @Test
    void testFindById2() throws DataAccessException {
        when(jdbcTemplate.queryForObject((String) any(), (RowMapper<Product>) any()))
                .thenThrow(new EmptyResultDataAccessException(3));
        assertNull(productRepositoryImpl.findById(1));
        verify(jdbcTemplate).queryForObject((String) any(), (RowMapper<Product>) any());
    }
}

