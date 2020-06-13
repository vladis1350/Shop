package com.vladis1350.service;

import com.vladis1350.bean.Cart;
import com.vladis1350.bean.Product;
import com.vladis1350.repositories.CartRepository;
import com.vladis1350.service.interfaces.ServiceInterface;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CartService implements ServiceInterface<Cart, Long> {
    private final CartRepository cartRepository;


    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void save(Cart cart) throws SQLException {
        this.cartRepository.save(cart);
    }

    @Override
    public void update(Cart entity) throws SQLException {

    }

    @Override
    public Cart getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void remove(Long id) throws SQLException {

    }

    @Override
    public List<Cart> findAll() throws SQLException {
        return null;
    }
}
