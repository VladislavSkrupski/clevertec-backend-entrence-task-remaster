package ru.clevertec.backendtest.models.receipt;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.clevertec.backendtest.models.discountCard.DiscountCard;
import ru.clevertec.backendtest.models.product.Goods;
import ru.clevertec.backendtest.utils.ReceiptTestBuilder;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ReceiptTest {
    private Receipt receipt;

    @AfterEach
    void tearDown() {
        receipt = null;
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "Test", "Test Test "})
    void getCashierIDShouldReturnCorrectCashierId(String cashierId) {
        receipt = ReceiptTestBuilder.aReceipt().withCashierId(cashierId).build();

        assertThat(receipt.getCashierID()).isEqualTo(cashierId);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "Test", "Test Test "})
    void getStoreIdShouldReturnCorrectStoreId(String storeId) {
        receipt = ReceiptTestBuilder.aReceipt().withStoreId(storeId).build();

        assertThat(receipt.getStoreId()).isEqualTo(storeId);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "Test", "Test Test "})
    void getStoreAddressShouldReturnCorrectStoreAddress(String storeAddress) {
        receipt = ReceiptTestBuilder.aReceipt().withStoreAddress(storeAddress).build();

        assertThat(receipt.getStoreAddress()).isEqualTo(storeAddress);
    }

    @ParameterizedTest
    @MethodSource("dateProvider")
    void getDateShouldReturnCorrectDate(GregorianCalendar date) {
        receipt = ReceiptTestBuilder.aReceipt().withDate(date).build();

        assertThat(receipt.getDate()).isEqualTo(date);
    }

    @Nested
    class ReceiptItemsTest {
        @ParameterizedTest
        @NullAndEmptySource
        void getItemsShouldReturnNullOrEmptyIfItemsIsNullOrEmpty(List<Goods> items) {
            receipt = ReceiptTestBuilder.aReceipt().withItems(items).build();

            assertThat(receipt.getItems()).isEqualTo(items);
        }

        @Test
        void getItemsShouldContainsInstancesOfGoods() {
            receipt = ReceiptTestBuilder.aReceipt().build();

            receipt.getItems().stream()
                    .forEach(
                            item -> assertThat(item).isInstanceOf(Goods.class)
                    );
        }
    }

    @Nested
    class ReceiptDiscountCardTest {
        @ParameterizedTest
        @NullSource
        void getDiscountCardShouldReturnNullIfDiscountCardIsNull(DiscountCard discountCard) {
            receipt = ReceiptTestBuilder.aReceipt().withDiscountCard(discountCard).build();

            assertThat(receipt.getDiscountCard()).isNull();
        }


        @ParameterizedTest
        @MethodSource("ru.clevertec.backendtest.models.receipt.ReceiptTest#discountCardProvider")
        void getDiscountCardShouldReturnCorrectDiscountCard(DiscountCard discountCard) {
            receipt = ReceiptTestBuilder.aReceipt().withDiscountCard(discountCard).build();

            assertAll(
                    () -> assertThat(receipt.getDiscountCard().getId()).isEqualTo(discountCard.getId()),
                    () -> assertThat(receipt.getDiscountCard().getDiscount()).isEqualTo(discountCard.getDiscount())
            );
        }
    }

    @ParameterizedTest
    @NullSource
    @MethodSource("discountCardProvider")
    void hasDiscountShouldReturnTrueIfDiscountCardIsNotNull(DiscountCard discountCard) {
        receipt = ReceiptTestBuilder.aReceipt().withDiscountCard(discountCard).build();

        assertThat(receipt.hasDiscount()).isEqualTo(discountCard != null);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.MIN_VALUE, 0, 10.0, Double.MAX_VALUE})
    void getTotalCostShouldReturnCorrectTotalCost(double totalCost) {
        receipt = ReceiptTestBuilder.aReceipt().withTotalCost(totalCost).build();

        assertThat(receipt.getTotalCost()).isEqualTo(totalCost);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.MIN_VALUE, 0, 10.0, Double.MAX_VALUE})
    void getTotalCostWithDiscountShouldReturnCorrectTotalCostWithDiscount(double totalCostWithDiscount) {
        receipt = ReceiptTestBuilder.aReceipt().withTotalCostWithDiscount(totalCostWithDiscount).build();

        assertThat(receipt.getTotalCostWithDiscount()).isEqualTo(totalCostWithDiscount);
    }

    @Test
    void print() {
        receipt = ReceiptTestBuilder.aReceipt().build();

        assertThat(receipt.print()).isEqualTo(ReceiptTestBuilder.TEST_RECEIPT);
    }

    private static Stream<GregorianCalendar> dateProvider() {
        return Stream.of(
                new GregorianCalendar(1970, Calendar.FEBRUARY, 15),
                new GregorianCalendar(2020, Calendar.JULY, 17, 14, 30, 0),
                new GregorianCalendar(2023, Calendar.FEBRUARY, 21, 19, 10)
        );
    }

    private static Stream<DiscountCard> discountCardProvider() {
        return Stream.of(
                new DiscountCard(1234, 10),
                new DiscountCard(-1, 0)
        );
    }
}