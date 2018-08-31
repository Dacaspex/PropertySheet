package com.dacaspex.propertysheet.property;

import com.dacaspex.propertysheet.validator.FloatValidator;

public class FloatProperty extends AbstractProperty<Float> {

    public FloatProperty(String name, Float value) {
        super(name, value, new FloatValidator());
    }
}
