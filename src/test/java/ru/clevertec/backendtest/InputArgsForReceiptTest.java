package ru.clevertec.backendtest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InputArgsForReceiptTest {
    private InputArgsForReceipt inputArgs;

    @Spy
    private InputArgsForReceipt inputArgsMock;

    @Captor
    private ArgumentCaptor<Integer> discountCardIdIntArgumentCaptor;

    @Captor
    private ArgumentCaptor<String> discountCardIdStringArgumentCaptor;

    @Captor
    private ArgumentCaptor<Integer> productIdIntArgumentCaptor;

    @Captor
    private ArgumentCaptor<Integer> productQuantityIntArgumentCaptor;

    @Captor
    private ArgumentCaptor<String> productAmountStringArgumentCaptor;

    @Captor
    private ArgumentCaptor<Map<Integer, Integer>> productAmountMapArgumentCaptor;

    @BeforeEach
    void setUp() {
        inputArgs = new InputArgsForReceipt();
    }

    @AfterEach
    void tearDown() {
        inputArgs = null;
    }

    @Nested
    class GetDiscountCardIdTest {
        @Test
        void getDiscountCardIdShouldReturnMinusOneIfDiscountCardIdNotSet() {
            int actual = inputArgs.getDiscountCardId();

            assertThat(actual).isEqualTo(-1);
        }

        @ParameterizedTest
        @ValueSource(ints = {Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE})
        void getDiscountCardIdShouldReturnDiscountCardIdIfSet(int discountCardId) {
            inputArgs.setDiscountCardId(discountCardId);

            int actual = inputArgs.getDiscountCardId();

            assertEquals(discountCardId, actual);
        }
    }

    @Nested
    class GetProductAmountMapTest {
        @Test
        void getProductAmountMapShouldReturnEmptyMapIfProductAmountMapNotSet() {
            assertThat(inputArgs.getProductAmountMap()).isEmpty();
        }

        @Test
        void getProductAmountMapShouldReturnProductAmountMapIfSet() {
            inputArgs.setProductAmountMap(Map.of(1, 1));

            assertThat(inputArgs.getProductAmountMap()).containsEntry(1, 1);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE})
    void setDiscountCardIdWithIntArgShouldSetDiscountCardId(int id) {
        inputArgsMock.setDiscountCardId(id);

        verify(inputArgsMock).setDiscountCardId(discountCardIdIntArgumentCaptor.capture());

        assertThat(discountCardIdIntArgumentCaptor.getValue()).isEqualTo(id);
        assertThat(inputArgsMock.getDiscountCardId()).isEqualTo(id);
    }

    @Nested
    class SetDiscountCardIdWithWrongIntArgTest {
        @Test
        void setDiscountCardIdWithStringArgShouldSetDiscountCardId() {
            inputArgsMock.setDiscountCardId("card-1234");

            verify(inputArgsMock).setDiscountCardId(discountCardIdStringArgumentCaptor.capture());

            assertThat(discountCardIdStringArgumentCaptor.getValue()).isEqualTo("card-1234");
            assertThat(inputArgsMock.getDiscountCardId()).isEqualTo(1234);
        }

        @ParameterizedTest
        @ValueSource(strings = {"card1234", "card-", "", "1234-card"})
        void setDiscountCardIdWithWrongStringArgShouldThrowRuntimeException(String arg) {
            assertThrows(RuntimeException.class, () -> inputArgsMock.setDiscountCardId(arg));
        }
    }

    @Nested
    class SetProductAmountMapWithIntArgsTest {
        @ParameterizedTest
        @CsvSource(value = {
                "-2147483648, -2147483648",
                "-1,-1",
                "0,0",
                "1,1",
                "2147483647, 2147483647"})
        void setProductAmountMapWithIntArgsShouldSetProductAmountMap(int key, int value) {
            inputArgsMock.setProductAmountMap(key, value);

            verify(inputArgsMock).setProductAmountMap(productIdIntArgumentCaptor.capture(), productQuantityIntArgumentCaptor.capture());

            assertThat(productIdIntArgumentCaptor.getValue()).isEqualTo(key);
            assertThat(productQuantityIntArgumentCaptor.getValue()).isEqualTo(value);
            assertThat(inputArgsMock.getProductAmountMap()).containsEntry(key, value);
        }
    }

    @Nested
    class SetProductAmountMapWithStringArgTest {
        @Test
        void setProductAmountMapWithStringArg() {
            inputArgsMock.setProductAmountMap("1-1");

            verify(inputArgsMock).setProductAmountMap(productAmountStringArgumentCaptor.capture());

            assertThat(productAmountStringArgumentCaptor.getValue()).isEqualTo("1-1");
            assertThat(inputArgsMock.getProductAmountMap()).containsEntry(1, 1);
        }

        @ParameterizedTest
        @ValueSource(strings = {"1", "abc-1", "1-abc", ""})
        void setProductAmountMapWithWrongStringArg(String arg) {
            assertThrows(RuntimeException.class, () -> inputArgsMock.setProductAmountMap(arg));
        }
    }

    @Nested
    class SetProductAmountMapWithMapArgTest {
        @Test
        void setProductAmountMapWithMapArgShouldSetProductAmountMap() {
            inputArgsMock.setProductAmountMap(Map.of(1, 1));

            verify(inputArgsMock).setProductAmountMap(productAmountMapArgumentCaptor.capture());

            assertThat(productAmountMapArgumentCaptor.getValue()).containsEntry(1, 1);
            assertThat(inputArgsMock.getProductAmountMap()).containsEntry(1, 1);
        }

        @ParameterizedTest
        @NullSource
        void setProductAmountMapWithNullArgShouldThrowNullPointerException(Map<Integer, Integer> arg) {
            assertThrows(NullPointerException.class, () -> inputArgsMock.setProductAmountMap(arg));
        }

        @ParameterizedTest
        @EmptySource
        void setProductAmountMapWithEmptyArgShouldReturnEmptyMap(Map<Integer, Integer> arg) {
            inputArgsMock.setProductAmountMap(arg);

            verify(inputArgsMock).setProductAmountMap(productAmountMapArgumentCaptor.capture());

            assertThat(productAmountMapArgumentCaptor.getValue()).isEmpty();
            assertThat(inputArgsMock.getProductAmountMap()).isEmpty();
        }
    }
}