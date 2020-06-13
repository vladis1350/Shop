package com.vladis1350.controllers;

import com.vladis1350.bean.Cart;
import com.vladis1350.bean.Product;
import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.service.CartService;
import com.vladis1350.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.sql.SQLException;

@Controller
public class CartServiceController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @PostMapping(value = Http.ADD_TO_CART + "/{id}")
    public String addProductToCart(
            @ModelAttribute("countOfGoods") Integer count,
            @PathVariable(name = "id") Long id) throws SQLException {
        System.out.println(count);
        Product product = productService.getById(id);
        Cart cart = Cart.builder().id_product(product.getId())
                .quantityOfGoods(count)
                .amountOfMoney(product.getPrice().multiply(BigDecimal.valueOf(count))).build();
        cartService.save(cart);
        return Pages.REDIRECT + Pages.HOME;
    }
}
