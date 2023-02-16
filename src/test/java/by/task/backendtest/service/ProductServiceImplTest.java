package by.task.backendtest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import by.task.backendtest.DAO.ProductRepository;
import by.task.backendtest.store.product.BulkProduct;
import by.task.backendtest.store.product.Product;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    /**
     * Method under test: {@link ProductServiceImpl#getById(int)}
     */
    @Test
    void testGetById() {
        BulkProduct bulkProduct = new BulkProduct(1, "Name", 10.0d, true);

        when(productRepository.findById(anyInt())).thenReturn(bulkProduct);
        assertSame(bulkProduct, productServiceImpl.getById(1));
        verify(productRepository).findById(anyInt());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getAllById(List)}
     */
    @Test
    void testGetAllById() {
        assertTrue(productServiceImpl.getAllById(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getAllById(List)}
     */
    @Test
    void testGetAllById2() {
        when(productRepository.findById(anyInt())).thenReturn(new BulkProduct(1, "Name", 10.0d, true));

        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(2);
        assertEquals(1, productServiceImpl.getAllById(integerList).size());
        verify(productRepository).findById(anyInt());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getAll()}
     */
    @Test
    void testGetAll() {
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> actualAll = productServiceImpl.getAll();
        assertSame(productList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(productRepository).findAll();
    }
}

