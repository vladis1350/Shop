package com.vladis1350.admin.controllers;

import com.vladis1350.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryManagementController {

    @Autowired
    private CategoryService categoryService;


}
