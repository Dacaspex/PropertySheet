package com.dacaspex.propertysheet.property.selection;

import com.dacaspex.propertysheet.property.AbstractProperty;
import com.dacaspex.propertysheet.validator.NullValidator;

import java.util.ArrayList;

public class SelectionProperty<T> extends AbstractProperty<T> {

    protected ArrayList<Item<T>> items;

    public SelectionProperty(String name, ArrayList<Item<T>> items) {
        super(name, items.get(0).getValue(), new NullValidator());

        this.items = items;
    }

    public ArrayList<Item<T>> getItems() {
        return items;
    }
}
