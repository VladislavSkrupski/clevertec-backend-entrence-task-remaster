package ru.clevertec.backendtest.model.receipt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.clevertec.backendtest.model.discountCard.DiscountCard;
import ru.clevertec.backendtest.model.product.Goods;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

@Getter
@AllArgsConstructor
public class Receipt {
    private final String cashierID;
    private final String storeId;
    private final String storeAddress;
    private final GregorianCalendar date;
    private final List<Goods> items;
    private final DiscountCard discountCard;
    private final double totalCost;
    private final double totalCostWithDiscount;

    @Override
    public String toString() {
        final int RECEIPT_WIDTH = 65;
        StringBuilder stringBuilder = new StringBuilder();
        String title = "RECEIPT";
        String store = "Store: " + storeId;
        String address = "Address: " + storeAddress;
        String cashier = "Cashier: " + cashierID;
        String date = "Date: " + new SimpleDateFormat("dd/MM/yyyy").format(this.date.getTime());
        String time = "Time: " + new SimpleDateFormat("HH:mm:ss").format(this.date.getTime());
        String cashierDateFormattedString = "%-" + cashier.length() + "s%" + (RECEIPT_WIDTH - cashier.length()) + "s";
        String formatHeaderItems = "%-20s%5s%10s%10s%10s%10s";
        String formatItems = "%-20s%-5s%10s%10.2f%10s%10.2f";
        String footerFormat = "%-20s%45s";
        String total = String.format("%.2f", totalCost);
        String discount = (discountCard != null ? discountCard.getDiscount() : 0) + "%";
        String totalWithDiscount = String.format("%.2f", totalCostWithDiscount);
        stringBuilder.append("\n");
        stringBuilder.append(toCenter(RECEIPT_WIDTH, title)).append(title).append("\n");
        stringBuilder.append(toCenter(RECEIPT_WIDTH, store)).append(store).append("\n");
        stringBuilder.append(toCenter(RECEIPT_WIDTH, address)).append(address).append("\n");
        stringBuilder.append(String.format(cashierDateFormattedString, cashier, date)).append("\n");
        stringBuilder.append(String.format("%" + (RECEIPT_WIDTH) + "s", time)).append("\n");
        stringBuilder.append("-".repeat(RECEIPT_WIDTH)).append("\n");
        stringBuilder.append(
                        String.format(
                                formatHeaderItems,
                                "Name",
                                "Unit",
                                "QTY",
                                "Price",
                                "Promo",
                                "Cost"))
                .append("\n");
        stringBuilder.append("-".repeat(RECEIPT_WIDTH)).append("\n");
        for (Goods item : items) {
            String promo;
            if (item.isPromotional() && ((Item) item).getAmount() >= ((Item) item).getPromotionAmount()) {
                promo = ((Item) item).getPromotionDiscount() + "%";
            } else promo = "";
            stringBuilder.append(
                            String.format(formatItems,
                                    item.getName(),
                                    item.getUnit(),
                                    ((Item) item).getAmount(),
                                    item.getPrice(),
                                    promo,
                                    ((Item) item).getCost()))
                    .append("\n");
        }
        stringBuilder.append("=".repeat(RECEIPT_WIDTH)).append("\n");
        stringBuilder.append(String.format(footerFormat, "SUM", total)).append("\n");
        stringBuilder.append(String.format(footerFormat, "DISCOUNT", discount)).append("\n");
        stringBuilder.append(String.format(footerFormat, "TOTAL", totalWithDiscount)).append("\n");
        return stringBuilder.toString();
    }

    private String toCenter(int width, String str) {
        return " ".repeat((width - str.length()) / 2);
    }
}
