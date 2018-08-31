package com.dacaspex.propertysheet.property;

public class IntegerProperty implements Property<Integer> {

    private String name;
    private int value;

    public IntegerProperty(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public IntegerProperty(int value) {
        this("", value);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
