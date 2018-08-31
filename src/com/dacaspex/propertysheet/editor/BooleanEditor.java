package com.dacaspex.propertysheet.editor;

import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.property.BooleanProperty;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BooleanEditor extends DefaultCellEditor implements TableCellEditor, ActionListener {

    private static final long serialVersionUID = 3464531358342477564L;

    private JCheckBox checkBox;
    private BooleanProperty property;
    private PropertySheet table;

    public BooleanEditor(BooleanProperty property, PropertySheet table) {
        super(new JCheckBox());

        this.checkBox = (JCheckBox) super.getComponent();
        this.property = property;
        this.table = table;

        checkBox.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
//        property.setValue(checkBox.isSelected());
//        table.requestUpdate(false);
    }
}
