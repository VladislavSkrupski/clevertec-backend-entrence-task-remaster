package ru.clevertec.backendtest.models.receipt;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.backendtest.models.product.*;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ItemTest {
    @Mock
    private Product product;

    @Nested
    class AddItemAmountTest {

        @ParameterizedTest
        @MethodSource("ru.clevertec.backendtest.models.receipt.ItemTest#provideArguments")
        void getAmountShouldReturnCorrectAmount(Item item) {
            assertThat(item.getAmount()).matches((amount) -> List.of(2.0, 5.0, 10.0).contains(amount));
        }

        @ParameterizedTest
        @ValueSource(doubles = {Double.MIN_VALUE, 0.0, 10.0f, Double.MAX_VALUE})
        void getAmountShouldReturnCorrectAmount(double amount) {
            Item item = new Item(product, amount);

            assertThat(item.getAmount()).isEqualTo(amount);
        }
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void getCostShouldReturnCorrectCost(Item item) {
        assertThat(item.getCost()).matches((cost) -> List.of(450.0, 200.0, 1000.0).contains(cost));
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void getIdsShouldReturnCorrectId(Item item) {
        assertThat(item.getId()).matches((id) -> List.of(1, 2, 3).contains(id));
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void getNameShouldReturnCorrectName(Item item) {
        assertThat(item.getName()).isEqualTo("Test");
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void getPriceShouldReturnCorrectPrice(Item item) {
        assertThat(item.getPrice()).isEqualTo(100.0);
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void isPromotionalShouldReturnCorrectPromotional(Item item) {
        if (item.getUnit().equals(Units.PIECE.getUnit())) {
            assertThat(item.isPromotional()).isFalse();
        } else {
            assertThat(item.isPromotional()).isTrue();
        }
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

    public static Stream<Item> provideArguments() {
        return Stream.of(
                new Item(new BulkProduct(1, "Test", 100.0, true), 5),
                new Item(new LiquidProduct(2, "Test", 100.0, true), 2),
                new Item(new PieceProduct(3, "Test", 100.0, false), 10)
        );
    }
}