package com.dacaspex.propertysheet.renderer;

import com.dacaspex.propertysheet.validator.Validator;

public class NullValidator implements Validator {

    @Override
    public boolean validate(Object object) {
        return true;
    }
}
