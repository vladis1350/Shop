package com.vladis1350.shop.controllers;

import com.vladis1350.shop.bean.Category;
import com.vladis1350.constants.EntityConstant;
import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

@Controller
public class CategoryServiceController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = Http.NEW_CATEGORY)
    public String showNewCategoryForm(Model model) throws SQLException {
        Category category = new Category();
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAll());
        model.addAttribute(EntityConstant.UNIT_CATEGORY, category);
        return Pages.CATEGORY;
    }

    @PostMapping(value = Http.SAVE_CATEGORY)
    public String showCategory(@ModelAttribute Category category, Model model) throws SQLException {
        categoryService.save(category);

        return showNewCategoryForm(model);
    }
}
