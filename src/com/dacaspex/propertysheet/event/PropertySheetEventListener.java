package com.dacaspex.propertysheet.event;

import com.dacaspex.propertysheet.property.Property;

public interface PropertySheetEventListener {

    public void onPropertyUpdated(Property property);

}
