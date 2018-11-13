package com.dacaspex.propertysheet.cell;

import com.dacaspex.propertysheet.property.ColorProperty;

import javax.swing.*;
import java.awt.*;

public class ColorCellComponent extends AbstractCellComponent {

    private ColorProperty property;
    private JButton delegate;

    public ColorCellComponent(ColorProperty property) {
        this.property = property;
        this.delegate = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(ColorCellComponent.this.property.getValue());
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
            }
        };

        delegate.addActionListener(e -> {
            // Blocking call to select a colour
            Color color = JColorChooser.showDialog(delegate, "Choose colour", property.getValue());
            property.setValue(color);
            eventDispatcher.dispatchUpdateEvent(property);
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable jTable, Object o, boolean b, int i, int i1) {
        return delegate;
    }

    @Override
    public Object getCellEditorValue() {
        return property.getValue();
    }

    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i, int i1) {
        return delegate;
    }
}
