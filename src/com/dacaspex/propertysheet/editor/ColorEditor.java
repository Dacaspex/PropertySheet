package com.dacaspex.propertysheet.editor;

import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.property.Property;

import javax.swing.*;
import java.awt.*;

public class ColorEditor extends PropertySheetCellEditor {

    protected JButton delegate;
    protected Property<Color> property;

    public ColorEditor(Property<Color> property, PropertySheet sheet) {
        super(property, sheet, new JTextField());

        this.property = property;
        this.delegate = new JButton() {
            @Override
            public void paintComponent(Graphics g) {
                g.setColor(ColorEditor.this.property.getValue());
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
            }
        };

        delegate.addActionListener(e -> {
            Color color = JColorChooser.showDialog(delegate, "Choose colour", property.getValue());
            this.changeColor(color);
            sheet.dispatchUpdateEvent(property);
        });
    }

    public Object getCellEditorValue() {
        return String.format("#%06x", property.getValue().getRGB() & 0x00FFFFFF);
    }

    protected void changeColor(Color color) {
        if (color != null) {
            property.setValue(color);
            delegate.setBackground(color);
        }
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        String hex = (String) value;
        Color color = new Color(
                Integer.valueOf(hex.substring(1, 3), 16),
                Integer.valueOf(hex.substring(3, 5), 16),
                Integer.valueOf(hex.substring(5, 7), 16)
        );

        changeColor(color);
        return delegate;
    }
}
