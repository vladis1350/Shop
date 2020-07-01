package com.vladis1350.shop.bean;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_shopping_cart")
public class UserShoppingCart implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_shopping_cart")
    private Long idCart;

    @Column(name = "id_product")
    private Long idProduct;

    private String product;
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantityOfGoods;

    @Column(name = "summ_order")
    private BigDecimal amountOfMoney;
}
