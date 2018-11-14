package com.dacaspex.propertysheet.cell;

import com.dacaspex.propertysheet.property.selection.Item;
import com.dacaspex.propertysheet.property.selection.SelectionProperty;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.event.ItemEvent;

public class SelectionCellComponent extends AbstractCellComponent {

    private SelectionProperty property;
    private JComboBox<Item> comboBox;

    public SelectionCellComponent(SelectionProperty property) {
        this.property = property;
        this.comboBox = new JComboBox<>();

        property.getItems().forEach(e -> comboBox.addItem((Item) e));
        comboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (comboBox.getSelectedItem() == null) {
                    return;
                }

                property.setValue(((Item) comboBox.getSelectedItem()).getValue());
                eventDispatcher.dispatchUpdateEvent(property);
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable jTable, Object o, boolean b, int i, int i1) {
        return comboBox;
    }

    @Override
    public Object getCellEditorValue() {
        return comboBox.getSelectedItem();
    }

    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i, int i1) {
        JLabel label = new JLabel();
        if (comboBox.getSelectedItem() != null) {
            label.setText(comboBox.getSelectedItem().toString());
        }

        return label;
    }
}
