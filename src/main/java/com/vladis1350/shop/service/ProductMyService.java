package com.vladis1350.shop.service;

import com.vladis1350.shop.bean.Category;
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
        this.productRepository.save(product);
    }

    @Override
    public Product getById(Long id) throws SQLException {
        return this.productRepository.getById(id);
    }

    @Override
    public void delete(Long id) throws SQLException {
        this.productRepository.delete(getById(id));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAllByCategory(String category){
        return productRepository.findProductByCategory(category);
    }

    public Product findByProductName(String productName) {
        return productRepository.findByName(productName);
    }

    public void changeDiscountForCategories(Long idCategory, BigDecimal discount) throws SQLException {
        Product product = productRepository.getById(idCategory);
        product.setDiscount(discount);
        save(product);
    }
}
