package ru.clevertec.backendtest.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.backendtest.DAO.ProductRepository;
import ru.clevertec.backendtest.model.product.Product;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static ru.clevertec.backendtest.util.CollectionsOfTestObjects.listOfProductsFromDB;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Test
    void getByIdShouldReturnProductById() {
        when(productRepository.findById(anyInt())).thenReturn(listOfProductsFromDB().get(0));

        Product product = new ProductServiceImpl(productRepository).getById(1);

        assertAll(
                () -> assertThat(product.getId()).isEqualTo(listOfProductsFromDB().get(0).getId()),
                () -> assertThat(product.getName()).isEqualTo(listOfProductsFromDB().get(0).getName()),
                () -> assertThat(product.getPrice()).isEqualTo(listOfProductsFromDB().get(0).getPrice()),
                () -> assertThat(product.isPromotional()).isEqualTo(listOfProductsFromDB().get(0).isPromotional()),
                () -> assertThat(product.getUnit()).isEqualTo(listOfProductsFromDB().get(0).getUnit())
        );
    }

    @Test
    void getAllByIdShouldReturnListOfProductsByIds() {
        when(productRepository.findById(anyInt())).thenReturn(listOfProductsFromDB().get(0));

        List<Product> products = new ProductServiceImpl(productRepository).getAllById(List.of(1, 2, 3, 4));

        assertThat(products).hasSize(4);
    }

    @Test
    void getAllShouldReturnListOfProductsFromFakeDB() {
        when(productRepository.findAll()).thenReturn(listOfProductsFromDB());

        List<Product> products = new ProductServiceImpl(productRepository).getAll();

        assertThat(products).hasSize(listOfProductsFromDB().size());
    }
}