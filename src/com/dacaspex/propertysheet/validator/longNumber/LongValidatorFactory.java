package com.dacaspex.propertysheet.validator.longNumber;

import com.dacaspex.propertysheet.validator.CompoundValidator;
import com.dacaspex.propertysheet.validator.Validator;

import java.util.ArrayList;

public class LongValidatorFactory {

    private LongValidator validator;
    private LongRangeValidator rangeValidator;
    private LongParityValidator parityValidator;
    private LongZeroPolicyValidator zeroPolicyValidator;

    public LongValidatorFactory() {
        this.validator = new LongValidator();
        this.rangeValidator = null;
        this.parityValidator = null;
        this.zeroPolicyValidator = null;
    }

    public LongValidatorFactory setRange(int lowerBound, int upperBound) {
        rangeValidator = new LongRangeValidator(lowerBound, upperBound);

        return this;
    }

    public LongValidatorFactory setLowerBound(int lowerBound) {
        if (rangeValidator == null) {
            rangeValidator = new LongRangeValidator();
        }
        rangeValidator.setLowerBound(lowerBound);

        return this;
    }

    public LongValidatorFactory setUpperBound(int upperBound) {
        if (rangeValidator == null) {
            rangeValidator = new LongRangeValidator();
        }
        rangeValidator.setUpperBound(upperBound);

        return this;
    }

    public LongValidatorFactory setParity(boolean even) {
        parityValidator = new LongParityValidator(even);

        return this;
    }

    public LongValidatorFactory allowZero(boolean allowZero) {
        zeroPolicyValidator = new LongZeroPolicyValidator(allowZero);

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
