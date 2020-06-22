package com.vladis1350.repositories;

import com.vladis1350.bean.Product;
import com.vladis1350.configDatabase.DatabaseConnection;
import com.vladis1350.constants.ProductDataConstant;
import com.vladis1350.converters.ResultSetConverter;
import com.vladis1350.repositories.interfaces.Repositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

@Repository
@Transactional
public class ProductRepository<T extends Product> implements Repositories<Product, Long> {
    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);
    private Statement statement;
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private PreparedStatement preparedStatement;

    @Override
    public void save(Product product) throws SQLException {
        String insert = "INSERT INTO product (" + ProductDataConstant.PRODUCT_NAME + "," + ProductDataConstant.PRODUCT_PRICE + "," +
                ProductDataConstant.PRODUCT_DESCRIPTION + "," + ProductDataConstant.PRODUCT_DISCOUNT  + "," + ProductDataConstant.PRODUCT_CATEGORY + ") VALUES(?,?,?,?,?)";
        preparedStatement = databaseConnection.getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, String.valueOf(product.getPrice()));
        preparedStatement.setString(3, product.getDescription());
        preparedStatement.setString(4, String.valueOf(product.getDiscount()));
        preparedStatement.setString(5, product.getCategory());

        preparedStatement.executeUpdate();
    }

    @Override
    public List<Product> findAll() throws SQLException {
        ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery(
                "SELECT id_product, product_name, price, description, discount, categories.name_category as category " +
                        "FROM product, categories " +
                        "WHERE product.id_category=categories.id_category");
        return ResultSetConverter.convertToListProduct(resultSet);
    }

    @Override
    public Product getById(Long id) throws SQLException {
        ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery("SELECT * FROM product WHERE id_product=" + id);
        return ResultSetConverter.convertToProduct(resultSet);
    }

    @Override
    public void delete(Long id) throws SQLException {
        statement = databaseConnection.getDbConnection().createStatement();
        statement.executeUpdate(" DELETE FROM product WHERE id_product=" + id);
    }

    @Override
    public void update(Product product) throws SQLException {
        statement = databaseConnection.getDbConnection().createStatement();
        statement.executeUpdate(" UPDATE product SET product_name='" + product.getName() + "', price='" + product.getPrice() +
                "', description='" + product.getDescription() + "',  discount='" + product.getDiscount() + "', id_category='" + product.getCategory() + "' " +
                " WHERE id_product=" + product.getId() + "");
    }

    public void changeDiscountForCategories(Long id_category, BigDecimal discount) throws SQLException {
        statement = databaseConnection.getDbConnection().createStatement();
        statement.executeUpdate("UPDATE product SET discount=" + discount + " WHERE id_category=" + id_category);
    }
}
