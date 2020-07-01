package com.vladis1350.shop.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
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

}
