package com.vladis1350.bean;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private long id;

    @Column(name = "product_name")
    @Length(min = 3, max = 32, message = "название не может быть меньше 3 символов и больше 32")
    @NotEmpty(message = "Пожалуйста, заполните название продукта")
    private String name;

    @Column(name = "price")
    @NotEmpty(message = "цена продукта должна быть больше 0")
    private BigDecimal price;

    private String category;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "description")
    private String description;

//    public Product(String name, BigDecimal price, String category, BigDecimal discount, String description) {
//        this.name = name;
//        this.price = price;
//        this.category = category;
//        this.discount = discount;
//    }
//
//    public Product(Long id, String name, BigDecimal price, String category, BigDecimal discount, String description) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.category = category;
//        this.discount = discount;
//    }


}
