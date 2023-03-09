package ru.clevertec.backendtest.model.product;

public class BulkProduct extends Product {
    private static final Units unit = Units.BULK;

    public BulkProduct(int id, String name, double price, boolean isPromotional) {
        super(id, name, price, isPromotional);
    }

    @Override
    public String getUnit() {
        return unit.getUnit();
    }
}
