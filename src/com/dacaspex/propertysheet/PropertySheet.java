package com.dacaspex.propertysheet;

import com.dacaspex.propertysheet.cell.AbstractCellComponent;
import com.dacaspex.propertysheet.cell.IntegerCellComponent;
import com.dacaspex.propertysheet.editor.*;
import com.dacaspex.propertysheet.event.EventDispatcher;
import com.dacaspex.propertysheet.event.PropertySheetEventListener;
import com.dacaspex.propertysheet.exception.PropertyNotSupportedException;
import com.dacaspex.propertysheet.property.*;
import com.dacaspex.propertysheet.property.selection.SelectionProperty;
import com.dacaspex.propertysheet.renderer.BooleanRenderer;
import com.dacaspex.propertysheet.renderer.ColorRenderer;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PropertySheet extends JTable {

    private int cursor;
    private PropertySheetOptions options;
    private EditorController editorController;
    private HashMap<Integer, TableCellRenderer> renderers;
    private PropertySheetModel propertySheetModel;
    private EventDispatcher eventDispatcher;

    private List<AbstractCellComponent> cellComponents;

    /**
     * @param options Options object for the property sheet
     */
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
//        getColumnModel().getColumn(1).setCellEditor(editorController);
        setRowHeight(options.getRowHeight());
        getTableHeader().setReorderingAllowed(false);

        cellComponents = new ArrayList<>();
        AbstractCellComponent.init(options, eventDispatcher);
    }

    public PropertySheetOptions getOptions() {
        return options;
    }

    public void addProperty(Property property, AbstractCellComponent cellComponent) {
        propertySheetModel.addRow(new Object[] {property.getName(), property.getValue()});
        cellComponents.add(cellComponent);
        eventDispatcher.dispatchPropertyAddedEvent(property);
    }

    /**
     * Add a property to the property sheet
     *
     * @param property Property to add
     * @param display  Display string
     * @param editor   Editor for the property
     * @param renderer Renderer for the property. Leave null if not necessary
     */
    public void addProperty(
            Property property,
            String display,
            TableCellEditor editor,
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

    /**
     * @param property Property to add
     * @param editor   Editor for the property
     * @param renderer Renderer for the property
     */
    public void addProperty(Property property, PropertySheetCellEditor editor, TableCellRenderer renderer) {
        addProperty(property, property.getValue().toString(), editor, renderer);
    }

    /**
     * @param property Property to add
     * @param display  Display string
     * @param editor   Editor for the property
     */
    public void addProperty(Property property, String display, PropertySheetCellEditor editor) {
        addProperty(property, display, editor, null);
    }

    /**
     * @param property Property to add
     * @param editor   Editor for the property
     */
    public void addProperty(Property property, TableCellEditor editor) {
        addProperty(property, property.getValue().toString(), editor, null);
    }

    /**
     * Adds a default known property to the property sheet
     *
     * @param property Property to add. Must be an instance of the properties in this library
     * @throws PropertyNotSupportedException If property is not known
     */
    public void addProperty(Property property) {
        if (property instanceof IntegerProperty) {
            addProperty(property, new IntegerCellComponent((IntegerProperty) property));
        } else if (property instanceof FloatProperty) {
            addProperty(property, new FloatEditor(property, this));
        } else if (property instanceof DoubleProperty) {
            addProperty(property, new DoubleEditor(property, this));
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

    public void clear() {
        propertySheetModel.clear();
        editorController.clear();
        renderers.clear();
        cursor = 0;
    }

    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        if (column == 1) {
            return cellComponents.get(row);
        }

        return super.getCellEditor(row, column);
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {

        if (column == 1) {
            return cellComponents.get(row);
        }

        return super.getCellRenderer(row, column);

//        if (column == 1) {
//            if (row == 0) {
//                System.out.println("test");
//                return new IntegerCellComponent(new IntegerProperty("Test", 1234));
//            }
//
//            return renderers.getOrDefault(row, super.getCellRenderer(row, column));
//        }
//
//        return super.getCellRenderer(row, column);
    }

    public void addEventListener(PropertySheetEventListener eventListener) {
        eventDispatcher.addEventListener(eventListener);
    }

    public void removeEventListener(PropertySheetEventListener eventListener) {
        eventDispatcher.removeEventListener(eventListener);
    }
}
