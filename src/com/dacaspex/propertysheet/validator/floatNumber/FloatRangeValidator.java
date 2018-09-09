package com.dacaspex.propertysheet.validator.floatNumber;

import com.dacaspex.propertysheet.validator.Validator;

public class FloatRangeValidator implements Validator {

    protected float lowerBound;
    protected float upperBound;

    protected boolean includeLowerBound;
    protected boolean includeUpperBound;

    public FloatRangeValidator(
            float lowerBound,
            float upperBound,
            boolean includeLowerBound,
            boolean includeUpperBound
    ) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.includeLowerBound = includeLowerBound;
        this.includeUpperBound = includeUpperBound;
    }

    public FloatRangeValidator(float lowerBound, float upperBound) {
        this(lowerBound, upperBound, true, true);
    }

    public FloatRangeValidator() {
        this(-Float.MAX_VALUE, Float.MAX_VALUE, true, true);
    }

    public void setLowerBound(float lowerBound) {
        this.lowerBound = lowerBound;
    }

    public void setUpperBound(float upperBound) {
        this.upperBound = upperBound;
    }

    public void setIncludeLowerBound(boolean includeLowerBound) {
        this.includeLowerBound = includeLowerBound;
    }

    public void setIncludeUpperBound(boolean includeUpperBound) {
        this.includeUpperBound = includeUpperBound;
    }

    public void setIncludeBounds(boolean includeLowerBound, boolean includeUpperBound) {
        this.includeLowerBound = includeLowerBound;
        this.includeUpperBound = includeUpperBound;
    }

    @Override
    public boolean validate(Object object) {
        float value = Float.parseFloat((String) object);

        if ((value == lowerBound && includeLowerBound) || (value == upperBound && includeUpperBound)) {
            return true;
        }

        return value > lowerBound && value < upperBound;
    }
}
