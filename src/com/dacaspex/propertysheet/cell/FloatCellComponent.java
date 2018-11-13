package com.dacaspex.propertysheet.cell;

import com.dacaspex.propertysheet.editor.Keys;
import com.dacaspex.propertysheet.property.FloatProperty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class FloatCellComponent extends AbstractCellComponent {

    private FloatProperty property;
    private JTextField textField;

    public FloatCellComponent(FloatProperty property) {
        this.property = property;
        this.textField = new JTextField();

        textField.addKeyListener(new FloatCellComponent.KeyAdapter());
    }

    @Override
    public Component getTableCellEditorComponent(JTable jTable, Object o, boolean b, int i, int i1) {
        textField.setText(property.getValue().toString());

        return textField;
    }

    @Override
    public Object getCellEditorValue() {
        return property.getValue();
    }

    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i, int i1) {
        return new JLabel(property.getValue().toString());
    }

    private class KeyAdapter extends java.awt.event.KeyAdapter {
        @Override
        public void keyReleased(KeyEvent event) {
            if (Keys.ignoreKey(event.getKeyCode())) {
                return;
            }

            if (property.getValidator().validate(textField.getText())) {
                property.setValue(Float.parseFloat(textField.getText()));
                textField.setBackground(options.getBackgroundColor());
                eventDispatcher.dispatchUpdateEvent(property);
            } else {
                textField.setBackground(options.getInvalidColor());
            }
        }
    }
}
