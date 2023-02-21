package ru.clevertec.backendtest.models.utils;

import ru.clevertec.backendtest.models.discountCard.DiscountCard;
import ru.clevertec.backendtest.models.product.Goods;
import ru.clevertec.backendtest.models.receipt.Receipt;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ReceiptTestBuilder implements TestBuilder<Receipt> {
    public static final String TEST_RECEIPT =
            """
                                                 
                                                 RECEIPT
                                               Store: S025
                                     Address: Minsk, str. Nemiga, 5
                    Cashier: K021                                    Date: 01/01/2020
                                                                       Time: 12:00:00
                    -----------------------------------------------------------------
                    Name                 Unit       QTY     Price     Promo      Cost
                    -----------------------------------------------------------------
                    bulk1               кг          5.0     10,00       10%     45,00
                    liquid1             л           2.0     10,00               20,00
                    piece1              шт.        10.0     10,00              100,00
                    =================================================================
                    SUM                                                       1000,00
                    DISCOUNT                                                      10%
                    TOTAL                                                      900,00
                    """;

    private String cashierId = "K021";
    private String storeId = "S025";
    private String storeAddress = "Minsk, str. Nemiga, 5";
    private GregorianCalendar date = new GregorianCalendar(2020, Calendar.JANUARY, 1, 12, 0);
    private List<Goods> items = CollectionsOfTestObjects.listOfGoodsForTestReceipt();
    private DiscountCard discountCard = new DiscountCard(1234, 10);
    private double totalCost = 1000.0;
    private double totalCostWithDiscount = 900.0;

    private ReceiptTestBuilder() {
    }

    public static ReceiptTestBuilder aReceipt() {
        return new ReceiptTestBuilder();
    }

    public ReceiptTestBuilder withCashierId(String cashierId) {
        this.cashierId = cashierId;
        return this;
    }

    public ReceiptTestBuilder withStoreId(String storeId) {
        this.storeId = storeId;
        return this;
    }

    public ReceiptTestBuilder withStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
        return this;
    }

    public ReceiptTestBuilder withDate(GregorianCalendar date) {
        this.date = date;
        return this;
    }

    public ReceiptTestBuilder withItems(List<Goods> items) {
        this.items = items;
        return this;
    }

    public ReceiptTestBuilder withDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
        return this;
    }

    public ReceiptTestBuilder withTotalCost(double totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    public ReceiptTestBuilder withTotalCostWithDiscount(double totalCostWithDiscount) {
        this.totalCostWithDiscount = totalCostWithDiscount;
        return this;
    }

    @Override
    public Receipt build() {
        return new Receipt(
                cashierId,
                storeId,
                storeAddress,
                date,
                items,
                discountCard,
                totalCost,
                totalCostWithDiscount
        );
    }
}
