package com.dacaspex.propertysheet;

import java.awt.*;

/**
 * Cosmetic options for the property sheet
 */
public class PropertySheetOptions {

    private final String[] headers;
    private final Color backgroundColor;
    private final Color invalidColor;
    private final int rowHeight;

    public PropertySheetOptions(
            String[] headers,
            Color backgroundColor,
            Color invalidColor,
            int rowHeight
    ) {
        this.headers = headers;
        this.backgroundColor = backgroundColor;
        this.invalidColor = invalidColor;
        this.rowHeight = rowHeight;
    }

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

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getInvalidColor() {
        return invalidColor;
    }

    public int getRowHeight() {
        return rowHeight;
    }

    public static class Builder {

        private String[] headers;
        private Color backgroundColor;
        private Color invalidColor;
        private int rowHeight;

        public Builder() {
            this.headers = new String[]{"Property", "value"};
            this.backgroundColor = new Color(255, 255, 255);
            this.invalidColor = new Color(255, 179, 176);
            this.rowHeight = 30;
        }

        public Builder setHeaders(String header1, String header2) {
            this.headers = new String[]{header1, header2};

            return this;
        }

        public Builder setBackgroundColor(Color backgroundColor) {
            this.backgroundColor = backgroundColor;

            return this;
        }

        public Builder setInvalidColor(Color invalidColor) {
            this.invalidColor = invalidColor;

            return this;
        }

        public Builder setRowHeight(int rowHeight) {
            this.rowHeight = rowHeight;

            return this;
        }

        public PropertySheetOptions build() {
            return new PropertySheetOptions(headers, backgroundColor, invalidColor, rowHeight);
        }
    }
}
