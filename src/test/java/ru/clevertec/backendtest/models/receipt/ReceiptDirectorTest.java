package ru.clevertec.backendtest.models.receipt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.backendtest.InputArgsForReceipt;
import ru.clevertec.backendtest.service.DiscountCardService;
import ru.clevertec.backendtest.service.ProductService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;
import static ru.clevertec.backendtest.utils.CollectionsOfTestObjects.discountCardForTestReceipt;
import static ru.clevertec.backendtest.utils.CollectionsOfTestObjects.listOfProductsForTest;

@ExtendWith(MockitoExtension.class)
class ReceiptDirectorTest {
    @Mock
    private ProductService productService;

    @Mock
    private DiscountCardService discountCardService;

    @Mock
    private InputArgsForReceipt inputArgsForReceipt;

    private ReceiptDirector director;

    @BeforeEach
    void setUp() {
        director = new ReceiptDirector(discountCardService, productService);

        when(discountCardService.getById(1234)).thenReturn(discountCardForTestReceipt());

        for (int i = 0; i < listOfProductsForTest().size(); i++) {
            when(productService.getById(i + 1)).thenReturn(listOfProductsForTest().get(i));
        }

        when(inputArgsForReceipt.getDiscountCardId()).thenReturn(1234);
        when(inputArgsForReceipt.getProductAmountMap()).thenReturn(java.util.Map.of(1, 5, 2, 2, 3, 10));
    }

    @Test
    void buildReceipt() {
        Receipt receipt = director.buildReceipt(new ReceiptBuilder(), inputArgsForReceipt);

        assertAll(
                () -> assertThat(receipt.getCashierID()).isEqualTo("K021"),
                () -> assertThat(receipt.getStoreId()).isEqualTo("S025"),
                () -> assertThat(receipt.getStoreAddress()).isEqualTo("Minsk, str. Nemiga, 5"),
                () -> assertThat(receipt.getDiscountCard().getId()).isEqualTo(1234),
                () -> assertThat(receipt.getDiscountCard().getDiscount()).isEqualTo(10),
                () -> assertThat(receipt.getItems().size()).isEqualTo(3),
                () -> assertThat(receipt.getTotalCost()).isEqualTo(165),
                () -> assertThat(receipt.getTotalCostWithDiscount()).isEqualTo(148.5)
        );
    }
}