package com.vladis1350.validate;

import com.vladis1350.auth.bean.User;
import com.vladis1350.auth.service.EmailValidator;
import com.vladis1350.constants.DataCondition;

public class UserValidator {

    private static EmailValidator emailValidator = new EmailValidator();

    public UserValidator(){}

    public static boolean validateUserName(String userName) {
        return (userName != null && userName.length() >= DataCondition.MIN_LENGTH_USER_NAME &&
                userName.length() <= DataCondition.MAX_LENGTH_USER_NAME);
    }

    public static boolean validateFirstName(String firstName) {
        return (firstName != null && firstName.length() >= DataCondition.MIN_LENGTH_NAME &&
                firstName.length() <= DataCondition.MAX_LENGTH_NAME);
    }

    public static boolean validateLastName(String lastName) {
        return (lastName != null && lastName.length() >= DataCondition.MIN_LENGTH_NAME &&
                lastName.length() <= DataCondition.MAX_LENGTH_NAME);
    }

    public static boolean validateEmail(String email) {
        return (email != null && emailValidator.validate(email));
    }

    public static boolean validatePassword(String password) {
        return (password != null && password.length() >= DataCondition.MIN_LENGTH_PASSWORD);
    }

    public static boolean checkValidateDataUser(User user) {
        return validateUserName(user.getUserName()) &&
                validateFirstName(user.getFirst_name()) &&
                validateLastName(user.getLast_name()) &&
                validateEmail(user.getEmail()) &&
                validatePassword(user.getPassword());
    }
}
