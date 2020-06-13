package com.vladis1350.bean;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private BigDecimal price;

    private String category;

    private BigDecimal discount;

    private String description;

    public Product() {
    }

    public Product(String name, BigDecimal price, String category, BigDecimal discount, String description) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.discount = discount;
    }

    public Product(Long id, String name, BigDecimal price, String category, BigDecimal discount, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.discount = discount;
    }



}
