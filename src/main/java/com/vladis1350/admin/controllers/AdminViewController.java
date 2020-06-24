package com.vladis1350.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminViewController {

    @GetMapping("/admin/basic-table")
    public ModelAndView showAdminPanel() {
        return new ModelAndView("admin/basic-table");
    }

}
