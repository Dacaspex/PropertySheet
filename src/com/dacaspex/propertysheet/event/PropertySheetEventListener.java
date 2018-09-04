package com.dacaspex.propertysheet.event;

import com.dacaspex.propertysheet.property.Property;

public interface PropertySheetEventListener {

    public void onPropertyUpdated(Property property);

    public void onPropertyAdded(Property property);
}
