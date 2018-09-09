package com.dacaspex.propertysheet.validator.floatNumber;

import com.dacaspex.propertysheet.validator.CompoundValidator;
import com.dacaspex.propertysheet.validator.Validator;

import java.util.ArrayList;

public class FloatValidatorFactory {

    private FloatValidator floatValidator;
    private FloatRangeValidator rangeValidator;
    private FloatZeroPolicyValidator zeroPolicyValidator;

    public FloatValidatorFactory() {
        this.floatValidator = new FloatValidator();
        this.rangeValidator = null;
        this.zeroPolicyValidator = null;
    }

    public FloatValidatorFactory setRange(float lowerBound, float upperBound) {
        rangeValidator = new FloatRangeValidator(lowerBound, upperBound);

        return this;
    }

    public FloatValidatorFactory setRange(
            float lowerBound,
            float upperBound,
            boolean includeLowerBound,
            boolean includeUpperBound
    ) {
        rangeValidator = new FloatRangeValidator(
                lowerBound,
                upperBound,
                includeLowerBound,
                includeUpperBound
        );

        return this;
    }

    public FloatValidatorFactory includeBounds(boolean includeLowerBound, boolean includeUpperBound) {
        if (rangeValidator == null) {
            rangeValidator = new FloatRangeValidator();
        }
        rangeValidator.setIncludeBounds(includeLowerBound, includeUpperBound);

        return this;
    }

    public FloatValidatorFactory allowZero(boolean allowZero) {
        zeroPolicyValidator = new FloatZeroPolicyValidator(allowZero);

        return this;
    }

    public Validator build() {
        ArrayList<Validator> validators = new ArrayList<>();
        validators.add(floatValidator);

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
