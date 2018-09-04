package com.dacaspex.propertysheet.property.selection;

import com.dacaspex.propertysheet.property.AbstractProperty;
import com.dacaspex.propertysheet.validator.NullValidator;

import java.util.ArrayList;

public class SelectionProperty<T> extends AbstractProperty<Item> {

    protected ArrayList<Item> items;

    public SelectionProperty(String name, ArrayList<Item> items) {
        super(name, items.get(0), new NullValidator());

        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
