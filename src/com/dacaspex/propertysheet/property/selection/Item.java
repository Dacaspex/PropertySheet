package com.dacaspex.propertysheet.property.selection;

public class Item<T> {

    protected T value;
    protected String description;

    public Item(T value, String description) {
        this.value = value;
        this.description = description;
    }

    public T getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
