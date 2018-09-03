package com.dacaspex.propertysheet.exception;

import com.dacaspex.propertysheet.property.Property;

public class PropertyNotSupportedException extends RuntimeException {

    public PropertyNotSupportedException(Property property) {
        super("Could not find default editor/renderer for property '" + property.getName() + "'");
    }
}
