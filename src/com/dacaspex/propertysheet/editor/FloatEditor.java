package com.dacaspex.propertysheet.editor;

import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.property.FloatProperty;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FloatEditor extends DefaultCellEditor implements TableCellEditor, KeyListener {

    private FloatProperty property;
    private PropertySheet table;

    public FloatEditor(FloatProperty property, PropertySheet table) {
        super(new JTextField());

        this.property = property;
        this.table = table;

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

        JTextField textField = (JTextField) super.getComponent();
        String value = (String) textField.getText();

        // TODO: Validate
        // TODO: Fire update event

    }

    @Override
    public void keyTyped(KeyEvent event) {
        return;
    }

}
