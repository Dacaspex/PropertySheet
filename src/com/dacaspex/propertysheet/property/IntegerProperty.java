package com.dacaspex.propertysheet.property;

import com.dacaspex.propertysheet.validator.IntegerValidator;

public class IntegerProperty extends AbstractProperty<Integer> {

    public IntegerProperty(String name, Integer value) {
        super(name, value, new IntegerValidator());
    }
}
