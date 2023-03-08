package ru.clevertec.backendtest.model.receipt;

import ru.clevertec.backendtest.InputArgsForReceipt;
import ru.clevertec.backendtest.service.DiscountCardService;
import ru.clevertec.backendtest.service.ProductService;
import ru.clevertec.backendtest.model.discountCard.DiscountCard;
import ru.clevertec.backendtest.model.product.Goods;
import ru.clevertec.backendtest.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReceiptDirector {
    private final DiscountCardService discountCardService;
    private final ProductService productService;

    @Autowired
    public ReceiptDirector(DiscountCardService discountCardService, ProductService productService) {
        this.discountCardService = discountCardService;
        this.productService = productService;
    }

    public Receipt buildReceipt(Builder builder, InputArgsForReceipt inputArgsForReceipt) {
        return builder
                .setCashierId()
                .setStoreId()
                .setStoreAddress()
                .setDate()
                .setItems(getItems(inputArgsForReceipt))
                .setDiscountCard(getDiscountCard(inputArgsForReceipt))
                .setTotalCost()
                .setTotalCostWithDiscount()
                .build();
    }

    private DiscountCard getDiscountCard(InputArgsForReceipt inputArgsForReceipt) {
        DiscountCard discountCard;
        discountCard = discountCardService.getById(inputArgsForReceipt.getDiscountCardId());
        return discountCard;
    }

    private List<Goods> getItems(InputArgsForReceipt inputArgsForReceipt) {
        List<Goods> items = new ArrayList<>();
        for (int id : inputArgsForReceipt.getProductAmountMap().keySet()) {
            Product product = productService.getById(id);
            if (product != null) {
                items.add(new Item(product, inputArgsForReceipt.getProductAmountMap().get(id)));
            }
        }
        return items;
    }
}
