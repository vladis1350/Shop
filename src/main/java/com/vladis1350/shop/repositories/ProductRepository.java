package com.vladis1350.shop.repositories;

import com.vladis1350.shop.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String productName);
    Product getById(Long id);
    List<Product> findProductByCategory(String categoryName);
//    void changeDiscountForCategories(Long idCategory, BigDecimal discount);

}
