package com.dacaspex.propertysheet.validator.integer;

import com.dacaspex.propertysheet.validator.Validator;

public class IntegerZeroPolicyValidator implements Validator {

    protected boolean allowZero;

    public IntegerZeroPolicyValidator() {
        this(true);
    }

    public IntegerZeroPolicyValidator(boolean allowZero) {
        this.allowZero = allowZero;
    }

    @Override
    public boolean validate(Object object) {
        int value = Integer.parseInt((String) object);

        return (value == 0 && allowZero) || value != 0;
    }
}
