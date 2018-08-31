package com.dacaspex.propertysheet.validator;

public class FloatValidator implements Validator {

    @Override
    public boolean validate(Object object) {
        return isFloat(object);
    }

    protected boolean isFloat(Object object) {
        try {
            Float.parseFloat((String) object);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
