package com.vladis1350.shop.mappers;

import com.vladis1350.shop.bean.Category;
import com.vladis1350.shop.bean.Product;
import com.vladis1350.shop.bean.UserShoppingCart;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetConverter {

    private ResultSetConverter(){}

//    public static List<Product> convertToListProduct(ResultSet resultSet) throws SQLException {
//        List<Product> list = new ArrayList<>();
//        while (resultSet.next()) {
//            Long id = resultSet.getLong(1);
//            String name = resultSet.getString(2);
//            BigDecimal price = resultSet.getBigDecimal(3);
//            String desc = resultSet.getString(4);
//            BigDecimal discount = resultSet.getBigDecimal(5);
//            String category = resultSet.getString(6);
//            list.add(new Product(id, name, price, category, discount, desc));
//        }
//        return list;
//    }

    public static List<Category> convertToListCategory(ResultSet resultSet) throws SQLException {
        List<Category> list = new ArrayList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong(1);
            String name = resultSet.getString(2);
            list.add(new Category(id, name));
        }
        return list;
    }

//    public static List<UserShoppingCart> convertToListUserShoppingCart(ResultSet resultSet) throws SQLException {
//        List<UserShoppingCart> list = new ArrayList<>();
//        while (resultSet.next()) {
//            UserShoppingCart shoppingCart = UserShoppingCart.builder()
//                    .idCart(resultSet.getLong(1))
//                    .idProduct(resultSet.getLong(2))
//                    .product(resultSet.getString(3))
//                    .price(resultSet.getBigDecimal(4))
//                    .quantityOfGoods(resultSet.getInt(5))
//                    .amountOfMoney(resultSet.getBigDecimal(6))
//                    .build();
//            list.add(shoppingCart);
//        }
//        return list;
//    }

    public static Product convertToProduct(ResultSet resultSet) throws SQLException {
        Product product = null;
        while (resultSet.next()) {
            product = Product.builder()
                    .id(resultSet.getLong(1))
                    .name(resultSet.getString(2))
                    .price(resultSet.getBigDecimal(3))
                    .description(resultSet.getString(4))
                    .discount(resultSet.getBigDecimal(5))
                    .category(resultSet.getString(6))
                    .build();
        }
        return product;
    }

//    public static UserShoppingCart convertToUserShoppingCart(ResultSet resultSet) throws SQLException {
//        UserShoppingCart userShoppingCart = null;
//        while (resultSet.next()) {
//            userShoppingCart = UserShoppingCart.builder()
//                    .idCart(resultSet.getLong(1))
//                    .idProduct(resultSet.getLong(2))
//                    .quantityOfGoods(resultSet.getInt(3))
//                    .amountOfMoney(resultSet.getBigDecimal(4))
//                    .build();
//        }
//        return userShoppingCart;
//    }

    public static Category convertToCategory(ResultSet resultSet) throws SQLException {
        Category category = null;
        while (resultSet.next()) {
            category = Category.builder()
                    .id(resultSet.getLong(1))
                    .nameCategory(resultSet.getString(2)).build();
        }
        return category;
    }

}
