package com.dacaspex.propertysheet.validator.longNumber;

import com.dacaspex.propertysheet.validator.Validator;

public class LongZeroPolicyValidator implements Validator {

    protected boolean allowZero;

    public LongZeroPolicyValidator() {
        this(true);
    }

    public LongZeroPolicyValidator(boolean allowZero) {
        this.allowZero = allowZero;
    }

    @Override
    public boolean validate(Object object) {
        long value = Long.parseLong((String) object);

        return (value == 0 && allowZero) || value != 0;
    }
}
