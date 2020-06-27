package com.vladis1350.service;

import com.vladis1350.bean.UserShoppingCart;
import com.vladis1350.repositories.UserShoppingCartRepository;
import com.vladis1350.service.interfaces.ServiceInterface;
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

    }

    @Override
    public List<UserShoppingCart> findAll() throws SQLException {
        return null;
    }

    public int getQuantityProductsInUserShoppingCart(Long id_product, Long id_cart) throws SQLException {
        return getByProductId(id_product, id_cart).getQuantityOfGoods();
    }


}
