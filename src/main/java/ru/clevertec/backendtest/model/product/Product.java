package ru.clevertec.backendtest.model.product;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Product implements Goods {
    private final int id;
    @Pattern(regexp = "^\\S+[a-zA-Zа-яА-ЯёЁ\\s\\d-()/,]{3,}\\S+$")
    private final String name;
    private final double price;
    private final boolean isPromotional;
}
