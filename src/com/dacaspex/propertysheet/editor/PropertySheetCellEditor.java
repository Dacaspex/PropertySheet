package com.dacaspex.propertysheet.editor;

import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.event.EventDispatcher;
import com.dacaspex.propertysheet.property.Property;

import javax.swing.*;
import javax.swing.table.TableCellEditor;

public class PropertySheetCellEditor extends DefaultCellEditor implements TableCellEditor {

    protected PropertySheet sheet;

    protected static EventDispatcher eventDispatcher;

    public PropertySheetCellEditor(Property property, PropertySheet sheet) {
        super(new JTextField());

        this.sheet = sheet;
    }

    public PropertySheetCellEditor(Property property, PropertySheet sheet, JTextField textField) {
        super(textField);

        this.sheet = sheet;
    }

    public PropertySheetCellEditor(Property property, PropertySheet sheet, JCheckBox checkBox) {
        super(checkBox);

        this.sheet = sheet;
    }

    public PropertySheetCellEditor(Property property, PropertySheet sheet, JComboBox comboBox) {
        super(comboBox);

        this.sheet = sheet;
    }

    public static void setEventDispatcher(EventDispatcher eventDispatcher) {
        PropertySheetCellEditor.eventDispatcher = eventDispatcher;
    }
}
