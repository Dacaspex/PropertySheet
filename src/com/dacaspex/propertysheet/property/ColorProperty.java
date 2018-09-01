package com.dacaspex.propertysheet.property;

import com.dacaspex.propertysheet.validator.NullValidator;

import java.awt.*;

public class ColorProperty extends AbstractProperty<Color> {

    public ColorProperty(String name, Color value) {
        super(name, value, new NullValidator());
    }
}
