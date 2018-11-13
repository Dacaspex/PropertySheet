package com.dacaspex.propertysheet.editor;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.HashMap;

public class EditorController extends AbstractCellEditor implements TableCellEditor {

    private HashMap<Integer, TableCellEditor> editors;
    private TableCellEditor lastSelected;

    public EditorController() {
        editors = new HashMap<>();
    }

    public void addEditor(int key, TableCellEditor editor) {
        editors.put(key, editor);
    }

    public void clear() {
        editors.clear();
    }

    @Override
    public Object getCellEditorValue() {
        return lastSelected.getCellEditorValue();
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table,
            Object value,
            boolean isSelected,
            int row,
            int column
    ) {
        if (column == 1) {
            lastSelected = editors.get(row);
        }

        return lastSelected.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

}
