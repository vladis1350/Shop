package com.vladis1350.auth.controllers;

import com.vladis1350.auth.bean.User;
import com.vladis1350.auth.service.UserService;
import com.vladis1350.validate.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView addNewUser(@RequestParam(value = "userName", required = false) String userName,
                                   @RequestParam(value = "first_name", required = false) String firstName,
                                   @RequestParam(value = "last_name", required = false) String lastName,
                                   @RequestParam(value = "email", required = false) String email,
                                   @RequestParam(value = "password", required = false) String password,
                                   @RequestParam Optional<String> confPassword) {
        ModelAndView mod = new ModelAndView("/signUp");
        User userFromDb = userService.findUserByUserName(userName);
        User newUser = User.builder()
                .userName(userName)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .build();

        if (userFromDb != null) {
            mod.addObject("userNameMessage", "Пользователь с таким именем уже существует!");
            return mod;
        }
        if (!confPassword.isPresent() || !newUser.getPassword().equals(confPassword.get())) {
            mod.addObject("confPasswordMessage", "Пароли не совпадают.");
        }
        if (!UserValidator.validateUserName(newUser.getUserName())) {
            mod.addObject("userNameMessage", "Логин должен быть не менее 5-х символов.");
        }
        if (!UserValidator.validateFirstName(newUser.getFirstName())) {
            mod.addObject("firstNameMessage", "Введите имя более 3-x символов.");
        }
        if (!UserValidator.validateLastName(newUser.getLastName())) {
            mod.addObject("lastNameMessage", "Введите фамилию более 3-х символов.");
        }
        if (!UserValidator.validatePassword(newUser.getPassword())) {
            mod.addObject("passwordMessage", "Придумайте пароль более 5 символов.");
        }

        if (!UserValidator.validateEmail(newUser.getEmail())) {
            mod.addObject("emailMessage", "Не верный Email.");
        }
        if (UserValidator.checkValidateDataUser(newUser) &&
                (!confPassword.isPresent() || newUser.getPassword().equals(confPassword.get()))) {
            userService.saveUser(newUser, Optional.empty());
            mod.addObject("successRegistration", "Пользователь успешно зарегистрирован!");
            mod.addObject("user", new User());
            mod.setViewName("signUp");
        }
        return mod;
    }
}
