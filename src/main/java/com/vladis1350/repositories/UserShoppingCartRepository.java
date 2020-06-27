package com.vladis1350.repositories;

import com.vladis1350.bean.UserShoppingCart;
import com.vladis1350.configDatabase.DatabaseConnection;
import com.vladis1350.converters.ResultSetConverter;
import com.vladis1350.repositories.interfaces.Repositories;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserShoppingCartRepository implements Repositories<UserShoppingCart, Long> {
    private Statement statement;
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private PreparedStatement preparedStatement;

    public int getCountRecord() throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM user_shopping_cart";
        ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery(query);
        return resultSet.getInt(1);
    }


    @Override
    public void save(UserShoppingCart userShoppingCart) throws SQLException {
        String insert = "INSERT INTO user_shopping_cart (id_shopping_cart, id_product, quantity, summ_order) VALUES(?,?,?,?)";
        preparedStatement = databaseConnection.getDbConnection().prepareStatement(insert);
        preparedStatement.setLong(1, userShoppingCart.getId_cart());
        preparedStatement.setLong(2, userShoppingCart.getId_product());
        preparedStatement.setInt(3, userShoppingCart.getQuantityOfGoods());
        preparedStatement.setBigDecimal(4, userShoppingCart.getAmountOfMoney());
        preparedStatement.execute();
    }

    @Override
    public List<UserShoppingCart> findAll() throws SQLException {
        return null;
    }

    @Override
    public UserShoppingCart getById(Long id) throws SQLException {
        ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery("SELECT * FROM user_shopping_cart WHERE id_shopping_cart=" + id);
        return ResultSetConverter.convertToUserShoppingCart(resultSet);
    }

    public UserShoppingCart getByProductId(Long id, Long id_cart) throws SQLException {
        ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery("SELECT * FROM user_shopping_cart WHERE id_product=" + id + " and id_shopping_cart=" + id_cart);
        return ResultSetConverter.convertToUserShoppingCart(resultSet);
    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    @Override
    public void update(UserShoppingCart userShoppingCart) throws SQLException {
        statement = databaseConnection.getDbConnection().createStatement();
        statement.executeUpdate(" UPDATE user_shopping_cart SET quantity='" + userShoppingCart.getQuantityOfGoods() + "', summ_order='" + userShoppingCart.getAmountOfMoney() +
                "' WHERE id_product=" + userShoppingCart.getId_product() + " and id_shopping_cart=" + userShoppingCart.getId_cart());
    }
}
