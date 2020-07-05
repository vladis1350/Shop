package com.vladis1350.shop.repositories;

import com.vladis1350.shop.bean.Product;
import com.vladis1350.dao.DatabaseConnection;
import com.vladis1350.constants.ProductDataConstant;
import com.vladis1350.shop.converters.ResultSetConverter;
import com.vladis1350.shop.repositories.interfaces.MyRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

@Repository
@Transactional
public class ProductRepository implements MyRepositoryInterface<Product> {
    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    @Override
    public void save(Product product) throws SQLException {
        String insert = "INSERT INTO product (" + ProductDataConstant.PRODUCT_NAME + "," + ProductDataConstant.PRODUCT_PRICE + "," +
                ProductDataConstant.PRODUCT_DESCRIPTION + "," + ProductDataConstant.PRODUCT_DISCOUNT + "," + ProductDataConstant.PRODUCT_CATEGORY + ") VALUES(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(insert)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, String.valueOf(product.getPrice()));
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, String.valueOf(product.getDiscount()));
            preparedStatement.setString(5, String.valueOf(product.getCategory()));
            if (!preparedStatement.execute()) {
                logger.info("Product: '{}' successfully added!", product.getName());
            } else {
                logger.error("Product: '{}' has not been added.", product.getName());
            }
        }
    }

    @Override
    public List<Product> findAll() throws SQLException {
        String query = "SELECT id_product, product_name, price, description, discount, categories.name_category as category " +
                "FROM product, categories " +
                "WHERE product.id_category=categories.id_category";
        try (ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery(query)) {
            return ResultSetConverter.convertToListProduct(resultSet);
        }
    }

    public Product findByProductName(String productName) throws SQLException {
        String query = "SELECT * FROM product WHERE product_name = ?";
        try(PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(query)) {
            preparedStatement.setString(1, productName);
            return ResultSetConverter.convertToProduct(preparedStatement.executeQuery());
        }

    }

    @Override
    public Product getById(Long id) throws SQLException {
        String query = "SELECT * FROM product WHERE id_product=?";
        try (PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            return ResultSetConverter.convertToProduct(preparedStatement.executeQuery());
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String query = "DELETE FROM product WHERE id_product=?";
        try(PreparedStatement statement = databaseConnection.getDbConnection().prepareStatement(query)) {
            statement.setLong(1, id);
            statement.execute(query);
        }
    }

    @Override
    public void update(Product product) throws SQLException {
        String query = " UPDATE product SET product_name=?, price=?, description=?,  discount=?, id_category=? " +
                "WHERE id_product=?";
        try(PreparedStatement statement = databaseConnection.getDbConnection().prepareStatement(query)) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setString(3, product.getDescription());
            statement.setBigDecimal(4, product.getDiscount());
            statement.setString(5, product.getCategory());
            statement.setLong(6, product.getId());
            statement.executeUpdate();
        }
    }

    public List<Product> findProductByCategory(String categoryName) throws SQLException {
        String query = "SELECT id_product, product_name, price, description, discount, categories.name_category as category " +
                "FROM product, categories " +
                "WHERE product.id_category=categories.id_category and categories.name_category=?";
        try (PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(query)) {
            preparedStatement.setString(1, categoryName);
            return ResultSetConverter.convertToListProduct(preparedStatement.executeQuery());
        }
    }

    public void changeDiscountForCategories(Long idCategory, BigDecimal discount) throws SQLException {
        String query = "UPDATE product SET discount=? WHERE id_category=?";
        try(PreparedStatement statement = databaseConnection.getDbConnection().prepareStatement(query)) {
            statement.setBigDecimal(1, discount);
            statement.setLong(2, idCategory);
            statement.executeUpdate();
        }
    }
}
