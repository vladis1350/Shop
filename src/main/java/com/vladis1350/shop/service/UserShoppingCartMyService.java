package com.vladis1350.shop.service;

import com.vladis1350.shop.bean.Product;
import com.vladis1350.shop.bean.UserShoppingCart;
import com.vladis1350.shop.repositories.UserShoppingCartRepository;
import com.vladis1350.shop.service.interfaces.MyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserShoppingCartMyService implements MyServiceInterface<UserShoppingCart> {
    @Autowired
    private UserShoppingCartRepository userShoppingCartRepository;
    @Autowired
    private ProductMyService productMyService;

    public UserShoppingCartMyService(UserShoppingCartRepository userShoppingCartRepository) {
        this.userShoppingCartRepository = userShoppingCartRepository;
    }

    @Override
    public void save(UserShoppingCart userShoppingCart) throws SQLException {
        this.userShoppingCartRepository.save(userShoppingCart);
    }

    @Override
    public void update(UserShoppingCart entity) throws SQLException {
        userShoppingCartRepository.save(entity);
    }

    @Override
    public UserShoppingCart getById(Long id) throws SQLException {
        return userShoppingCartRepository.getByIdCart(id);
    }

    public UserShoppingCart getByProductId(Product product, Long idCart) {
        return userShoppingCartRepository.getByIdCartAndProduct(idCart, product);
    }

    @Override
    public void delete(Long id) throws SQLException {
        userShoppingCartRepository.delete(getById(id));
    }

    public void remove(Long idCart, Long idProduct) throws SQLException {
        Product product = productMyService.getById(idProduct);
        if (product != null) {
            userShoppingCartRepository.deleteByIdCartAndProduct(idCart, product);
        }
    }

    @Override
    public List<UserShoppingCart> findAll() throws SQLException {
        return userShoppingCartRepository.findAll();
    }

    public List<UserShoppingCart> findAllByIdCart(Long id) throws SQLException {
        return userShoppingCartRepository.findAllByIdCart(id);
    }

    public int getQuantityProductsInUserShoppingCart(Long idProduct, Long idCart) throws SQLException {
        Product product = productMyService.getById(idProduct);
        return getByProductId(product, idCart).getQuantityOfGoods();
    }


}
