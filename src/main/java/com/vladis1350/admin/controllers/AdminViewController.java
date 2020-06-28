package com.vladis1350.admin.controllers;

import com.vladis1350.auth.repositories.UserRepository;
import com.vladis1350.shop.service.CategoryService;
import com.vladis1350.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/admin/categories")
    public ModelAndView showCategoryListInAdminPanel() throws SQLException {
        ModelAndView mod = new ModelAndView();
        mod.addObject("categoryList", categoryService.findAll());
        return mod;
    }




}
