package com.vladis1350.shop.controllers;

import com.vladis1350.auth.service.UserAccessService;
import com.vladis1350.constants.EntityConstant;
import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.constants.SuccessConstants;
import com.vladis1350.shop.bean.Product;
import com.vladis1350.shop.service.CategoryMyService;
import com.vladis1350.shop.service.ProductMyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ProductServiceController {

    @Autowired
    private ProductMyService productService;

    @Autowired
    private CategoryMyService categoryService;

    @Autowired
    private UserAccessService userAccessService;

    @GetMapping(value = Http.HOME)
    public ModelAndView viewHomePage() throws SQLException {
        ModelAndView mod = new ModelAndView("home");
        mod.addObject(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        mod.addObject(EntityConstant.PRODUCTS, productService.findAll());
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAll());
        return mod;
    }

    @PostMapping(value = Http.CANCEL)
    public String clearFilterAndSearch() {
        return Pages.REDIRECT + Pages.HOME;
    }

    @PostMapping(value = Http.SEARCH)
    public String searchProductById(@ModelAttribute(EntityConstant.ENTITY_ID_PRODUCT) Long idProduct, Model model) throws SQLException {
        Product product1 = productService.getById(idProduct);
        model.addAttribute(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        model.addAttribute(EntityConstant.PRODUCTS, product1);
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAll());
        return Pages.HOME;
    }

    @PostMapping(value = Http.FILTER)
    public String filterProductByCategory(@ModelAttribute(EntityConstant.UNIT_CATEGORY) String category, Model model) throws SQLException {
        List<Product> productList = productService.findAllByCategory(category);
        model.addAttribute(SuccessConstants.IS_AUTHENTICATED, userAccessService.isCurrentUserAuthenticated());
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAll());
        model.addAttribute(EntityConstant.PRODUCTS, productList);
        return Pages.HOME;
    }
}
