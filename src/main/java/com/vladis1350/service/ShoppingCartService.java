package com.vladis1350.service;

import com.vladis1350.bean.ShoppingCart;
import com.vladis1350.repositories.interfaces.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository cartRepository;

    public void saveShoppingCart(ShoppingCart shoppingCart) {
            cartRepository.save(shoppingCart);
    }

    public boolean findUserById(Long id) {
        return cartRepository.findShoppingCartByIdUser(id).isEmpty();
    }
}
