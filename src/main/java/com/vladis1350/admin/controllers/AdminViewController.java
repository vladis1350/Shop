package com.vladis1350.admin.controllers;

import com.vladis1350.auth.repositories.UserRepository;
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

@Controller
public class AdminViewController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/users")
    public ModelAndView showAdminPanel() {
        ModelAndView mod = new ModelAndView();
        mod.addObject("userList", userRepository.findAll());
        return mod;
    }

    @GetMapping("/admin/products")
    public ModelAndView showProductListInAdminPanel() throws SQLException {
        ModelAndView mod = new ModelAndView();
        mod.addObject("productList", productService.findAll());
        return mod;
    }

    @GetMapping("/admin/categories")
    public ModelAndView showCategoryListInAdminPanel() throws SQLException {
        ModelAndView mod = new ModelAndView();
        mod.addObject("categoryList", categoryService.findAll());
        return mod;
    }

    @GetMapping(value = Http.EDIT_PRODUCT + "/{id}")
    public ModelAndView showEditProductsForm(@PathVariable(name = "id") Long id, Model model) throws SQLException {
        ModelAndView modelAndView = new ModelAndView(Pages.EDIT_PRODUCT);
        Product product = productService.getById(id);
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAll());
        modelAndView.addObject(EntityConstant.UNIT_PRODUCT, product);
        return modelAndView;
    }

    @PostMapping(value = Http.SAVE_EDIT_PRODUCT)
    public String saveEditProduct(@ModelAttribute(EntityConstant.UNIT_PRODUCT) Product product) throws SQLException {
        if (ProductValidator.checkValidateDataProduct(product)) {
            productService.update(product);
        } else {

        }
        return Pages.REDIRECT + Pages.HOME;
    }

    @GetMapping(value = Http.DELETE_PRODUCT + "/{id}")
    public String deleteProducts(@PathVariable(name = "id") Long id) throws SQLException {
        productService.remove(id);
        return Pages.REDIRECT + Pages.HOME;
    }


}
