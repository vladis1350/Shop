package com.vladis1350.shop.mappers;

import com.vladis1350.shop.bean.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        return Product.builder()
                .id(resultSet.getLong(1))
                .name(resultSet.getString(2))
                .price(resultSet.getBigDecimal(3))
                .description(resultSet.getString(4))
                .discount(resultSet.getBigDecimal(5))
                .category(resultSet.getString(6)).build();
    }
}

