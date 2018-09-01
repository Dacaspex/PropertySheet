package com.dacaspex.propertysheet.property;

import com.dacaspex.propertysheet.validator.StringValidator;
import com.dacaspex.propertysheet.validator.Validator;

public class StringProperty extends AbstractProperty<String> {

    public StringProperty(String name, String value) {
        super(name, value, new StringValidator());
    }

    public StringProperty(String name, String value, Validator validator) {
        super(name, value, validator);
    }
}
