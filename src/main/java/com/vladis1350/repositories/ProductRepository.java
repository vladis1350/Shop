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

import java.sql.*;
import java.util.List;

@Repository
public class ProductRepository<T extends Product> implements Repositories<Product, Long> {
    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);
    private Statement statement;
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private PreparedStatement preparedStatement;

    @Override
    @Transactional
    public void save(Product product) throws SQLException {
        String insert = "INSERT INTO product (" + ProductDataConstant.PRODUCT_CATEGORY + "," + ProductDataConstant.PRODUCT_DESCRIPTION + "," +
                ProductDataConstant.PRODUCT_DISCOUNT + "," + ProductDataConstant.PRODUCT_NAME  + "," + ProductDataConstant.PRODUCT_PRICE + ") VALUES(?,?,?,?,?)";
        preparedStatement = databaseConnection.getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, product.getCategory());
        preparedStatement.setString(2, product.getDescription());
        preparedStatement.setString(3, String.valueOf(product.getDiscount()));
        preparedStatement.setString(4, product.getName());
        preparedStatement.setString(5, String.valueOf(product.getPrice()));

        preparedStatement.executeUpdate();
    }

    @Override
    @Transactional
    public List<Product> findAll() throws SQLException {
        ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery("SELECT * FROM product");
        return ResultSetConverter.convertToListProduct(resultSet);
    }

    @Override
    @Transactional
    public Product getById(Long id) throws SQLException {
        ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery("SELECT * FROM product WHERE id=" + id);
        return ResultSetConverter.convertToProduct(resultSet);
    }

    @Override
    @Transactional
    public void delete(Long id) throws SQLException {
        statement = databaseConnection.getDbConnection().createStatement();
        statement.executeUpdate(" DELETE FROM product WHERE id=" + id);
    }

    @Override
    @Transactional
    public void update(Product product) throws SQLException {
        statement = databaseConnection.getDbConnection().createStatement();
        statement.executeUpdate(" UPDATE product SET category='" + product.getCategory() + "', description='" + product.getDescription() +
                "', discount='" + product.getDiscount() + "',  name='" + product.getName() + "', price='" + product.getPrice() + "' " +
                " WHERE id=" + product.getId() + "");
    }

}
