package ru.clevertec.backendtest.model.receipt;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.backendtest.model.product.Product;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.clevertec.backendtest.util.CollectionsOfTestObjects.listOfGoodsForTestReceipt;
import static ru.clevertec.backendtest.util.CollectionsOfTestObjects.listOfProductsForTest;

@ExtendWith(MockitoExtension.class)
class ItemTest {
    @Mock
    private Product product;

    @Nested
    class AddItemAmountTest {

        @ParameterizedTest
        @MethodSource("ru.clevertec.backendtest.model.receipt.ItemTest#provideArgumentsWithExpectedValue")
        void getAmountShouldReturnCorrectAmountFromItem(Item item) {
            Double actual = item.getAmount();

            assertThat(actual).matches(aDouble -> List.of(2.0, 5.0, 10.0).contains(aDouble));
        }

        @ParameterizedTest
        @ValueSource(doubles = {Double.MIN_VALUE, 0.0, 10.0f, Double.MAX_VALUE})
        void getAmountShouldReturnCorrectAmount(double amount) {
            Item item = new Item(product, amount);

            assertThat(item.getAmount()).isEqualTo(amount);
        }
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsWithExpectedValue")
    void getCostShouldReturnCorrectCost(Item item) {
        assertThat(item.getCost()).matches((cost) -> List.of(45.0, 20.0, 100.0).contains(cost));
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsWithExpectedValue")
    void getIdsShouldReturnCorrectId(Item item) {
        assertThat(item.getId()).matches((id) -> List.of(1, 2, 3).contains(id));
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsWithExpectedValue")
    void getNameShouldReturnCorrectName(Item item, int index) {
        String expected = listOfProductsForTest().get(index).getName();
        String actual = item.getName();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsWithExpectedValue")
    void getPriceShouldReturnCorrectPrice(Item item) {
        assertThat(item.getPrice()).isEqualTo(10.0);
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsWithExpectedValue")
    void isPromotionalShouldReturnCorrectPromotional(Item item, int index) {
        boolean expected = listOfProductsForTest().get(index).isPromotional();
        boolean actual = item.isPromotional();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getPromotionDiscountShouldReturnCorrectPromotionDiscount() {
        Item item = new Item(product, 0);

        assertThat(item.getPromotionDiscount()).isEqualTo(10);
    }

    @Test
    void getPromotionAmountShouldReturnCorrectPromotionAmount() {
        Item item = new Item(product, 0);

        assertThat(item.getPromotionAmount()).isEqualTo(5);
    }

    public static Stream<Arguments> provideArgumentsWithExpectedValue() {
        return Stream.of(
                Arguments.of(listOfGoodsForTestReceipt().get(0), 0),
                Arguments.of(listOfGoodsForTestReceipt().get(1), 1),
                Arguments.of(listOfGoodsForTestReceipt().get(2), 2)
        );
    }
}
