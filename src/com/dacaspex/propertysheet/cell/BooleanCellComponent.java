package com.dacaspex.propertysheet.cell;

import com.dacaspex.propertysheet.property.BooleanProperty;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import java.awt.Component;

public class BooleanCellComponent extends AbstractCellComponent {

    private BooleanProperty property;
    private JCheckBox checkBox;

    public BooleanCellComponent(BooleanProperty property) {
        this.property = property;
        this.checkBox = new JCheckBox();

        checkBox.setSelected(property.getValue());
        checkBox.addActionListener(e -> {
            property.setValue(checkBox.isSelected());
            eventDispatcher.dispatchUpdateEvent(property);
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable jTable, Object o, boolean b, int i, int i1) {
        return checkBox;
    }

    @Override
    public Object getCellEditorValue() {
        return property.getValue();
    }

    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i, int i1) {
        return checkBox;
    }
}
