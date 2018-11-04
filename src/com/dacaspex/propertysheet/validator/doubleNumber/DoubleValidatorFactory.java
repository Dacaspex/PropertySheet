package com.dacaspex.propertysheet.validator.doubleNumber;

import com.dacaspex.propertysheet.validator.CompoundValidator;
import com.dacaspex.propertysheet.validator.Validator;

import java.util.ArrayList;

public class DoubleValidatorFactory {

    private DoubleValidator doubleValidator;
    private DoubleRangeValidator rangeValidator;
    private DoubleZeroPolicyValidator zeroPolicyValidator;

    public DoubleValidatorFactory() {
        this.doubleValidator = new DoubleValidator();
        this.rangeValidator = null;
        this.zeroPolicyValidator = null;
    }

    public DoubleValidatorFactory setRange(double lowerBound, double upperBound) {
        rangeValidator = new DoubleRangeValidator(lowerBound, upperBound);

        return this;
    }

    public DoubleValidatorFactory setRange(
            double lowerBound,
            double upperBound,
            boolean includeLowerBound,
            boolean includeUpperBound
    ) {
        rangeValidator = new DoubleRangeValidator(
                lowerBound,
                upperBound,
                includeLowerBound,
                includeUpperBound
        );

        return this;
    }

    public DoubleValidatorFactory includeBounds(boolean includeLowerBound, boolean includeUpperBound) {
        if (rangeValidator == null) {
            rangeValidator = new DoubleRangeValidator();
        }
        rangeValidator.setIncludeBounds(includeLowerBound, includeUpperBound);

        return this;
    }

    public DoubleValidatorFactory setLowerBound(double lowerBound) {
        if (rangeValidator == null) {
            rangeValidator = new DoubleRangeValidator();
        }
        rangeValidator.setLowerBound(lowerBound);

        return this;
    }

    public DoubleValidatorFactory setUpperBound(double upperBound) {
        if (rangeValidator == null) {
            rangeValidator = new DoubleRangeValidator();
        }
        rangeValidator.setUpperBound(upperBound);

        return this;
    }

    public DoubleValidatorFactory allowZero(boolean allowZero) {
        zeroPolicyValidator = new DoubleZeroPolicyValidator(allowZero);

        return this;
    }

    public Validator build() {
        ArrayList<Validator> validators = new ArrayList<>();
        validators.add(doubleValidator);

        if (rangeValidator != null) {
            validators.add(rangeValidator);
        }
        if (zeroPolicyValidator != null) {
            validators.add(zeroPolicyValidator);
        }

        Validator[] _validators = new Validator[validators.size()];
        validators.toArray(_validators);

        return new CompoundValidator(_validators);
    }
}
