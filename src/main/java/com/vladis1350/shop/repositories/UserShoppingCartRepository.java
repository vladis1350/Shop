package com.vladis1350.shop.repositories;

import com.vladis1350.dao.DatabaseConnection;
import com.vladis1350.shop.bean.UserShoppingCart;
import com.vladis1350.shop.mappers.ResultSetConverter;
import com.vladis1350.shop.repositories.interfaces.MyRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserShoppingCartRepository implements MyRepositoryInterface<UserShoppingCart> {
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
                "WHERE product.id_product=user_shopping_cart.id_product and user_shopping_cart.id_shopping_cart=?";
        try(PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            return ResultSetConverter.convertToListUserShoppingCart(preparedStatement.executeQuery());
        }
    }

    @Override
    public UserShoppingCart getById(Long id) throws SQLException {
        String query = "SELECT * FROM user_shopping_cart WHERE id_shopping_cart=?";
        try(PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            return ResultSetConverter.convertToUserShoppingCart(preparedStatement.executeQuery());
        }
    }

    public UserShoppingCart getByProductId(Long idProduct, Long idCart) throws SQLException {
        String query = "SELECT * FROM user_shopping_cart WHERE id_product=? and id_shopping_cart=?";
        try(PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(query)) {
            preparedStatement.setLong(1, idProduct);
            preparedStatement.setLong(2, idCart);
            return ResultSetConverter.convertToUserShoppingCart(preparedStatement.executeQuery());
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String query = "DELETE FROM user_shopping_cart WHERE id_product=?";
        try(PreparedStatement statement = databaseConnection.getDbConnection().prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    public void deleteProductFromCart(Long idCart, Long idProduct) throws SQLException {
        String query = "DELETE FROM user_shopping_cart WHERE id_shopping_cart=? and id_product=?";
        try(PreparedStatement statement = databaseConnection.getDbConnection().prepareStatement(query)) {
            statement.setLong(1, idCart);
            statement.setLong(2, idProduct);
            statement.executeUpdate();
        }
    }

    @Override
    public void update(UserShoppingCart userShoppingCart) throws SQLException {
        String query = "UPDATE user_shopping_cart SET quantity=?, summ_order=? WHERE id_product=? and id_shopping_cart=?";
        try(PreparedStatement statement = databaseConnection.getDbConnection().prepareStatement(query)) {
            statement.setInt(1, userShoppingCart.getQuantityOfGoods());
            statement.setBigDecimal(2, userShoppingCart.getAmountOfMoney());
            statement.setLong(3, userShoppingCart.getIdProduct());
            statement.setLong(4, userShoppingCart.getIdCart());
            statement.executeUpdate();
        }
    }
}
