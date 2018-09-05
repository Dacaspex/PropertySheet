package com.dacaspex.propertysheet.renderer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Renders a checkbox for a boolean property
 */
public class BooleanRenderer extends JCheckBox implements TableCellRenderer {

    public BooleanRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column
    ) {

        if (value.toString().equals("true")) {
            this.setSelected(true);
        } else if (value.toString().equals("false")) {
            this.setSelected(false);
        } else {
            // Error
        }

        return this;

    }
}
