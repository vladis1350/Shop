package com.vladis1350.shop.service;

import com.vladis1350.shop.bean.Product;
import com.vladis1350.shop.repositories.ProductRepository;
import com.vladis1350.shop.service.interfaces.MyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@Service
public class ProductMyService implements MyServiceInterface<Product> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void save(Product product) throws SQLException {
        this.productRepository.save(product);
    }

    @Override
    public void update(Product product) throws SQLException {
        this.productRepository.update(product);
    }

    @Override
    public Product getById(Long id) throws SQLException {
        return this.productRepository.getById(id);
    }

    @Override
    public void remove(Long id) throws SQLException {
        this.productRepository.delete(id);
    }

    @Override
    public List<Product> findAll() throws SQLException {
        return productRepository.findAll();
    }

    public List<Product> findAllByCategory(String category) throws SQLException {
        return productRepository.findProductByCategory(category);
    }

    public Product findByProductName(String productName) throws SQLException {
        return productRepository.findByProductName(productName);
    }

    public void changeDiscountForCategories(Long idCategory, BigDecimal discount) throws SQLException {
        productRepository.changeDiscountForCategories(idCategory, discount);
    }
}
