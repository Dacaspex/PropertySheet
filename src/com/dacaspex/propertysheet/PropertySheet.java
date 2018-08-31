package com.dacaspex.propertysheet;

import com.dacaspex.propertysheet.editor.EditorController;
import com.dacaspex.propertysheet.editor.IntegerEditor;
import com.dacaspex.propertysheet.property.IntegerProperty;
import com.dacaspex.propertysheet.property.Property;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PropertySheet extends JTable {

    private String[] headers;
    private Color invalidColor;
    private int rowHeight;
    private int cursor;

    private ArrayList<Property> properties;

    private EditorController editorController;
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
        this.propertySheetModel = new PropertySheetModel(headers);

        // Set table properties
        setModel(propertySheetModel);
        getColumnModel().getColumn(1).setCellEditor(editorController);
        setRowHeight(rowHeight);
    }

    public void addProperty(Property property) {
        properties.add(property);

        if (property instanceof IntegerProperty) {
            propertySheetModel.addRow(
                    new String[]{property.getName(), property.getValue().toString()}
            );
            editorController.addEditor(cursor++, new IntegerEditor((IntegerProperty) property, this));
        }
    }
}
