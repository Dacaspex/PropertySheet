package com.dacaspex.propertysheet;

import com.dacaspex.propertysheet.editor.*;
import com.dacaspex.propertysheet.event.EventDispatcher;
import com.dacaspex.propertysheet.event.PropertySheetEventListener;
import com.dacaspex.propertysheet.exception.PropertyNotSupportedException;
import com.dacaspex.propertysheet.property.*;
import com.dacaspex.propertysheet.property.selection.SelectionProperty;
import com.dacaspex.propertysheet.renderer.BooleanRenderer;
import com.dacaspex.propertysheet.renderer.ColorRenderer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.util.HashMap;

public class PropertySheet extends JTable {

    private int cursor;
    private PropertySheetOptions options;
    private EditorController editorController;
    private HashMap<Integer, TableCellRenderer> renderers;
    private PropertySheetModel propertySheetModel;
    private EventDispatcher eventDispatcher;

    public PropertySheet(PropertySheetOptions options) {

        // Set options
        this.options = options;

        // Initialise required variables
        this.cursor = 0;
        this.editorController = new EditorController();
        this.renderers = new HashMap<>();
        this.propertySheetModel = new PropertySheetModel(options.getHeaders());
        this.eventDispatcher = new EventDispatcher();
        PropertySheetCellEditor.setEventDispatcher(eventDispatcher);

        // Set table properties
        setModel(propertySheetModel);
        getColumnModel().getColumn(1).setCellEditor(editorController);
        setRowHeight(options.getRowHeight());
        getTableHeader().setReorderingAllowed(false);
    }

    public PropertySheetOptions getOptions() {
        return options;
    }

    public void addProperty(
            Property property,
            String display,
            PropertySheetCellEditor editor,
            TableCellRenderer renderer
    ) {
        propertySheetModel.addRow(new String[]{property.getName(), display});
        editorController.addEditor(cursor, editor);

        if (renderer != null) {
            renderers.put(cursor, renderer);
        }

        cursor++;
        eventDispatcher.dispatchPropertyAddedEvent(property);
    }

    public void addProperty(Property property, PropertySheetCellEditor editor, TableCellRenderer renderer) {
        addProperty(property, property.getValue().toString(), editor, renderer);
    }

    public void addProperty(Property property, String display, PropertySheetCellEditor editor) {
        addProperty(property, display, editor, null);
    }

    public void addProperty(Property property, PropertySheetCellEditor editor) {
        addProperty(property, property.getValue().toString(), editor, null);
    }

    public void addProperty(Property property) {
        if (property instanceof IntegerProperty) {
            addProperty(property, new IntegerEditor(property, this));
        } else if (property instanceof FloatProperty) {
            addProperty(property, new FloatEditor(property, this));
        } else if (property instanceof BooleanProperty) {
            addProperty(property, new BooleanEditor(property, this), new BooleanRenderer());
        } else if (property instanceof ColorProperty) {
            String color = String.format(
                    "#%06x",
                    ((ColorProperty) property).getValue().getRGB() & 0x00FFFFFF
            );
            addProperty(property, color, new ColorEditor(property, this), new ColorRenderer());
        } else if (property instanceof StringProperty) {
            addProperty(property, new StringEditor(property, this));
        } else if (property instanceof SelectionProperty) {
            addProperty(
                    property,
                    ((SelectionProperty) property).getItems().get(0).toString(),
                    new SelectionEditor((SelectionProperty) property, this)
            );
        } else {
            throw new PropertyNotSupportedException(property);
        }
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {

        if (column == 1) {
            return renderers.getOrDefault(row, super.getCellRenderer(row, column));
        }

        return super.getCellRenderer(row, column);
    }

    public void addEventListener(PropertySheetEventListener eventListener) {
        eventDispatcher.addEventListener(eventListener);
    }

    public void removeEventListener(PropertySheetEventListener eventListener) {
        eventDispatcher.removeEventListener(eventListener);
    }
}
