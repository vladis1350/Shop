package com.vladis1350.validate;

import com.vladis1350.constants.DataCondition;

public class UserValidator {

    public UserValidator(){}

    public static boolean validateUserName(String userName) {
        return (userName != null && userName.length() >= DataCondition.MIN_LENGTH_NAME &&
                userName.length() <= DataCondition.MAX_LENGTH_NAME);
    }
}
