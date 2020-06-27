package com.vladis1350.controllers;

import com.vladis1350.auth.service.UserAccessService;
import com.vladis1350.auth.service.UserService;
import com.vladis1350.bean.Product;
import com.vladis1350.constants.EntityConstant;
import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.service.CategoryService;
import com.vladis1350.service.ProductService;
import com.vladis1350.validate.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@Controller
public class ProductServiceController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserAccessService userAccessService;

    @Autowired
    private UserService userService;

    @GetMapping(value = Http.HOME)
    public ModelAndView viewHomePage() throws SQLException {
        ModelAndView mod = new ModelAndView("home");
        mod.addObject("IS_AUTHENTICATED", userAccessService.isCurrentUserAuthenticated());
        mod.addObject(EntityConstant.PRODUCTS, productService.findAll());
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAll());
        return mod;
    }

    @PostMapping(value = Http.CANCEL)
    public String clearFilterAndSearch() {
        return Pages.REDIRECT + Pages.HOME;
    }

    @PostMapping(value = Http.SEARCH)
    public String searchProductById(@ModelAttribute(EntityConstant.ENTITY_ID_PRODUCT) Long id_product, Model model) throws SQLException {
        Product product1 = productService.getById(id_product);
        model.addAttribute(EntityConstant.PRODUCTS, product1);
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAll());
        return Pages.HOME;
    }

    @PostMapping(value = Http.FILTER)
    public String filterProductByCategory(@ModelAttribute(EntityConstant.UNIT_CATEGORY) String category, Model model) throws SQLException {
        List<Product> productList = productService.findAllByCategory(category);
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAll());
        model.addAttribute(EntityConstant.PRODUCTS, productList);
        return Pages.HOME;
    }
}
