package com.vladis1350.admin.controllers;

import com.vladis1350.auth.repositories.UserRepository;
import com.vladis1350.shop.service.CategoryMyService;
import com.vladis1350.shop.service.ProductMyService;
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
    private ProductMyService productService;

    @Autowired
    private CategoryMyService categoryService;

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
