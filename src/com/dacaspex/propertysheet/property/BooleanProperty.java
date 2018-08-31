package com.dacaspex.propertysheet.property;

import com.dacaspex.propertysheet.renderer.NullValidator;

public class BooleanProperty extends AbstractProperty<Boolean> {

    public BooleanProperty(String name, Boolean value) {
        super(name, value, new NullValidator());
    }
}
