package com.vladis1350.shop.bean;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_shopping_cart")
public class UserShoppingCart implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_shopping_cart")
    private Long idCart;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "userShoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products;

    private String product;
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantityOfGoods;

    @Column(name = "summ_order")
    private BigDecimal amountOfMoney;
}
