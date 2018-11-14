package com.dacaspex.propertysheet;

import java.awt.*;

/**
 * Cosmetic options for the property sheet
 */
public class PropertySheetOptions {

    private String[] headers;
    private Color backgroundColor;
    private Color invalidColor;
    private int rowHeight;

    /**
     * Constructs a default option set
     */
    public PropertySheetOptions() {
        this.headers = new String[]{"Property", "value"};
        this.backgroundColor = new Color(255, 255, 255);
        this.invalidColor = new Color(255, 179, 176);
        this.rowHeight = 30;
    }

    public String[] getHeaders() {
        return headers;
    }

    public PropertySheetOptions setHeaders(String header1, String header2) {
        headers = new String[]{header1, header2};
        return this;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public PropertySheetOptions setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public Color getInvalidColor() {
        return invalidColor;
    }

    public PropertySheetOptions setInvalidColor(Color invalidColor) {
        this.invalidColor = invalidColor;
        return this;
    }

    public int getRowHeight() {
        return rowHeight;
    }

    public PropertySheetOptions setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
        return this;
    }

    public class Builder {

    }
}
