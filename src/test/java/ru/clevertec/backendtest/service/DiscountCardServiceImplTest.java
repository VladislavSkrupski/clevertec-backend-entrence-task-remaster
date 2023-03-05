package ru.clevertec.backendtest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.backendtest.DAO.DiscountCardRepository;
import ru.clevertec.backendtest.models.discountCard.DiscountCard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscountCardServiceImplTest {

    @Mock
    private DiscountCardRepository discountCardRepository;

    @Test
    void getByIdShouldReturnDiscountCardByIdWhenIdExistsInDatabase() {
        when(discountCardRepository.findById(anyInt())).thenReturn(new DiscountCard(1, 0));

        DiscountCard discountCard = new DiscountCardServiceImpl(discountCardRepository).getById(1);

        assertAll(
                () -> assertThat(discountCard.getId()).isEqualTo(1),
                () -> assertThat(discountCard.getDiscount()).isEqualTo(0)
        );
    }
}