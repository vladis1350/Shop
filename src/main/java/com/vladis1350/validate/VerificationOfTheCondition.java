package com.vladis1350.validate;

import java.util.Optional;

public class VerificationOfTheCondition {

    public static boolean checkOptional(Optional<String> optional) {
        return !optional.isPresent();
    }

}
