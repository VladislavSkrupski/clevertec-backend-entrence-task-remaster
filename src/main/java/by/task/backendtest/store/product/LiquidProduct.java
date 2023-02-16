package by.task.backendtest.store.product;

public class LiquidProduct extends Product {
    private static final Units unit = Units.LIQUID;

    public LiquidProduct(int id, String name, double price, boolean isPromotional) {
        super(id, name, price, isPromotional);
    }

    @Override
    public String getUnit() {
        return unit.getUnit();
    }
}
