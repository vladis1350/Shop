package com.vladis1350.shop.bean;

import com.vladis1350.auth.bean.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "id_userShoppingCart", nullable = false)
    private UserShoppingCart userShoppingCart;

}
