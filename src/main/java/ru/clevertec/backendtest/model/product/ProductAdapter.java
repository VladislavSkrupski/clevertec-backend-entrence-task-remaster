package ru.clevertec.backendtest.model.product;

public class ProductAdapter extends Product {
    private Units unit;

    public ProductAdapter(int id, String name, double price, boolean isPromotional, String unit) {
        super(id, name, price, isPromotional);
        this.unit = getEnumUnit(unit);
    }

    @Override
    public String getUnit() {
        return unit.getUnit();
    }

    public Units getEnumUnit(){
        return unit;
    }

    private Units getEnumUnit(String unit) {
        return switch (unit) {
            case "кг" -> Units.BULK;
            case "л" -> Units.LIQUID;
            default -> Units.PIECE;
        };
    }
}
