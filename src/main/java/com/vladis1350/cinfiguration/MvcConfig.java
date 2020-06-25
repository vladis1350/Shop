package com.vladis1350.cinfiguration;

import com.vladis1350.constants.Http;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/header").setViewName("header");
        registry.addViewController(Http.NEW_PRODUCT).setViewName("new_product");
        registry.addViewController(Http.SET_DISCOUNT).setViewName("set_discount");
        registry.addViewController(Http.EDIT_PRODUCT).setViewName("edit_product");
        registry.addViewController(Http.NEW_CATEGORY).setViewName("category_form");
        registry.addViewController("/signIn").setViewName("signIn");
        registry.addViewController("/signUp").setViewName("signUp");
        registry.addViewController("/saveProduct").setViewName("saveProduct");
        registry.addViewController("/admin/users").setViewName("admin/users");
        registry.addViewController("/admin/products").setViewName("admin/products");
        registry.addViewController("/admin/categories").setViewName("admin/categories");
        registry.addViewController("/admin/start_page").setViewName("admin/start_page");
    }

}
