package com.dacaspex.propertysheet.validator.longNumber;

import com.dacaspex.propertysheet.validator.Validator;

public class LongRangeValidator implements Validator {

    protected long lowerBound;
    protected long upperBound;

    public LongRangeValidator() {
        this(Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public LongRangeValidator(long lowerBound, long upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public void setLowerBound(long lowerBound) {
        this.lowerBound = lowerBound;
    }

    public void setUpperBound(long upperBound) {
        this.upperBound = upperBound;
    }

    @Override
    public boolean validate(Object object) {
        long value = Long.parseLong((String) object);

        return value >= lowerBound && value <= upperBound;
    }

}
