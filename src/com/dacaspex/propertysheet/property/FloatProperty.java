package com.dacaspex.propertysheet.property;

import com.dacaspex.propertysheet.validator.FloatValidator;
import com.dacaspex.propertysheet.validator.Validator;

public class FloatProperty extends AbstractProperty<Float> {

    public FloatProperty(String name, Float value) {
        super(name, value, new FloatValidator());
    }

    public FloatProperty(String name, Float value, Validator validator) {
        super(name, value, validator);
    }
}
