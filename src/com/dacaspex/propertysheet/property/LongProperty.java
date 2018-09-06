package com.dacaspex.propertysheet.property;

import com.dacaspex.propertysheet.validator.longNumber.LongValidator;
import com.dacaspex.propertysheet.validator.Validator;

public class LongProperty extends AbstractProperty<Long> {

    public LongProperty(String name, Long value) {
        super(name, value, new LongValidator());
    }

    public LongProperty(String name, Long value, Validator validator) {
        super(name, value, validator);
    }
}
