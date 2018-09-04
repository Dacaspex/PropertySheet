package com.dacaspex.propertysheet;

import com.dacaspex.propertysheet.editor.*;
import com.dacaspex.propertysheet.event.PropertySheetEventListener;
import com.dacaspex.propertysheet.exception.PropertyNotSupportedException;
import com.dacaspex.propertysheet.property.*;
import com.dacaspex.propertysheet.renderer.BooleanRenderer;
import com.dacaspex.propertysheet.renderer.ColorRenderer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PropertySheet extends JTable {

    private String[] headers;
    private Color backgroundColor;
    private Color invalidColor;
    private int rowHeight;
    private int cursor;

    private ArrayList<Property> properties;

    private EditorController editorController;
    private HashMap<Integer, TableCellRenderer> renderers;
    private PropertySheetModel propertySheetModel;

    private ArrayList<PropertySheetEventListener> listeners;

    public PropertySheet() {

        // Set cosmetics
        this.headers = new String[]{"Property", "value"};
        this.backgroundColor = Color.WHITE;
        this.invalidColor = new Color(255, 114, 114);
        this.rowHeight = 30;

        // Initialise required variables
        this.cursor = 0;
        this.properties = new ArrayList<>();
        this.editorController = new EditorController();
        this.renderers = new HashMap<>();
        this.propertySheetModel = new PropertySheetModel(headers);
        this.listeners = new ArrayList<>();

        // Set table properties
        setModel(propertySheetModel);
        getColumnModel().getColumn(1).setCellEditor(editorController);
        setRowHeight(rowHeight);
        getTableHeader().setReorderingAllowed(false);
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getInvalidColor() {
        return invalidColor;
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

        // Update event listeners
        listeners.forEach(l -> l.onPropertyAdded(property));
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
        properties.add(property);

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
        listeners.add(eventListener);
    }

    public void removeEventListener(PropertySheetEventListener eventListener) {
        listeners.remove(eventListener);
    }

    public void dispatchUpdateEvent(Property property) {
        listeners.forEach(l -> l.onPropertyUpdated(property));
    }
}
