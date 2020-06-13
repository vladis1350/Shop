package com.vladis1350.repositories;

import com.vladis1350.bean.Category;
import com.vladis1350.configDatabase.DatabaseConnection;
import com.vladis1350.converters.ResultSetConverter;
import com.vladis1350.repositories.interfaces.Repositories;
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
public class CategoryRepository<T extends Category> implements Repositories<Category, Long> {
    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);
    private Statement statement;
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private PreparedStatement preparedStatement;

    @Override
    @Transactional
    public void save(Category category) throws SQLException {
        String insert = "INSERT INTO category (name_category)  VALUES(?)";
        preparedStatement = databaseConnection.getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, category.getNameCategory());
        preparedStatement.execute();
    }

    @Override
    @Transactional
    public List<Category> findAll() throws SQLException {
        ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery("SELECT * FROM category");
        return ResultSetConverter.convertToListCategory(resultSet);
    }

    @Override
    @Transactional
    public Category getById(Long id) throws SQLException {
        ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery("SELECT * FROM category WHERE id=" + id);
        return ResultSetConverter.convertToCategory(resultSet);
    }

    @Override
    @Transactional
    public void delete(Long id) throws SQLException {
        statement = databaseConnection.getDbConnection().createStatement();
        statement.executeUpdate(" DELETE FROM category WHERE id=" + id);
    }

    @Override
    @Transactional
    public void update(Category category) throws SQLException {
        statement = databaseConnection.getDbConnection().createStatement();
        statement.executeUpdate("UPDATE category SET name_category ='" + category.getNameCategory() + "'");
    }
}
