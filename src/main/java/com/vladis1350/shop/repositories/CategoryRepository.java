package com.vladis1350.shop.repositories;

import com.vladis1350.shop.bean.Category;
import com.vladis1350.configDatabase.DatabaseConnection;
import com.vladis1350.shop.converters.ResultSetConverter;
import com.vladis1350.shop.repositories.interfaces.Repositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
@Transactional
public class CategoryRepository<T extends Category> implements Repositories<Category, Long> {
    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);
    private Statement statement;
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private PreparedStatement preparedStatement;

    @Override
    public void save(Category category) throws SQLException {
        String insert = "INSERT INTO categories (name_category)  VALUES(?)";
        preparedStatement = databaseConnection.getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, category.getNameCategory());
        preparedStatement.execute();
    }

    @Override
    public List<Category> findAll() throws SQLException {
        ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery("SELECT * FROM categories");
        return ResultSetConverter.convertToListCategory(resultSet);
    }

    @Override
    public Category getById(Long id) throws SQLException {
        ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery("SELECT * FROM categories WHERE id_category=" + id);
        return ResultSetConverter.convertToCategory(resultSet);
    }

    @Override
    public void delete(Long id) throws SQLException {
        statement = databaseConnection.getDbConnection().createStatement();
        statement.executeUpdate(" DELETE FROM categories WHERE id_category=" + id);
    }

    @Override
    public void update(Category category) throws SQLException {
        statement = databaseConnection.getDbConnection().createStatement();
        statement.executeUpdate("UPDATE categories SET name_category ='" + category.getNameCategory() + "'");
    }
}
