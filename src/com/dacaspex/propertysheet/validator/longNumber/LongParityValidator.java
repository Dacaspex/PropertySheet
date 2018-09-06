package com.dacaspex.propertysheet.validator.longNumber;

import com.dacaspex.propertysheet.validator.Validator;

public class LongParityValidator implements Validator {

    protected boolean even;

    public LongParityValidator() {
        this(true);
    }

    public void setEven(boolean even) {
        this.even = even;
    }

    public LongParityValidator(boolean even) {
        this.even = even;
    }

    @Override
    public boolean validate(Object object) {
        long value = Long.parseLong((String) object);

        if (even) {
            return value % 2 == 0;
        } else {
            return value % 2 != 0;
        }
    }
}
