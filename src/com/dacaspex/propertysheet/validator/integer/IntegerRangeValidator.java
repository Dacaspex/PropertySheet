package com.dacaspex.propertysheet.validator.integer;

import com.dacaspex.propertysheet.validator.Validator;

public class IntegerRangeValidator implements Validator {

    protected int lowerBound;
    protected int upperBound;

    public IntegerRangeValidator() {
        this(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public IntegerRangeValidator(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public boolean validate(Object object) {
        int value = Integer.parseInt((String) object);

        return value >= lowerBound && value <= upperBound;
    }
}
