package com.dacaspex.propertysheet.property;

import com.dacaspex.propertysheet.validator.Validator;

public class AbstractProperty<T> implements Property<T> {

    protected T value;
    protected String name;
    protected Validator validator;

    public AbstractProperty(String name, T value, Validator validator) {
        this.name = name;
        this.value = value;
        this.validator = validator;
    }

    public AbstractProperty(T value) {
        this("", value, null);
    }

    public AbstractProperty(String name) {
        this(name, null, null);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public Validator getValidator() {
        return validator;
    }
}
