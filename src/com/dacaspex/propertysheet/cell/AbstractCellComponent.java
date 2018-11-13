package com.dacaspex.propertysheet.cell;

import com.dacaspex.propertysheet.PropertySheetOptions;
import com.dacaspex.propertysheet.event.EventDispatcher;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public abstract class AbstractCellComponent extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

    protected static PropertySheetOptions options;
    protected static EventDispatcher eventDispatcher;

    @Override
    public abstract Component getTableCellEditorComponent(JTable jTable, Object o, boolean b, int i, int i1);

    @Override
    public abstract Object getCellEditorValue();

    @Override
    public abstract Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i, int i1);

    public static void init(PropertySheetOptions options, EventDispatcher eventDispatcher) {
        AbstractCellComponent.options = options;
        AbstractCellComponent.eventDispatcher = eventDispatcher;
    }
}
