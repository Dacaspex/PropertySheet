package com.dacaspex.propertysheet.event;

import com.dacaspex.propertysheet.property.Property;

import java.util.ArrayList;

public class EventDispatcher {

    private ArrayList<PropertySheetEventListener> listeners;

    public EventDispatcher() {
        this.listeners = new ArrayList<>();
    }

    public void addEventListener(PropertySheetEventListener eventListener) {
        listeners.add(eventListener);
    }

    public void removeEventListener(PropertySheetEventListener eventListener) {
        listeners.remove(eventListener);
    }

    public void dispatchUpdateEvent(Property property) {
        listeners.forEach(l -> l.onPropertyUpdated(property));
    }

    public void dispatchPropertyAddedEvent(Property property) {
        listeners.forEach(l -> l.onPropertyAdded(property));
    }
}
