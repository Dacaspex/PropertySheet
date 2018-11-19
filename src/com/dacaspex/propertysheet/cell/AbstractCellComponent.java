package com.dacaspex.propertysheet.cell;

import com.dacaspex.propertysheet.PropertySheetOptions;
import com.dacaspex.propertysheet.event.EventDispatcher;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;

public abstract class AbstractCellComponent extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

    protected PropertySheetOptions options;
    protected EventDispatcher eventDispatcher;

    @Override
    public abstract Component getTableCellEditorComponent(JTable jTable, Object o, boolean b, int i, int i1);

    @Override
    public abstract Object getCellEditorValue();

    @Override
    public abstract Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i, int i1);

    public void init(PropertySheetOptions options, EventDispatcher eventDispatcher) {
        this.options = options;
        this.eventDispatcher = eventDispatcher;
    }
}
