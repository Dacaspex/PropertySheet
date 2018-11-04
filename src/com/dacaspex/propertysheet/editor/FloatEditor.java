package com.dacaspex.propertysheet.editor;

import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.property.Property;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FloatEditor extends PropertySheetCellEditor implements KeyListener {

    protected Property<Float> property;

    public FloatEditor(Property<Float> property, PropertySheet sheet) {
        super(property, sheet, new JTextField());

        this.property = property;

        super.getComponent().addKeyListener(this);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        return;
    }

    @Override
    public void keyReleased(KeyEvent event) {

        if (Keys.ignoreKey(event.getKeyCode())) {
            return;
        }

        JTextField textField = (JTextField) super.getComponent();
        String value = textField.getText();

        if (property.getValidator().validate(value)) {
            property.setValue(Float.parseFloat(value));
            textField.setBackground(sheet.getOptions().getBackgroundColor());

            eventDispatcher.dispatchUpdateEvent(property);
        } else {
            textField.setBackground(sheet.getOptions().getInvalidColor());
        }
    }

    @Override
    public void keyTyped(KeyEvent event) {
        return;
    }

}
