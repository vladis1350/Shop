package com.vladis1350.cinfiguration;

import com.vladis1350.constants.Http;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/header").setViewName("header");
        registry.addViewController("/" + Http.ADD_TO_CART + "/**").setViewName("addToCart");
        registry.addViewController("/shopping_cart").setViewName("shopping_cart");
        registry.addViewController("/signIn").setViewName("signIn");
        registry.addViewController("/signUp").setViewName("signUp");
        registry.addViewController("/saveProduct").setViewName("saveProduct");
        registry.addViewController("/admin/users").setViewName("admin/users");
        registry.addViewController("/admin/products").setViewName("admin/products");
        registry.addViewController("/admin/categories").setViewName("admin/categories");
        registry.addViewController("/admin/start_page").setViewName("admin/start_page");
    }

}
