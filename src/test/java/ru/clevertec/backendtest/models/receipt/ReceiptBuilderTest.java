package ru.clevertec.backendtest.models.receipt;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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

    @Test
    void setItems() {
    }

    @Test
    void setDiscountCard() {
    }

    @Test
    void setTotalCost() {
    }

    @Test
    void setTotalCostWithDiscount() {
    }

    @Test
    void build() {
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
}