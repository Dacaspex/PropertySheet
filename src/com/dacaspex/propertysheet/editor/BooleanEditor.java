package com.dacaspex.propertysheet.editor;

import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.property.Property;

import javax.swing.*;
import javax.swing.table.TableCellEditor;

public class BooleanEditor extends DefaultCellEditor implements TableCellEditor {

    private JCheckBox checkBox;

    public BooleanEditor(Property<Boolean> property, PropertySheet sheet) {
        super(new JCheckBox());

        this.checkBox = (JCheckBox) super.getComponent();

        checkBox.addActionListener(e -> {
            property.setValue(checkBox.isSelected());
            sheet.dispatchUpdateEvent(property);
        });
    }
}
