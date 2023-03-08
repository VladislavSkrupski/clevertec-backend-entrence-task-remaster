package ru.clevertec.backendtest.model.discountCard;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountCardTest {

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, 0, 1234, Integer.MAX_VALUE})
    void getIdShouldReturnCorrectId(int id) {
        DiscountCard discountCard = new DiscountCard(id, 0);

        assertThat(discountCard.getId()).isEqualTo(id);
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, 0, 10, Integer.MAX_VALUE})
    void getDiscountShouldReturnCorrectDiscount(int discount) {
        DiscountCard discountCard = new DiscountCard(0, discount);

        assertThat(discountCard.getDiscount()).isEqualTo(discount);
    }
}