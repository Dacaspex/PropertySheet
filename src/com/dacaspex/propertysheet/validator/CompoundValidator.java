package com.dacaspex.propertysheet.validator;

import java.util.Arrays;

public class CompoundValidator implements Validator {

    protected Validator[] validators;

    public CompoundValidator(Validator... validators) {
        this.validators = validators;
    }

    @Override
    public boolean validate(Object object) {
        return Arrays.stream(validators).allMatch(v -> v.validate(object));
    }
}
