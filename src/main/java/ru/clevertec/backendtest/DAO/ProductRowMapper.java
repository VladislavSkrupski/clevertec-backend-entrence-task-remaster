package ru.clevertec.backendtest.DAO;

import ru.clevertec.backendtest.models.product.factories.BulkProductFactory;
import ru.clevertec.backendtest.models.product.factories.LiquidProductFactory;
import ru.clevertec.backendtest.models.product.factories.PieceProductFactory;
import ru.clevertec.backendtest.models.product.Product;
import ru.clevertec.backendtest.models.product.Units;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException, IllegalStateException {
        int id = rs.getInt("id");
        String name = rs.getString("product_name");
        double price = rs.getDouble("price");
        boolean isPromotional = rs.getBoolean("is_promotional");
        Units unit = Units.valueOf(rs.getString("product_unit"));

        switch (unit) {
            case BULK -> {
                return new BulkProductFactory().createProduct(id, name, price, isPromotional);
            }
            case LIQUID -> {
                return new LiquidProductFactory().createProduct(id, name, price, isPromotional);
            }
            case PIECE -> {
                return new PieceProductFactory().createProduct(id, name, price, isPromotional);
            }
            default -> throw new IllegalStateException("Unexpected value: " + unit);
        }
    }
}
