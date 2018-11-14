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

    public void setHeaders(String header1, String header2) {
        headers = new String[]{header1, header2};
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getInvalidColor() {
        return invalidColor;
    }

    public void setInvalidColor(Color invalidColor) {
        this.invalidColor = invalidColor;
    }

    public int getRowHeight() {
        return rowHeight;
    }

    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }

    public class Builder {
        private PropertySheetOptions options;

        public Builder() {
            this.options = new PropertySheetOptions();
        }

        public Builder setHeaders(String header1, String header2) {
            options.setHeaders(header1, header2);

            return this;
        }

        public Builder setBackgroundColor(Color color) {
            options.setBackgroundColor(color);

            return this;
        }

        public Builder setInvalidColor(Color color) {
            options.setInvalidColor(color);

            return this;
        }

        public Builder setRowHeight(int rowHeight) {
            options.setRowHeight(rowHeight);

            return this;
        }

        public PropertySheetOptions build() {
            return options;
        }
    }
}
