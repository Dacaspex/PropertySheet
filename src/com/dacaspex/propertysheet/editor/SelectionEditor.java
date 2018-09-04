package com.dacaspex.propertysheet.editor;

import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.property.selection.Item;
import com.dacaspex.propertysheet.property.selection.SelectionProperty;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SelectionEditor extends PropertySheetCellEditor implements ItemListener {

    private SelectionProperty property;
    private PropertySheet sheet;
    private JComboBox comboBox;

    public SelectionEditor(SelectionProperty property, PropertySheet table) {
        super(property, table, new JComboBox<>(generateList(property)));

        this.property = property;
        this.sheet = table;
        this.comboBox = (JComboBox) super.getComponent();

        comboBox.addItemListener(this);

    }

    private static Item[] generateList(SelectionProperty property) {

        Item[] list = new Item[property.getItems().size()];

        int index = 0;
        for (Object item : property.getItems()) {
            list[index++] = (Item<?>) item;
        }

        return list;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {

        if (event.getStateChange() == ItemEvent.SELECTED) {
            property.setValue(((Item<String>) comboBox.getSelectedItem()).getValue());
            eventDispatcher.dispatchUpdateEvent(property);
        }

    }

}
