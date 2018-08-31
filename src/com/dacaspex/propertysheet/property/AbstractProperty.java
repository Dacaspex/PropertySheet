package com.dacaspex.propertysheet.property;

public class AbstractProperty<T> implements Property<T> {

    protected T value;
    protected String name;

    public AbstractProperty(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public AbstractProperty(T value) {
        this("", value);
    }

    public AbstractProperty(String name) {
        this(name, null);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public T getValue() {
        return value;
    }
}
