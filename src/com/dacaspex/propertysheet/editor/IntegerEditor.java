package com.dacaspex.propertysheet.editor;

import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.property.IntegerProperty;
import com.dacaspex.propertysheet.property.Property;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class IntegerEditor extends DefaultCellEditor implements TableCellEditor, KeyListener {

    private static final long serialVersionUID = 3464531358342477564L;

    private IntegerProperty property;
    private PropertySheet sheet;

    public IntegerEditor(IntegerProperty property, PropertySheet sheet) {
        super(new JTextField());

        this.property = property;
        this.sheet = sheet;

        super.getComponent().addKeyListener(this);
    }

//    @Override
//    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
//    }

    @Override
    public void keyPressed(KeyEvent event) {
        return;
    }

    @Override
    public void keyReleased(KeyEvent event) {

        JTextField textField = (JTextField) super.getComponent();
        String value = (String) textField.getText();

        // TODO: Validate input
        // TODO: Fire update event
    }

    @Override
    public void keyTyped(KeyEvent event) {
        return;
    }

}
