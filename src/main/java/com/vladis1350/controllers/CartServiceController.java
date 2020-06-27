package com.vladis1350.controllers;

import com.vladis1350.auth.bean.User;
import com.vladis1350.auth.service.UserService;
import com.vladis1350.bean.ShoppingCart;
import com.vladis1350.bean.UserShoppingCart;
import com.vladis1350.bean.Product;
import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.service.ShoppingCartService;
import com.vladis1350.service.UserShoppingCartService;
import com.vladis1350.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.SQLException;

@Controller
public class CartServiceController {

    @Autowired
    private UserShoppingCartService userShoppingCartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService cartService;

    @PostMapping(value = Http.ADD_TO_CART + "/{id}")
    public String addProductToCart(
            @ModelAttribute("countOfGoods") Integer count,
            @PathVariable(name = "id") Long id) throws SQLException {
        User user = userService.getCurrentAuthenticationUser();
        if (cartService.findUserById(user.getId())) {
            ShoppingCart shoppingCart = ShoppingCart.builder()
                    .idUser(user.getId())
                    .build();
            cartService.saveShoppingCart(shoppingCart);
        }
        Product product = productService.getById(id);
//        UserShoppingCart userShoppingCart = UserShoppingCart.builder()
//                .id_cart()
//                .id_product(product.getId())
//                .quantityOfGoods(count)
//                .amountOfMoney(product.getPrice().multiply(BigDecimal.valueOf(count))).build();
//        userShoppingCartService.save(userShoppingCart);
        return Pages.REDIRECT + Pages.HOME;
    }
}
