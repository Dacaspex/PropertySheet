package com.dacaspex.propertysheet.editor;

import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.property.BooleanProperty;

import javax.swing.*;
import javax.swing.table.TableCellEditor;

public class BooleanEditor extends DefaultCellEditor implements TableCellEditor {

    private static final long serialVersionUID = 3464531358342477564L;

    private JCheckBox checkBox;
    private BooleanProperty property;
    private PropertySheet table;

    public BooleanEditor(BooleanProperty property, PropertySheet table) {
        super(new JCheckBox());

        this.checkBox = (JCheckBox) super.getComponent();
        this.property = property;
        this.table = table;

        checkBox.addActionListener(e -> {
            property.setValue(checkBox.isSelected());
            // TODO: Fire update event
        });
    }
}
