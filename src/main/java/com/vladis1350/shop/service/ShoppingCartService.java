package com.vladis1350.shop.service;

import com.vladis1350.shop.bean.ShoppingCart;
import com.vladis1350.shop.repositories.interfaces.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository cartRepository;

    public void saveShoppingCart(ShoppingCart shoppingCart) {
        cartRepository.save(shoppingCart);
    }

    public boolean findShoppingCartByIdUser(Long id) {
        return cartRepository.findShoppingCartByIdUser(id) == null;
    }

    public ShoppingCart findShoppingCart(Long id) {
        return cartRepository.findShoppingCartByIdUser(id);
    }
}
