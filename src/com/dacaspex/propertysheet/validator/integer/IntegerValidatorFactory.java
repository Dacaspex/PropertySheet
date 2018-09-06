package com.dacaspex.propertysheet.validator.integer;

import com.dacaspex.propertysheet.validator.CompoundValidator;
import com.dacaspex.propertysheet.validator.Validator;

import java.util.ArrayList;

public class IntegerValidatorFactory {

    private IntegerValidator validator;
    private IntegerRangeValidator rangeValidator;
    private IntegerParityValidator parityValidator;
    private IntegerZeroPolicyValidator zeroPolicyValidator;

    public IntegerValidatorFactory() {
        this.validator = new IntegerValidator();
        this.rangeValidator = null;
        this.parityValidator = null;
        this.zeroPolicyValidator = null;
    }

    public IntegerValidatorFactory setRange(int lowerBound, int upperBound) {
        rangeValidator = new IntegerRangeValidator(lowerBound, upperBound);

        return this;
    }

    public IntegerValidatorFactory setLowerBound(int lowerBound) {
        if (rangeValidator == null) {
            rangeValidator = new IntegerRangeValidator();
        }
        rangeValidator.setLowerBound(lowerBound);

        return this;
    }

    public IntegerValidatorFactory setUpperBound(int upperBound) {
        if (rangeValidator == null) {
            rangeValidator = new IntegerRangeValidator();
        }
        rangeValidator.setUpperBound(upperBound);

        return this;
    }

    public IntegerValidatorFactory setParity(boolean even) {
        parityValidator = new IntegerParityValidator(even);

        return this;
    }

    public IntegerValidatorFactory allowZero(boolean allowZero) {
        zeroPolicyValidator = new IntegerZeroPolicyValidator(allowZero);

        return this;
    }

    public CompoundValidator build() {
        ArrayList<Validator> validators = new ArrayList<>();
        validators.add(validator);

        if (rangeValidator != null) {
            validators.add(rangeValidator);
        }
        if (parityValidator != null) {
            validators.add(parityValidator);
        }
        if (zeroPolicyValidator != null) {
            validators.add(zeroPolicyValidator);
        }

        Validator[] _validators = new Validator[validators.size()];
        validators.toArray(_validators);

        return new CompoundValidator(_validators);
    }
}
