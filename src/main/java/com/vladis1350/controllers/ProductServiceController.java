package com.vladis1350.controllers;

import com.vladis1350.bean.Product;
import com.vladis1350.constants.EntityConstant;
import com.vladis1350.constants.Http;
import com.vladis1350.constants.Pages;
import com.vladis1350.service.CategoryService;
import com.vladis1350.service.ProductService;
import com.vladis1350.validate.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
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
        model.addAttribute(EntityConstant.PRODUCTS, productService.findAll());
        model.addAttribute(EntityConstant.CATEGORIES, categoryService.findAll());
        return Pages.HOME;
    }

    @GetMapping(value = Http.NEW_PRODUCT)
    public ModelAndView showNewProductsForm() throws SQLException {
        ModelAndView mod = new ModelAndView();
        mod.addObject(EntityConstant.UNIT_PRODUCT, new Product());
        mod.addObject(EntityConstant.CATEGORIES, categoryService.findAll());
        mod.setViewName(Pages.NEW_PRODUCT);
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

    @PostMapping(value = Http.SAVE_PRODUCT)
    public ModelAndView showProduct(@RequestParam(value = "product_name", required = false) String product_name,
                                    @RequestParam(value = "price", required = false) BigDecimal price,
                                    @RequestParam(value = "discount", required = false) BigDecimal discount,
                                    @RequestParam(value = "category", required = false) String category,
                                    @RequestParam(value = "description", required = false) String description) throws SQLException {
        ModelAndView mod = new ModelAndView(Pages.NEW_PRODUCT);
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
        return mod;
    }

    @PostMapping(value = Http.SAVE_EDIT_PRODUCT)
    public String saveEditProduct(@ModelAttribute(EntityConstant.UNIT_PRODUCT) Product product) throws SQLException {
        if (ProductValidator.checkValidateDataProduct(product)) {
            productService.update(product);
        } else {

        }
        return Pages.REDIRECT + Pages.HOME;
    }

    @PostMapping(value = Http.CANCEL)
    public String clearFilterAndSearch() {
        return Pages.REDIRECT + Pages.HOME;
    }

    @PostMapping(value = Http.SEARCH)
    public String searchProductById(@ModelAttribute(EntityConstant.ENTITY_ID_PRODUCT) Long id_product, Model model) throws SQLException {
        Product product1 = productService.getById(id_product);
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

    @PostMapping(value = Http.SET_DISCOUNT)
    public String setDiscountForCategory(@ModelAttribute(EntityConstant.UNIT_CATEGORY) Long id_category, BigDecimal discount) throws SQLException {
        productService.changeDiscountForCategories(id_category, discount);
        return Pages.REDIRECT + Pages.HOME;
    }
}
