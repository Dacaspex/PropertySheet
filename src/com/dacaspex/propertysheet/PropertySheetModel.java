package com.dacaspex.propertysheet;

import javax.swing.table.DefaultTableModel;

/**
 * Table model to make the property sheet work
 */
public class PropertySheetModel extends DefaultTableModel {

    public PropertySheetModel(String[] headers) {
        super(null, headers);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return (column == 1);
    }

}
