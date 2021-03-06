package com.dacaspex.propertysheet.validator.floatNumber;

import com.dacaspex.propertysheet.validator.Validator;

/**
 * Default implementation to validate float objects
 */
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
