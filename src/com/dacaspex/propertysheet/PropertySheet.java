package com.dacaspex.propertysheet;

import com.dacaspex.propertysheet.editor.*;
import com.dacaspex.propertysheet.event.PropertySheetEventListener;
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

    public <T> void addProperty(Property property) {
        properties.add(property);

        // TODO: The adding of renderes/editors should be redone to make sure elements can be deleted accordingly

        if (property instanceof IntegerProperty) {

            propertySheetModel.addRow(new String[]{property.getName(), property.getValue().toString()});
            editorController.addEditor(cursor++, new IntegerEditor(property, this));

        } else if (property instanceof FloatProperty) {

            propertySheetModel.addRow(new String[]{property.getName(), property.getValue().toString()});
            editorController.addEditor(cursor++, new FloatEditor(property, this));

        } else if (property instanceof BooleanProperty) {

            propertySheetModel.addRow(new String[]{property.getName(), property.getValue().toString()});
            editorController.addEditor(cursor, new BooleanEditor(property, this));
            renderers.put(cursor++, new BooleanRenderer());

        } else if (property instanceof ColorProperty) {

            String color = String.format(
                    "#%06x",
                    ((ColorProperty) property).getValue().getRGB() & 0x00FFFFFF
            );
            propertySheetModel.addRow(new String[]{property.getName(), color});
            editorController.addEditor(cursor, new ColorEditor(property, this));
            renderers.put(cursor++, new ColorRenderer());

        } else if (property instanceof StringProperty) {

            propertySheetModel.addRow(new String[]{property.getName(), property.getValue().toString()});
            editorController.addEditor(cursor++, new StringEditor((StringProperty) property, this));

        } else {
            // TODO: Error
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
