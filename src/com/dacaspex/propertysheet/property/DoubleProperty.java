package com.dacaspex.propertysheet.property;

import com.dacaspex.propertysheet.validator.doubleNumber.DoubleValidator;
import com.dacaspex.propertysheet.validator.Validator;

public class DoubleProperty extends AbstractProperty<Double> {

    public DoubleProperty(String name, Double value) {
        super(name, value, new DoubleValidator());
    }

    public DoubleProperty(String name, Double value, Validator validator) {
        super(name, value, validator);
    }
}
