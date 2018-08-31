package com.dacaspex.propertysheet.validator;

public class IntegerValidator implements Validator {

    @Override
    public boolean validate(Object object) {
        return isInteger(object);
    }

    protected boolean isInteger(Object object) {
        try {
            Integer.parseInt((String) object);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
