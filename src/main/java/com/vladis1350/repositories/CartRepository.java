package com.vladis1350.repositories;

import com.vladis1350.bean.Cart;
import com.vladis1350.configDatabase.DatabaseConnection;
import com.vladis1350.repositories.interfaces.Repositories;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class CartRepository implements Repositories<Cart, Long> {
    private Statement statement;
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private PreparedStatement preparedStatement;

    public int getCountRecord() throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM cart";
        ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery(query);
        return resultSet.getInt(1);
    }


    @Override
    public void save(Cart cart) throws SQLException {
        String insert = "INSERT INTO cart (id_cart, id_product, count_of_goods, amount_of_money) VALUES(?,?,?,?)";
        preparedStatement = databaseConnection.getDbConnection().prepareStatement(insert);
        preparedStatement.setLong(1, cart.getId_cart());
        preparedStatement.setLong(2, cart.getId_product());
        preparedStatement.setInt(3, cart.getQuantityOfGoods());
        preparedStatement.setBigDecimal(4, cart.getAmountOfMoney());
        preparedStatement.execute();
    }

    @Override
    public List<Cart> findAll() throws SQLException {
        return null;
    }

    @Override
    public Cart getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    @Override
    public void update(Cart cart) throws SQLException {

    }
}
