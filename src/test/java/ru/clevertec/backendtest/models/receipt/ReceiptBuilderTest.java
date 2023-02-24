package ru.clevertec.backendtest.models.receipt;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import ru.clevertec.backendtest.models.discountCard.DiscountCard;
import ru.clevertec.backendtest.models.product.Goods;
import ru.clevertec.backendtest.utils.CollectionsOfTestObjects;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static ru.clevertec.backendtest.utils.CollectionsOfTestObjects.discountCardForTestReceipt;
import static ru.clevertec.backendtest.utils.CollectionsOfTestObjects.listOfGoodsForTestReceipt;

class ReceiptBuilderTest {

    private ReceiptBuilder receiptBuilder;

    @BeforeEach
    void setUp() {
        receiptBuilder = new ReceiptBuilder();
    }

    @AfterEach
    void tearDown() {
        receiptBuilder = null;
    }

    @Test
    void setCashierIdShouldSetCorrectCashierIdWhenCalled() {
        receiptBuilder.setCashierId();

        String actual = (String) getFieldData(receiptBuilder, "cashierID");

        assertThat(actual).isEqualTo("K021");
    }

    @Test
    void setStoreIdShouldSetCorrectStoreIdWhenCalled() {
        receiptBuilder.setStoreId();

        String actual = (String) getFieldData(receiptBuilder, "storeId");

        assertThat(actual).isEqualTo("S025");
    }

    @Test
    void setStoreAddressShouldSetCorrectStoreAddressWhenCalled() {
        receiptBuilder.setStoreAddress();

        String actual = (String) getFieldData(receiptBuilder, "storeAddress");

        assertThat(actual).isEqualTo("Minsk, str. Nemiga, 5");
    }

    @Test
    void setDateShouldSetCorrectDateWhenCalled() {
        receiptBuilder.setDate();

        GregorianCalendar expected = new GregorianCalendar();
        GregorianCalendar actual = (GregorianCalendar) getFieldData(receiptBuilder, "date");

        assertAll(
                () -> assertThat(actual.get(GregorianCalendar.YEAR))
                        .isEqualTo(expected.get(GregorianCalendar.YEAR)),
                () -> assertThat(actual.get(GregorianCalendar.MONTH))
                        .isEqualTo(expected.get(GregorianCalendar.MONTH)),
                () -> assertThat(actual.get(GregorianCalendar.DAY_OF_MONTH))
                        .isEqualTo(expected.get(GregorianCalendar.DAY_OF_MONTH))
        );
    }

    @Nested
    class SetItemsTest {
        @ParameterizedTest
        @EmptySource
        void setItemsShouldSetEmptyListWhenCalled(List<Goods> items) {
            receiptBuilder.setItems(items);

            Object actual = getFieldData(receiptBuilder, "items");

            assertThat((List<?>) actual).isEmpty();
        }

        @Test
        void setItemsShouldSetCorrectItemsWhenCalled() {
            receiptBuilder.setItems(listOfGoodsForTestReceipt());

            Object actual = getFieldData(receiptBuilder, "items");

            assertThat((List<?>) actual).hasSize(listOfGoodsForTestReceipt().size());
        }
    }

    @Nested
    class SetDiscountCardTest {
        @ParameterizedTest
        @NullSource
        void setDiscountCardShouldSetNullWhenCalled(DiscountCard discountCard) {
            receiptBuilder.setDiscountCard(discountCard);

            Object actual = getFieldData(receiptBuilder, "discountCard");

            assertThat(actual).isNull();
        }

        @Test
        void setDiscountCardShouldSetCorrectDiscountCardWhenCalled() {
            receiptBuilder.setDiscountCard(discountCardForTestReceipt());

            DiscountCard actual = (DiscountCard) getFieldData(receiptBuilder, "discountCard");

            assertAll(
                    () -> assertThat(actual.getId()).isEqualTo(discountCardForTestReceipt().getId()),
                    () -> assertThat(actual.getDiscount()).isEqualTo(discountCardForTestReceipt().getDiscount())
            );
        }
    }

