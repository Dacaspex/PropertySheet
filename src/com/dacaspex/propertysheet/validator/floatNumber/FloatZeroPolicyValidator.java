package com.dacaspex.propertysheet.validator.floatNumber;

import com.dacaspex.propertysheet.validator.Validator;

public class FloatZeroPolicyValidator implements Validator {

    protected boolean allowZero;

    public FloatZeroPolicyValidator(boolean allowZero) {
        this.allowZero = allowZero;
    }

    public FloatZeroPolicyValidator() {
        this(true);
    }

    @Override
    public boolean validate(Object object) {
        double value = Float.parseFloat((String) object);

        return value != 0 || allowZero;
    }
}
