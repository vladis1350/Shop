package com.vladis1350.admin.controllers;

import com.vladis1350.shop.bean.Product;
import com.vladis1350.constants.EntityConstant;
import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.shop.service.CategoryMyService;
import com.vladis1350.shop.service.ProductMyService;
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
    private ProductMyService productService;

    @Autowired
    private CategoryMyService categoryService;

    @GetMapping(value = Http.NEW_PRODUCT)
    public ModelAndView showNewProductsForm() throws SQLException {
        ModelAndView mod = new ModelAndView();
        mod.addObject(EntityConstant.UNIT_PRODUCT, new Product());
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAll());
        mod.setViewName(Pages.NEW_PRODUCT);
        return mod;
    }

    @GetMapping("/admin/products")
    public ModelAndView showProductListInAdminPanel() throws SQLException {
        ModelAndView mod = new ModelAndView();
        mod.addObject("productList", productService.findAll());
        mod.addObject("categoryList", categoryService.findAll());
        return mod;
    }

    @PostMapping(value = Http.SAVE_PRODUCT)
    public ModelAndView  showProduct(@RequestParam(value = "productName", required = false) String productName,
                                    @RequestParam(value = "price", required = false) BigDecimal price,
                                    @RequestParam(value = "discount", required = false) BigDecimal discount,
                                    @RequestParam(value = "category", required = false) String category,
                                    @RequestParam(value = "description", required = false) String description) throws SQLException {
        ModelAndView mod = new ModelAndView();
        Product product = Product.builder()
                .name(productName)
                .price(price)
                .discount(discount)
                .category(category)
                .description(description)
                .build();
        Product newProduct = productService.findByProductName(productName);
        if (newProduct != null) {
            mod.addObject("errorMessage", "Продукт с таким именем уже сужествует!");
            mod.addObject(EntityConstant.PRODUCTS, productService.findAll());
            mod.addObject(EntityConstant.CATEGORIES, categoryService.findAll());
            mod.setViewName("/admin/products");
            return mod;
        }
        if (!ProductValidator.validateName(productName)) {
            mod.addObject("errProductName", "Нзвание должно быть не менее 3-х и не более 32 символов!");
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
        mod.addObject(EntityConstant.PRODUCTS, productService.findAll());
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAll());
        mod.setViewName("/admin/products");
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
        }
        return Pages.REDIRECT + Pages.HOME;
    }

    @GetMapping(value = Http.DELETE_PRODUCT + "/{id}")
    public ModelAndView deleteProducts(@PathVariable(name = "id") Long id) throws SQLException {
        productService.delete(id);
        return new ModelAndView(Pages.REDIRECT + "admin/products");
    }

    @GetMapping(value = "/admin/"+Http.DISCOUNT)
    public ModelAndView showSetDiscountForm() throws SQLException {
        ModelAndView mod = new ModelAndView("admin/set_discount");
        Product product = new Product();
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAll());
        mod.addObject(EntityConstant.UNIT_PRODUCT, product);

        return mod;
    }

    @PostMapping(value = Http.SET_DISCOUNT)
    public ModelAndView setDiscountForCategory(@ModelAttribute(EntityConstant.UNIT_CATEGORY) Long idCategory, BigDecimal discount) throws SQLException {
        productService.changeDiscountForCategories(idCategory, discount);
        return new ModelAndView(Pages.REDIRECT + "admin/products");
    }
}
