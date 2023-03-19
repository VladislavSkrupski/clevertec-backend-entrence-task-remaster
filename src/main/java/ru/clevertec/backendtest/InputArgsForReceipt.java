package ru.clevertec.backendtest;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InputArgsForReceipt {
    private int discountCardId = -1;
    private final Map<Integer, Integer> productAmountMap = new HashMap<>();

    public InputArgsForReceipt() {
    }

    public static InputArgsForReceipt getInputArgsForReceipt(List<Integer> ids, List<Integer> quantities, int card) {
        InputArgsForReceipt inputArgsForReceipt = new InputArgsForReceipt();
        Map<Integer, Integer> productQuantitiesMap = new HashMap<>();
        if (card > 0) inputArgsForReceipt.setDiscountCardId(card);
        int paramListLength = Math.min(ids.size(), quantities.size());
        for (int i = 0; i < paramListLength; i++) {
            productQuantitiesMap.put(ids.get(i), quantities.get(i));
        }
        inputArgsForReceipt.setProductAmountMap(productQuantitiesMap);
        return inputArgsForReceipt;
    }

    public int getDiscountCardId() {
        return discountCardId;
    }

    public Map<Integer, Integer> getProductAmountMap() {
        return productAmountMap;
    }

    public void setDiscountCardId(int discountCardId) {
        this.discountCardId = discountCardId;
    }

    public void setDiscountCardId(String discountCardData) {
        String[] parameters = discountCardData.split("-");
        this.discountCardId = Integer.parseInt(parameters[1]);
    }

    public void setProductAmountMap(int id, int quantity) {
        if (this.productAmountMap.containsKey(id)) {
            this.productAmountMap.put(id, quantity + this.productAmountMap.get(id));
        } else {
            this.productAmountMap.put(id, quantity);
        }
    }

    public void setProductAmountMap(String productArg) {
        String[] parameters = productArg.split("-");
        setProductAmountMap(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]));
    }

    public void setProductAmountMap(Map<Integer, Integer> productAmountMap) {
        productAmountMap.forEach(this::setProductAmountMap);
    }
}
