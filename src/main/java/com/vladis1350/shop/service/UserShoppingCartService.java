package com.vladis1350.shop.service;

import com.vladis1350.shop.bean.UserShoppingCart;
import com.vladis1350.shop.repositories.UserShoppingCartRepository;
import com.vladis1350.shop.service.interfaces.ServiceInterface;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserShoppingCartService implements ServiceInterface<UserShoppingCart, Long> {
    private final UserShoppingCartRepository userShoppingCartRepository;


    public UserShoppingCartService(UserShoppingCartRepository userShoppingCartRepository) {
        this.userShoppingCartRepository = userShoppingCartRepository;
    }

    @Override
    public void save(UserShoppingCart userShoppingCart) throws SQLException {
        this.userShoppingCartRepository.save(userShoppingCart);
    }

    @Override
    public void update(UserShoppingCart entity) throws SQLException {
        userShoppingCartRepository.update(entity);
    }

    @Override
    public UserShoppingCart getById(Long id) throws SQLException {
        return userShoppingCartRepository.getById(id);
    }

    public UserShoppingCart getByProductId(Long id_product, Long id_cart) throws SQLException {
        return userShoppingCartRepository.getByProductId(id_product, id_cart);
    }

    @Override
    public void remove(Long id) throws SQLException {
        userShoppingCartRepository.delete(id);
    }

    public void remove(Long id_cart, Long id_product) throws SQLException {
        userShoppingCartRepository.deleteProductFromCart(id_cart, id_product);
    }

    @Override
    public List<UserShoppingCart> findAll() throws SQLException {
        return userShoppingCartRepository.findAll();
    }

    public List<UserShoppingCart> findAllById(Long id) throws SQLException {
        return userShoppingCartRepository.findAllById(id);
    }

    public int getQuantityProductsInUserShoppingCart(Long id_product, Long id_cart) throws SQLException {
        return getByProductId(id_product, id_cart).getQuantityOfGoods();
    }


}
