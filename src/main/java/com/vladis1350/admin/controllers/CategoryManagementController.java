package com.vladis1350.admin.controllers;

import com.vladis1350.shop.service.CategoryMyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryManagementController {

    @Autowired
    private CategoryMyService categoryService;


}
