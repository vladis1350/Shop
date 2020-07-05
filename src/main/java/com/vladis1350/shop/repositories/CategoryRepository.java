package com.vladis1350.shop.repositories;

import com.vladis1350.dao.DatabaseConnection;
import com.vladis1350.shop.bean.Category;
import com.vladis1350.shop.converters.ResultSetConverter;
import com.vladis1350.shop.repositories.interfaces.MyRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class CategoryRepository implements MyRepositoryInterface<Category> {
    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    @Override
    public void save(Category category) throws SQLException {
        String insert = "INSERT INTO categories (name_category)  VALUES(?)";
        try (PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(insert)) {
            preparedStatement.setString(1, category.getNameCategory());
            if (preparedStatement.execute()) {
                logger.info("Category: '{}' successfully added!", category.getNameCategory());
            } else {
                logger.error("Category: '{}' has not been added!", category.getNameCategory());
            }
        }
    }

    @Override
    public List<Category> findAll() throws SQLException {
        String query = "SELECT * FROM categories";
        try (ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery(query)) {
            return ResultSetConverter.convertToListCategory(resultSet);
        }
    }

    @Override
    public Category getById(Long id) throws SQLException {
        String query = "SELECT * FROM categories WHERE id_category=?";
        try (PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            return ResultSetConverter.convertToCategory(preparedStatement.executeQuery());
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String query = "DELETE FROM categories WHERE id_category=?";
        try (PreparedStatement statement = databaseConnection.getDbConnection().prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Category category) throws SQLException {
        String query = "UPDATE categories SET name_category =?";
        try (PreparedStatement statement = databaseConnection.getDbConnection().prepareStatement(query)) {
            statement.setString(1, category.getNameCategory());
            statement.executeUpdate();
        }
    }
}
