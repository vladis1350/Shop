package com.vladis1350.shop.repositories.interfaces;

import com.vladis1350.shop.bean.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findShoppingCartById(Long id);

    ShoppingCart findShoppingCartByIdUser(Long id);

}
