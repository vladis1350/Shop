package com.vladis1350.shop.repositories;

import com.vladis1350.shop.bean.UserShoppingCart;
import com.vladis1350.dao.DatabaseConnection;
import com.vladis1350.shop.converters.ResultSetConverter;
import com.vladis1350.shop.repositories.interfaces.MyRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserShoppingCartRepository implements MyRepositoryInterface<UserShoppingCart> {
    private Statement statement;
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    @Override
    public void save(UserShoppingCart userShoppingCart) throws SQLException {
        String insert = "INSERT INTO user_shopping_cart (id_shopping_cart, id_product, quantity, summ_order) VALUES(?,?,?,?)";
        try(PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(insert)) {
            preparedStatement.setLong(1, userShoppingCart.getIdCart());
            preparedStatement.setLong(2, userShoppingCart.getIdProduct());
            preparedStatement.setInt(3, userShoppingCart.getQuantityOfGoods());
            preparedStatement.setBigDecimal(4, userShoppingCart.getAmountOfMoney());
            preparedStatement.execute();
        }
    }

    @Override
    public List<UserShoppingCart> findAll() throws SQLException {
        String query = "SELECT product.product_name, product.price, user_shopping_cart.quantity, user_shopping_cart.summ_order   as category " +
                "FROM product INNER JOIN user_shopping_cart ON product.id_product=user_shopping_cart.id_product " +
                "WHERE product.id_product=user_shopping_cart.id_product";
        try(ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery(query)) {
            return ResultSetConverter.convertToListUserShoppingCart(resultSet);
        }
    }


    public List<UserShoppingCart> findAllById(Long id) throws SQLException {
        String query = "SELECT user_shopping_cart.id_shopping_cart, user_shopping_cart.id_product, product.product_name, product.price, user_shopping_cart.quantity, user_shopping_cart.summ_order " +
                "FROM product INNER JOIN user_shopping_cart ON product.id_product=user_shopping_cart.id_product " +
                "WHERE product.id_product=user_shopping_cart.id_product and user_shopping_cart.id_shopping_cart=" + id;
        try(ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery(query)) {
            return ResultSetConverter.convertToListUserShoppingCart(resultSet);
        }
    }

    @Override
    public UserShoppingCart getById(Long id) throws SQLException {
        String query = "SELECT * FROM user_shopping_cart WHERE id_shopping_cart=" + id;
        try(ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery(query)) {
            return ResultSetConverter.convertToUserShoppingCart(resultSet);
        }
    }

    public UserShoppingCart getByProductId(Long id, Long idCart) throws SQLException {
        String query = "SELECT * FROM user_shopping_cart WHERE id_product=" + id + " and id_shopping_cart=" + idCart;
        try(ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery(query)) {
            return ResultSetConverter.convertToUserShoppingCart(resultSet);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        statement = databaseConnection.getDbConnection().createStatement();
        statement.executeUpdate(" DELETE FROM user_shopping_cart WHERE id_product=" + id);
    }

    public void deleteProductFromCart(Long idCart, Long idProduct) throws SQLException {
        statement = databaseConnection.getDbConnection().createStatement();
        statement.executeUpdate(" DELETE FROM user_shopping_cart WHERE id_shopping_cart=" + idCart + " and id_product=" + idProduct);
    }

    @Override
    public void update(UserShoppingCart userShoppingCart) throws SQLException {
        statement = databaseConnection.getDbConnection().createStatement();
        statement.executeUpdate(" UPDATE user_shopping_cart SET quantity='" + userShoppingCart.getQuantityOfGoods() + "', summ_order='" + userShoppingCart.getAmountOfMoney() +
                "' WHERE id_product=" + userShoppingCart.getIdProduct() + " and id_shopping_cart=" + userShoppingCart.getIdCart());
    }
}
