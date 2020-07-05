package com.vladis1350.shop.controllers;

import com.vladis1350.auth.bean.User;
import com.vladis1350.auth.service.UserAccessService;
import com.vladis1350.auth.service.UserService;
import com.vladis1350.constants.SuccessConstants;
import com.vladis1350.shop.bean.Product;
import com.vladis1350.shop.bean.ShoppingCart;
import com.vladis1350.shop.bean.UserShoppingCart;
import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.shop.service.ProductMyService;
import com.vladis1350.shop.service.ShoppingCartService;
import com.vladis1350.shop.service.UserShoppingCartMyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.sql.SQLException;

@Controller
public class CartServiceController {

    @Autowired
    private UserShoppingCartMyService userShoppingCartService;

    @Autowired
    private ProductMyService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private UserAccessService userAccessService;

    @PostMapping(value = Http.ADD_TO_CART + "/{id}")
    public String addProductToCart(
            @ModelAttribute("countOfGoods") Integer count,
            @PathVariable(name = "id") Long id) throws SQLException {
        User user = userService.getCurrentAuthenticationUser();
        if (cartService.findShoppingCartByIdUserBool(user.getId())) {
            ShoppingCart shoppingCart = ShoppingCart.builder()
                    .idUser(user.getId())
                    .build();
            cartService.saveShoppingCart(shoppingCart);
        }
        Product product = productService.getById(id);
        Long idShoppingCart = cartService.findShoppingCartByIdUser(user.getId()).getId();
        if (userShoppingCartService.getByProductId(product.getId(), idShoppingCart) != null) {
            Integer quantityInBasket = userShoppingCartService.getQuantityProductsInUserShoppingCart(product.getId(), idShoppingCart);
            int totalQuantity = count+quantityInBasket;
            UserShoppingCart shoppingCart = UserShoppingCart.builder()
                    .idCart(idShoppingCart)
                    .idProduct(product.getId())
                    .quantityOfGoods(totalQuantity)
                    .amountOfMoney(product.getPrice().multiply(BigDecimal.valueOf(totalQuantity)))
                    .build();
            userShoppingCartService.update(shoppingCart);
        } else {
            UserShoppingCart userShoppingCart = UserShoppingCart.builder()
                    .idCart(cartService.findShoppingCartByIdUser(user.getId()).getId())
                    .idProduct(product.getId())
                    .quantityOfGoods(count)
                    .amountOfMoney(product.getPrice().multiply(BigDecimal.valueOf(count))).build();
            userShoppingCartService.save(userShoppingCart);
        }
        return Pages.REDIRECT + Pages.HOME;
    }

    @GetMapping(value = "/shopping_cart")
    public ModelAndView showUserShoppingCart() throws SQLException {
        ModelAndView mod = new ModelAndView("shopping_cart");
        User user = userService.getCurrentAuthenticationUser();
        Long idShoppingCart = cartService.findShoppingCartByIdUser(user.getId()).getId();
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        mod.addObject("userProductList", userShoppingCartService.findAllById(idShoppingCart));
        return mod;
    }

    @GetMapping(value = "/deleteUserProduct/{id_cart}/{id_product}")
    public ModelAndView deleteUserProduct(@PathVariable(name = "id_cart") Long idCart,
                                          @PathVariable(name = "id_product") Long idProduct) throws SQLException {
        ModelAndView mod = new ModelAndView();
        User user = userService.getCurrentAuthenticationUser();
        Long idShoppingCart = cartService.findShoppingCartByIdUser(user.getId()).getId();
        userShoppingCartService.remove(idCart, idProduct);
        mod.addObject("userProductList", userShoppingCartService.findAllById(idShoppingCart));
        mod.setViewName(Pages.REDIRECT + "shopping_cart");
        return mod;
    }
}
