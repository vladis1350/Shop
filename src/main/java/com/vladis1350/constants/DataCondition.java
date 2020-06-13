package com.vladis1350.constants;

import java.math.BigDecimal;

public class DataCondition {

    private DataCondition() {
    }

    public static final int MIN_LENGTH_NAME = 3;
    public static final int MAX_LENGTH_NAME = 55;

    public static final BigDecimal MIN_PRICE = BigDecimal.valueOf(0.1);
    public static final BigDecimal MIN_DISCOUNT = BigDecimal.valueOf(0);
    public static final BigDecimal MAX_DISCOUNT = BigDecimal.valueOf(100);
}
