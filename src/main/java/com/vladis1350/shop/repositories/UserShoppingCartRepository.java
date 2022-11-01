package com.vladis1350.shop.repositories;

import com.vladis1350.shop.bean.Product;
import com.vladis1350.shop.bean.UserShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserShoppingCartRepository extends JpaRepository<UserShoppingCart, Long> {

    UserShoppingCart getByIdCart(Long id);
    List<UserShoppingCart> findAllByIdCart(Long id);
    UserShoppingCart getByIdCartAndProduct(Long idCart, Product product);

    void deleteByIdCartAndProduct(Long idCart, Product product);

}
