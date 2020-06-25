package com.vladis1350.admin.controllers;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.sql.SQLException;

@Controller
public class ProductManagementController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = Http.NEW_PRODUCT)
    public ModelAndView showNewProductsForm() throws SQLException {
        ModelAndView mod = new ModelAndView();
        mod.addObject(EntityConstant.UNIT_PRODUCT, new Product());
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAll());
        mod.setViewName(Pages.NEW_PRODUCT);
        return mod;
    }

    @PostMapping(value = Http.SAVE_PRODUCT)
    public ModelAndView showProduct(@RequestParam(value = "product_name", required = false) String product_name,
                                    @RequestParam(value = "price", required = false) BigDecimal price,
                                    @RequestParam(value = "discount", required = false) BigDecimal discount,
                                    @RequestParam(value = "category", required = false) String category,
                                    @RequestParam(value = "description", required = false) String description) throws SQLException {
        ModelAndView mod = new ModelAndView("admin/products");
        Product product = Product.builder()
                .name(product_name)
                .price(price)
                .discount(discount)
                .category(category)
                .description(description)
                .build();
        Product newProduct = productService.findByProductName(product_name);
        if (newProduct != null) {
            mod.addObject("errorMessage", "Продукт с таким именем уже сужествует!");
            mod.addObject(EntityConstant.UNIT_PRODUCT, new Product());
            mod.addObject(EntityConstant.CATEGORIES, categoryService.findAll());
            return mod;
        }
        if (!ProductValidator.validateName(product_name)) {
            mod.addObject("errProductName", "Нзвание продукта должно быть меньше 3 и больше 32 символов!");
        }
        if (!ProductValidator.validatePrice(price)) {
            mod.addObject("errProductPrice", "Цена не может быть меньше 0!");
        }
        if (!ProductValidator.validateDiscount(discount)) {
            mod.addObject("errProductDiscount", "Скидка не может быть меньше 0 и больше 100%!");
        }
        if (ProductValidator.checkValidateDataProduct(product)){
            productService.save(product);
            mod.addObject("successMessage", "Продукт успешно добавлен");
        }
        mod.addObject(EntityConstant.UNIT_PRODUCT, new Product());
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAll());
        mod.setViewName(Pages.REDIRECT + "admin/products");
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
    public ModelAndView deleteProducts(@PathVariable(name = "id") Long id) throws SQLException {
        productService.remove(id);
        return new ModelAndView(Pages.REDIRECT + "admin/products");
    }

    @GetMapping(value = Http.DISCOUNT)
    public String showSetDiscountForm(Model model) throws SQLException {
        Product product = new Product();
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAll());
        model.addAttribute(EntityConstant.UNIT_PRODUCT, product);

        return Pages.SET_DISCOUNT;
    }

    @PostMapping(value = Http.SET_DISCOUNT)
    public String setDiscountForCategory(@ModelAttribute(EntityConstant.UNIT_CATEGORY) Long id_category, BigDecimal discount) throws SQLException {
        productService.changeDiscountForCategories(id_category, discount);
        return Pages.REDIRECT + Pages.HOME;
    }
}
