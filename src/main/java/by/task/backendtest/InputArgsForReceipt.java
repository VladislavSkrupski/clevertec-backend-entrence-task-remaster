package by.task.backendtest;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InputArgsForReceipt {
    int discountCardId = -1;
    Map<Integer, Integer> productAmountMap = new HashMap<>();

    public InputArgsForReceipt() {
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
