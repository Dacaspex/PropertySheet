package com.dacaspex.propertysheet.property;

import com.dacaspex.propertysheet.validator.Validator;

public interface Property<T> {

    public String getName();

    public T getValue();

    public void setValue(T value);

    public Validator getValidator();
}
