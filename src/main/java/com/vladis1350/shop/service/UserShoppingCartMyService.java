package com.vladis1350.shop.service;

import com.vladis1350.shop.bean.UserShoppingCart;
import com.vladis1350.shop.repositories.UserShoppingCartRepository;
import com.vladis1350.shop.service.interfaces.MyServiceInterface;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserShoppingCartMyService implements MyServiceInterface<UserShoppingCart> {
    private final UserShoppingCartRepository userShoppingCartRepository;


    public UserShoppingCartMyService(UserShoppingCartRepository userShoppingCartRepository) {
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

    public UserShoppingCart getByProductId(Long idProduct, Long idCart) throws SQLException {
        return userShoppingCartRepository.getByProductId(idProduct, idCart);
    }

    @Override
    public void remove(Long id) throws SQLException {
        userShoppingCartRepository.delete(id);
    }

    public void remove(Long idCart, Long idProduct) throws SQLException {
        userShoppingCartRepository.deleteProductFromCart(idCart, idProduct);
    }

    @Override
    public List<UserShoppingCart> findAll() throws SQLException {
        return userShoppingCartRepository.findAll();
    }

    public List<UserShoppingCart> findAllById(Long id) throws SQLException {
        return userShoppingCartRepository.findAllById(id);
    }

    public int getQuantityProductsInUserShoppingCart(Long idProduct, Long idCart) throws SQLException {
        return getByProductId(idProduct, idCart).getQuantityOfGoods();
    }


}
