package com.dacaspex.propertysheet.validator;

public class NullValidator implements Validator {

    @Override
    public boolean validate(Object object) {
        return true;
    }
}
