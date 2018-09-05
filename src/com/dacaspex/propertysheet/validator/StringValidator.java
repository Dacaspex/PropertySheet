package com.dacaspex.propertysheet.validator;

/**
 * Default string validator implementation
 */
public class StringValidator implements Validator {

    protected String[] options;

    public StringValidator() {
        this.options = new String[]{};
    }

    public StringValidator(String[] options) {
        this.options = options;
    }

    @Override
    public boolean validate(Object object) {

        String value = (String) object;

        if (options.length > 0) {
            for (String option : options) {
                if (option.equals(value)) {
                    return true;
                }
            }

            return false;
        }

        return true;
    }
}
