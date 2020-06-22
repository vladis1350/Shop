package com.vladis1350.service;

import com.vladis1350.bean.Category;
import com.vladis1350.bean.Product;
import com.vladis1350.repositories.ProductRepository;
import com.vladis1350.service.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ServiceInterface<Product, Long> {
    private ProductRepository repositories = new ProductRepository();

    @Autowired
    public void setRepositories(ProductRepository<Product> repositories) {
        this.repositories = repositories;
    }

    @Override
    public void save(Product product) throws SQLException {
        this.repositories.save(product);
    }

    @Override
    public void update(Product product) throws SQLException {
        this.repositories.update(product);
    }

    @Override
    public Product getById(Long id) throws SQLException {
        return this.repositories.getById(id);
    }

    @Override
    public void remove(Long id) throws SQLException {
        this.repositories.delete(id);
    }

    @Override
    public List<Product> findAll() throws SQLException {
        return repositories.findAll();
    }

    public List<Product> findAllByCategory(String category) throws SQLException {
        Iterable<Product> products = repositories.findAll();
        List<Product> resultFilter = new ArrayList<>();
        for (Product product: products) {
            if (product.getCategory().equals(category)) {
                resultFilter.add(product);
            }
        }
        return resultFilter;
    }

    public void changeDiscountForCategories(Long id_category, BigDecimal discount) throws SQLException {
        repositories.changeDiscountForCategories(id_category, discount);
    }
}
