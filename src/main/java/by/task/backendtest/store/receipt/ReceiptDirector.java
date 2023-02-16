package by.task.backendtest.store.receipt;

import by.task.backendtest.InputArgsForReceipt;
import by.task.backendtest.service.DiscountCardService;
import by.task.backendtest.service.ProductService;
import by.task.backendtest.store.discountCard.DiscountCard;
import by.task.backendtest.store.product.Goods;
import by.task.backendtest.store.product.Product;
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
