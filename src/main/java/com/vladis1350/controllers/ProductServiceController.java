package com.vladis1350.controllers;

import com.vladis1350.bean.Product;
import com.vladis1350.constants.EntityConstant;
import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.service.CategoryService;
import com.vladis1350.service.ProductService;
import com.vladis1350.validate.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ProductServiceController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = Http.HOME)
    public String viewHomePage(Model model) throws SQLException {
        Iterable<Product> products = productService.findAll();
        model.addAttribute(EntityConstant.PRODUCTS, products);
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAll());
        return Pages.HOME;
    }

    @GetMapping(value = Http.NEW_PRODUCT)
    public String showNewProductsForm(Model model) throws SQLException {
        Product product = new Product();
        model.addAttribute(EntityConstant.UNIT_PRODUCT, product);
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAll());
        return Pages.NEW_PRODUCT;
    }

    @GetMapping(value = Http.EDIT_PRODUCT + "/{id}")
    public ModelAndView showEditProductsForm(@PathVariable(name = "id") Long id, Model model) throws SQLException {
        ModelAndView modelAndView = new ModelAndView(Pages.EDIT_PRODUCT);
        Product product = productService.getById(id);
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAll());
        modelAndView.addObject(EntityConstant.UNIT_PRODUCT, product);
        return modelAndView;
    }

    @PostMapping(value = Http.SAVE_PRODUCT)
    public String showProduct(@ModelAttribute(EntityConstant.UNIT_PRODUCT) Product product) throws SQLException {
        if (ProductValidator.checkValidateDataProduct(product)) {
            productService.save(product);
        } else {
            return Pages.ERROR;
        }
        return Pages.REDIRECT + Pages.HOME;
    }

    @PostMapping(value = Http.SAVE_EDIT_PRODUCT)
    public String saveEditProduct(@ModelAttribute(EntityConstant.UNIT_PRODUCT) Product product) throws SQLException {
        if (ProductValidator.checkValidateDataProduct(product)) {
            productService.update(product);
        } else {
            return Pages.ERROR;
        }
        return Pages.REDIRECT + Pages.HOME;
    }

    @PostMapping(value = Http.CANCEL)
    public String clearFilterAndSearch() {
        return Pages.REDIRECT + Pages.HOME;
    }

    @PostMapping(value = Http.SEARCH)
    public String searchProductById(@ModelAttribute(EntityConstant.UNIT_PRODUCT) Product product, Model model) throws SQLException {
        Product product1 = productService.getById(product.getId());
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

    @GetMapping(value = Http.DELETE_PRODUCT + "/{id}")
    public String deleteProducts(@PathVariable(name = "id") Long id) throws SQLException {
        productService.remove(id);
        return Pages.REDIRECT + Pages.HOME;
    }

    @GetMapping(value = Http.DISCOUNT)
    public String showSetDiscountForm(Model model) throws SQLException {
        Product product = new Product();
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAll());
        model.addAttribute(EntityConstant.UNIT_PRODUCT, product);

        return Pages.SET_DISCOUNT;
    }

//    @PostMapping(value = Http.SET_DISCOUNT)
//    public String setDiscountForCategory(@ModelAttribute(EntityConstant.UNIT_PRODUCT) Product product, BigDecimal discount) {
//        productService.setDiscountForCategory(product.getCategory(), discount);
//        return Pages.REDIRECT + Pages.HOME;
//    }
}