    @Nested
    class SetTotalCostTest {
        @ParameterizedTest
        @EmptySource
        void setTotalCostShouldSetZeroWhenSetItemsCalledWithEmptyList(List<Goods> items) {
            receiptBuilder.setItems(items).setTotalCost();

            Double actual = (Double) getFieldData(receiptBuilder, "totalCost");

            assertThat(actual).isEqualTo(0);
        }

        @Test
        void setTotalCostShouldSetCorrectTotalCostWhenCalled() {
            receiptBuilder.setItems(listOfGoodsForTestReceipt()).setTotalCost();

            Double actual = (Double) getFieldData(receiptBuilder, "totalCost");
            Double expected = listOfGoodsForTestReceipt().stream()
                    .mapToDouble(goods -> ((Item) goods).getCost())
                    .sum();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @ParameterizedTest
    @MethodSource("provideArguments")
    void setTotalCostWithDiscountShouldSetCorrectTotalCostWithDiscountWhenCalled(List<Goods> items, DiscountCard discountCard) {
        receiptBuilder.setItems(items).setDiscountCard(discountCard).setTotalCost().setTotalCostWithDiscount();

        Double actual = (Double) getFieldData(receiptBuilder, "totalCostWithDiscount");
        Double expected = items.stream()
                .mapToDouble(goods -> ((Item) goods).getCost())
                .sum() * (Objects.nonNull(discountCard) ? (double) (100 - discountCard.getDiscount()) / 100 : 1);

        assertThat(actual).isEqualTo(expected);
    }

    @Nested
    class buildTest {
        @Test
        void buildShouldReturnReceiptWithNullFieldsWhenCalledWithoutSetFields() {
            Receipt actual = receiptBuilder.build();

            assertAll(
                    () -> assertThat(actual.getCashierID()).isNull(),
                    () -> assertThat(actual.getStoreId()).isNull(),
                    () -> assertThat(actual.getStoreAddress()).isNull(),
                    () -> assertThat(actual.getDate()).isNull(),
                    () -> assertThat(actual.getItems()).isNull(),
                    () -> assertThat(actual.getDiscountCard()).isNull(),
                    () -> assertThat(actual.getTotalCost()).isEqualTo(0),
                    () -> assertThat(actual.getTotalCostWithDiscount()).isEqualTo(0)
            );
        }

        @Test
        void buildShouldReturnReceiptWithCorrectFieldsWhenCalledWithSetFields() {
            Receipt actual = CollectionsOfTestObjects.receiptForTestReceipt();

            assertAll(
                    () -> assertThat(actual.getCashierID()).isEqualTo("K021"),
                    () -> assertThat(actual.getStoreId()).isEqualTo("S025"),
                    () -> assertThat(actual.getStoreAddress()).isEqualTo("Minsk, str. Nemiga, 5"),
                    () -> assertThat(actual.getDate()).isLessThan(new GregorianCalendar()),
                    () -> assertThat(actual.getItems()).hasSize(listOfGoodsForTestReceipt().size()),
                    () -> assertThat(actual.getDiscountCard()).isNotNull(),
                    () -> assertThat(actual.getTotalCost()).isEqualTo(165),
                    () -> assertThat(actual.getTotalCostWithDiscount()).isEqualTo(148.5)
            );
        }
    }

    private static Object getFieldData(Object object, String fieldName) {
        try {
            Field field = ReceiptBuilder.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Stream<? extends Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(new ArrayList<>(), null),
                Arguments.of(new ArrayList<>(), discountCardForTestReceipt()),
                Arguments.of(listOfGoodsForTestReceipt(), null),
                Arguments.of(listOfGoodsForTestReceipt(), discountCardForTestReceipt())
        );
    }
}