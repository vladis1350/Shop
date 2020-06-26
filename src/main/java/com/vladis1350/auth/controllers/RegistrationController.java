package com.vladis1350.auth.controllers;

import com.vladis1350.auth.bean.User;
import com.vladis1350.auth.bean.UserRoles;
import com.vladis1350.auth.repositories.UserRepository;
import com.vladis1350.auth.service.UserService;
import com.vladis1350.validate.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

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
        User userFromDb = userService.findUserByUserName(user.getUserName());
        if (userFromDb != null) {
            mod.addObject("userNameMessage", "Пользователь с таким именем уже существует!");
            return mod;
        }
        if (!confPassword.isPresent() || !user.getPassword().equals(confPassword.get())) {
            mod.addObject("confPasswordMessage", "Пароли не совпадают.");
        }
        if (!UserValidator.validateUserName(user.getUserName())) {
            mod.addObject("userNameMessage", "Логин должен быть не менее 5-х символов.");
        }
        if (!UserValidator.validateFirstName(user.getFirst_name())) {
            mod.addObject("firstNameMessage", "Введите имя более 3-x символов.");
        }
        if (!UserValidator.validateLastName(user.getLast_name())) {
            mod.addObject("lastNameMessage", "Введите фамилию более 3-х символов.");
        }
        if (!UserValidator.validatePassword(user.getPassword())) {
            mod.addObject("passwordMessage", "Придумайте пароль более 5 символов.");
        }

        if (!UserValidator.validateEmail(user.getEmail())) {
            mod.addObject("emailMessage", "Не верный Email.");
        }
        if (UserValidator.checkValidateDataUser(user)) {
            userService.saveUser(user, Optional.empty());
            mod.addObject("successRegistration", "Пользователь успешно зарегистрирован!");
            mod.setViewName("redirect:/signIn");
        }
        mod.setViewName("/signUp");
        return mod;
    }
}
