package com.dacaspex.propertysheet.validator.doubleNumber;

import com.dacaspex.propertysheet.validator.Validator;

public class DoubleRangeValidator implements Validator {

    protected double lowerBound;
    protected double upperBound;

    protected boolean includeLowerBound;
    protected boolean includeUpperBound;

    public DoubleRangeValidator(
            double lowerBound,
            double upperBound,
            boolean includeLowerBound,
            boolean includeUpperBound
    ) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.includeLowerBound = includeLowerBound;
        this.includeUpperBound = includeUpperBound;
    }

    public DoubleRangeValidator(double lowerBound, double upperBound) {
        this(lowerBound, upperBound, true, true);
    }

    public DoubleRangeValidator() {
        this(-Double.MAX_VALUE, Double.MAX_VALUE, true, true);
    }

    public void setLowerBound(double lowerBound) {
        this.lowerBound = lowerBound;
    }

    public void setUpperBound(double upperBound) {
        this.upperBound = upperBound;
    }

    public void setIncludeLowerBound(boolean includeLowerBound) {
        this.includeLowerBound = includeLowerBound;
    }

    public void setIncludeUpperBound(boolean includeUpperBound) {
        this.includeUpperBound = includeUpperBound;
    }

    @Override
    public boolean validate(Object object) {
        double value = Double.parseDouble((String) object);

        if ((value == lowerBound && includeLowerBound) || (value == upperBound && includeUpperBound)) {
            return true;
        }

        return value > lowerBound && value < upperBound;
    }
}
