package com.dacaspex.propertysheet.validator;

public class IntegerValidator implements Validator {

    protected int lowerBound;
    protected int upperBound;
    protected boolean allowNegative;

    public IntegerValidator() {
        this.upperBound = Integer.MAX_VALUE;
        this.lowerBound = Integer.MIN_VALUE;
        this.allowNegative = true;
    }

    public IntegerValidator(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.allowNegative = true;
    }

    public IntegerValidator(boolean allowNegative) {
        this.upperBound = Integer.MAX_VALUE;
        this.lowerBound = Integer.MIN_VALUE;
        this.allowNegative = allowNegative;
    }

    @Override
    public boolean validate(Object object) {

        if (!isInteger(object)) {
            return false;
        }

        int value = Integer.parseInt((String) object);

        return isInBounds(value)
                && adheresToNegativePolicy(value);
    }

    protected boolean isInteger(Object object) {
        try {
            Integer.parseInt((String) object);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    protected boolean isInBounds(int value) {
        return (value >= lowerBound && value <= upperBound);
    }

    protected boolean adheresToNegativePolicy(int value) {
        return (allowNegative || value >= 0);
    }
}
