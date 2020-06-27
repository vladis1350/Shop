package com.vladis1350.repositories.interfaces;

import com.vladis1350.bean.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findShoppingCartById(Long id);

    Streamable<Long> findShoppingCartByIdUser(Long id);

}
