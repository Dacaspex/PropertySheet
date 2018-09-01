package com.dacaspex.propertysheet.editor;

import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.property.BooleanProperty;

import javax.swing.*;
import javax.swing.table.TableCellEditor;

public class BooleanEditor extends DefaultCellEditor implements TableCellEditor {

    private static final long serialVersionUID = 3464531358342477564L;

    private JCheckBox checkBox;

    public BooleanEditor(BooleanProperty property, PropertySheet sheet) {
        super(new JCheckBox());

        this.checkBox = (JCheckBox) super.getComponent();

        checkBox.addActionListener(e -> {
            property.setValue(checkBox.isSelected());
            sheet.dispatchUpdateEvent(property);
        });
    }
}
