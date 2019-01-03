package com.dacaspex.propertysheet;

import com.dacaspex.propertysheet.cell.*;
import com.dacaspex.propertysheet.event.EventDispatcher;
import com.dacaspex.propertysheet.event.PropertySheetEventListener;
import com.dacaspex.propertysheet.exception.PropertyNotSupportedException;
import com.dacaspex.propertysheet.property.*;
import com.dacaspex.propertysheet.property.selection.SelectionProperty;

import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.List;

/**
 * Property sheet.
 * <p>
 * Simple Java Swing GUI component to display and edit properties.
 */
public class PropertySheet extends JTable {

    private PropertySheetOptions options;
    private PropertySheetModel propertySheetModel;
    private EventDispatcher eventDispatcher;
    private List<AbstractCellComponent> cellComponents;
    private List<Property> properties;

    /**
     * @param options Options object for the property sheet
     */
    public PropertySheet(PropertySheetOptions options) {
        this.options = options;
        this.propertySheetModel = new PropertySheetModel(options.getHeaders());
        this.eventDispatcher = new EventDispatcher();
        this.cellComponents = new ArrayList<>();
        this.properties = new ArrayList<>();

        // Set necessary properties
        setModel(propertySheetModel);
        setRowHeight(options.getRowHeight());
        getTableHeader().setReorderingAllowed(false);
    }

    /**
     * Add a new property to the table with a custom cell component.
     *
     * @param property      Property
     * @param cellComponent Custom cell component
     */
    public void addProperty(Property property, AbstractCellComponent cellComponent) {
        propertySheetModel.addRow(new Object[]{property.getName(), property.getValue()});
        cellComponents.add(cellComponent);
        properties.add(property);
        cellComponent.init(options, eventDispatcher);
        eventDispatcher.dispatchPropertyAddedEvent(property);
    }

    /**
     * Add a standard property. If the property is not standard, i.e. there does not exist a standard
     * cell component, a {@code PropertyNotSupportedException} is thrown.
     *
     * @param property Standard property
     * @throws PropertyNotSupportedException If {@code property} is not a standard property
     */
    public void addProperty(Property property) throws PropertyNotSupportedException {
        if (property instanceof IntegerProperty) {
            addProperty(property, new IntegerCellComponent((IntegerProperty) property));
        } else if (property instanceof LongProperty) {
            addProperty(property, new LongCellComponent((LongProperty) property));
        } else if (property instanceof DoubleProperty) {
            addProperty(property, new DoubleCellComponent((DoubleProperty) property));
        } else if (property instanceof FloatProperty) {
            addProperty(property, new FloatCellComponent((FloatProperty) property));
        } else if (property instanceof StringProperty) {
            addProperty(property, new StringCellComponent((StringProperty) property));
        } else if (property instanceof ColorProperty) {
            addProperty(property, new ColorCellComponent((ColorProperty) property));
        } else if (property instanceof BooleanProperty) {
            addProperty(property, new BooleanCellComponent((BooleanProperty) property));
        } else if (property instanceof SelectionProperty) {
            addProperty(property, new SelectionCellComponent((SelectionProperty) property));
        } else if (property instanceof ActionProperty) {
            addProperty(property, new ActionCellComponent((ActionProperty) property));
        } else {
            throw new PropertyNotSupportedException(property);
        }
    }

    /**
     * Removes the property from the sheet.
     *
     * @param property Property
     */
    public void removeProperty(Property property) {
        removeProperty(properties.indexOf(property));
    }

    /**
     * Removes a row from the table.
     *
     * @param row Row index
     */
    public void removeProperty(int row) {
        cellComponents.remove(row);
        properties.remove(row);
        propertySheetModel.removeRow(row);
    }

    /**
     * Removes all components from the table
     */
    public void clear() {
        propertySheetModel.clear();
        cellComponents.clear();
        properties.clear();
    }

    /**
     * Add a new event listener.
     *
     * @param eventListener Event listener
     */
    public void addEventListener(PropertySheetEventListener eventListener) {
        eventDispatcher.addEventListener(eventListener);
    }

    /**
     * Remove an event listener
     *
     * @param eventListener Event listener
     */
    public void removeEventListener(PropertySheetEventListener eventListener) {
        eventDispatcher.removeEventListener(eventListener);
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
    }
}
