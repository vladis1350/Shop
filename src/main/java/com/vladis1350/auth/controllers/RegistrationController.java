package com.vladis1350.auth.controllers;

import com.vladis1350.auth.bean.Role;
import com.vladis1350.auth.bean.User;
import com.vladis1350.auth.repositories.UserRepository;
import com.vladis1350.validate.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/signUp")
    public ModelAndView registration() {
        ModelAndView mod = new ModelAndView("/signUp");
        mod.addObject("user", new User());
        return mod;
    }

    @PostMapping(value = "/signUp")
    public ModelAndView addNewUser(@Valid User user,
                                   @RequestParam Optional<String> confPassword) {
        ModelAndView mod = new ModelAndView();
        User userFromDb = userRepository.findByUserName(user.getUserName());
        if (userFromDb != null) {
            mod.addObject("userNameMessage", "Пользователь с таким именем уже существует!");
            mod.setViewName("/signUp");
            return mod;
        }

        if (!confPassword.isPresent() || !user.getPassword().equals(confPassword.get())) {
            mod.addObject("passwordMessage","Пароли не совпадают.");
        }

        if (!UserValidator.validateUserName(user.getFirst_name())) {
            mod.addObject("userName","Логин должен быть больше 3-х символов.");
        }
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.ROLE_USER));
            userRepository.save(user);
            mod.setViewName("redirect:/signIn");
        return mod;
    }
}
