package com.dacaspex.propertysheet.validator;

public class DoubleValidator implements Validator {

    @Override
    public boolean validate(Object object) {
        return isDouble(object);
    }

    protected boolean isDouble(Object object) {
        try {
            Double.parseDouble((String) object);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
