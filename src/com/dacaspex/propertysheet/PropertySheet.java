package com.dacaspex.propertysheet;

import com.dacaspex.propertysheet.cell.*;
import com.dacaspex.propertysheet.event.EventDispatcher;
import com.dacaspex.propertysheet.event.PropertySheetEventListener;
import com.dacaspex.propertysheet.property.*;
import com.dacaspex.propertysheet.property.selection.SelectionProperty;

import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.List;

public class PropertySheet extends JTable {

    private PropertySheetOptions options;
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
        this.propertySheetModel = new PropertySheetModel(options.getHeaders());
        this.eventDispatcher = new EventDispatcher();

        // Set table properties
        setModel(propertySheetModel);
        setRowHeight(options.getRowHeight());
        getTableHeader().setReorderingAllowed(false);

        cellComponents = new ArrayList<>();
        AbstractCellComponent.init(options, eventDispatcher);
    }

    public PropertySheetOptions getOptions() {
        return options;
    }

    public void addProperty(Property property, AbstractCellComponent cellComponent) {
        propertySheetModel.addRow(new Object[]{property.getName(), property.getValue()});
        cellComponents.add(cellComponent);
        eventDispatcher.dispatchPropertyAddedEvent(property);
    }

    public void addProperty(Property property) {
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
        }
    }

    public void clear() {
        propertySheetModel.clear();
        cellComponents.clear();
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

    public void addEventListener(PropertySheetEventListener eventListener) {
        eventDispatcher.addEventListener(eventListener);
    }

    public void removeEventListener(PropertySheetEventListener eventListener) {
        eventDispatcher.removeEventListener(eventListener);
    }
}
