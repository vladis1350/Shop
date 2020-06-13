package com.vladis1350.controllers;

import com.vladis1350.bean.Product;
import com.vladis1350.constants.EntityConstant;
import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.constants.SortingOptions;
import com.vladis1350.service.CategoryService;
import com.vladis1350.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class ProductSortController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = Http.SORTING_NAME)
    public String sortingProductsByName(@ModelAttribute("sortByName") String variable, Model model) throws SQLException {
        List<Product> productList = productService.findAll();
        if (variable.equals(SortingOptions.ORDER_NAME)) {
            productList.sort(Comparator.comparing(Product::getName));
        } else if (variable.equals(SortingOptions.REVERSE_ORDER_NAME)) {
            productList.sort(Comparator.comparing(Product::getName, Collections.reverseOrder()));
        }
        model.addAttribute(EntityConstant.PRODUCTS, productList);
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAll());
        return Pages.HOME;
    }

    @PostMapping(value = Http.SORTING_PRICE)
    public String sortingProductsByPrice(@ModelAttribute("sortByPrice") String variable, Model model) throws SQLException {
        List<Product> productList = (List<Product>)productService.findAll();
        if (variable.equals(SortingOptions.ORDER_PRICE)) {
            productList.sort(Comparator.comparing(Product::getPrice));
        } else if (variable.equals(SortingOptions.REVERSE_ORDER_PRICE)){
            productList.sort(Comparator.comparing(Product::getPrice, Collections.reverseOrder()));
        }
        model.addAttribute(EntityConstant.PRODUCTS, productList);
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAll());
        return Pages.HOME;
    }

    @PostMapping(value = Http.SORTING_CATEGORY)
    public String sortingProductsByCategory(@ModelAttribute("sortByCategory") String variable, Model model) throws SQLException {
        List<Product> productList = (List<Product>)productService.findAll();
        if (variable.equals(SortingOptions.ORDER_CATEGORY)) {
            productList.sort(Comparator.comparing(Product::getCategory));
        } else if (variable.equals(SortingOptions.REVERSE_ORDER_CATEGORY)){
            productList.sort(Comparator.comparing(Product::getCategory, Collections.reverseOrder()));
        }
        model.addAttribute(EntityConstant.PRODUCTS, productList);
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAll());
        return Pages.HOME;
    }

    @PostMapping(value = Http.SORTING_DISCOUNT)
    public String sortingProductsByDiscount(@ModelAttribute("sortByDiscount") String variable, Model model) throws SQLException {
        List<Product> productList = (List<Product>) productService.findAll();
        if (variable.equals(SortingOptions.ORDER_DISCOUNT)) {
            productList.sort(Comparator.comparing(Product::getDiscount));
        } else if (variable.equals(SortingOptions.REVERSE_ORDER_DISCOUNT)){
            productList.sort(Comparator.comparing(Product::getDiscount, Collections.reverseOrder()));
        }
        model.addAttribute(EntityConstant.PRODUCTS, productList);
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAll());
        return Pages.HOME;
    }
}
