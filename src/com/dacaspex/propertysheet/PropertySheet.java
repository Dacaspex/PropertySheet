package com.dacaspex.propertysheet;

import com.dacaspex.propertysheet.editor.ColorEditor;
import com.dacaspex.propertysheet.editor.EditorController;
import com.dacaspex.propertysheet.editor.FloatEditor;
import com.dacaspex.propertysheet.editor.IntegerEditor;
import com.dacaspex.propertysheet.editor.BooleanEditor;
import com.dacaspex.propertysheet.property.*;
import com.dacaspex.propertysheet.renderer.ColorRenderer;
import com.dacaspex.propertysheet.renderer.BooleanRenderer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PropertySheet extends JTable {

    private String[] headers;
    private Color invalidColor;
    private int rowHeight;
    private int cursor;

    private ArrayList<Property> properties;

    private EditorController editorController;
    private HashMap<Integer, TableCellRenderer> renderers;
    private PropertySheetModel propertySheetModel;

    public PropertySheet() {

        // Set cosmetics
        this.headers = new String[]{"Property", "value"};
        this.invalidColor = new Color(255, 114, 114);
        this.rowHeight = 30;

        // Initialise required variables
        this.cursor = 0;
        this.properties = new ArrayList<>();
        this.editorController = new EditorController();
        this.renderers = new HashMap<>();
        this.propertySheetModel = new PropertySheetModel(headers);

        // Set table properties
        setModel(propertySheetModel);
        getColumnModel().getColumn(1).setCellEditor(editorController);
        setRowHeight(rowHeight);
        getTableHeader().setReorderingAllowed(false);
    }

    public void addProperty(Property property) {
        properties.add(property);

        // TODO: The adding of renderes/editors should be redone to make sure elements can be deleted accordingly

        if (property instanceof IntegerProperty) {

            propertySheetModel.addRow(new String[]{property.getName(), property.getValue().toString()});
            editorController.addEditor(cursor++, new IntegerEditor((IntegerProperty) property, this));

        } else if (property instanceof FloatProperty) {

            propertySheetModel.addRow(new String[]{property.getName(), property.getValue().toString()});
            editorController.addEditor(cursor++, new FloatEditor((FloatProperty) property, this));

        } else if (property instanceof BooleanProperty) {

            propertySheetModel.addRow(new String[]{property.getName(), property.getValue().toString()});
            editorController.addEditor(cursor, new BooleanEditor((BooleanProperty) property, this));
            renderers.put(cursor++, new BooleanRenderer());

        } else if (property instanceof ColorProperty) {

            String color = String.format(
                    "#%06x",
                    ((ColorProperty) property).getValue().getRGB() & 0x00FFFFFF
            );
            propertySheetModel.addRow(new String[]{property.getName(), color});
            editorController.addEditor(cursor, new ColorEditor((ColorProperty) property, this));
            renderers.put(cursor++, new ColorRenderer());

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
}
