package com.dacaspex.propertysheet.property;

import com.dacaspex.propertysheet.validator.integer.IntegerValidator;
import com.dacaspex.propertysheet.validator.Validator;

public class IntegerProperty extends AbstractProperty<Integer> {

    public IntegerProperty(String name, Integer value) {
        super(name, value, new IntegerValidator());
    }

    public IntegerProperty(String name, Integer value, Validator validator) {
        super(name, value, validator);
    }
}
