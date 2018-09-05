package com.dacaspex.propertysheet.event;

import com.dacaspex.propertysheet.property.Property;

/**
 * Event listener interface
 * <p>
 * Objects implementing this interface can subscribe to events coming from the
 * property sheet.
 */
public interface PropertySheetEventListener {

    /**
     * Fired when a property is successfully updated
     *
     * @param property Property
     */
    public void onPropertyUpdated(Property property);

    /**
     * Fired when a property is successfully added to the sheet
     *
     * @param property Property
     */
    public void onPropertyAdded(Property property);
}
