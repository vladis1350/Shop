package com.vladis1350.shop.repositories;

import com.vladis1350.constants.ProductDataConstant;
import com.vladis1350.dao.DatabaseConnection;
import com.vladis1350.shop.bean.Product;
import com.vladis1350.shop.mappers.ProductRowMapper;
import com.vladis1350.shop.mappers.ResultSetConverter;
import com.vladis1350.shop.repositories.interfaces.MyRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProductRepository implements MyRepositoryInterface<Product> {
    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void save(Product product) throws SQLException {
        String insert = "INSERT INTO product VALUES(:id, :name, :price, :description, :discount, :category)";
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(product);
        namedParameterJdbcTemplate.update(insert, namedParameter);
    }

    @Override
    public List<Product> findAll() {
        String query = "SELECT id_product, product_name, price, description, discount, categories.name_category as category " +
                "FROM product, categories " +
                "WHERE product.id_category=categories.id_category";
        return jdbcTemplate.query(query, new ProductRowMapper());
    }

    public Product findByProductName(String productName) throws SQLException {
        String query = "SELECT * FROM product WHERE product_name = :name";
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("name", productName);
        List<Product> product = namedParameterJdbcTemplate.query(query, parameterSource, new ProductRowMapper());
        if (product.isEmpty()){
            return null;
        }
        return product.get(0);
    }

    @Override
    public Product getById(Long id) throws SQLException {
        String query = "SELECT * FROM product WHERE id_product=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("id", id);
        List<Product> product = namedParameterJdbcTemplate.query(query, parameterSource, new ProductRowMapper());
        if (product.isEmpty()){
            return null;
        }
        return product.get(0);
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
