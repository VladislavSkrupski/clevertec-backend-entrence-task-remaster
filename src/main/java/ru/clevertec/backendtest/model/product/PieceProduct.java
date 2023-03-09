package ru.clevertec.backendtest.model.product;

public class PieceProduct extends Product {
    private static final Units unit = Units.PIECE;

    public PieceProduct(int id, String name, double price, boolean isPromotional) {
        super(id, name, price, isPromotional);
    }

    @Override
    public String getUnit() {
        return unit.getUnit();
    }
}
