package com.dacaspex.propertysheet.validator;

/**
 * Validator interface
 * <p>
 * A validators job is to check whether a given object can be converted
 * to the desired type. Moreover, a validator can check for additional requirements.
 */
public interface Validator {

    /**
     * Checks whether the object meets the criteria
     *
     * @param object Raw object coming from editor
     * @return True if the object meets the criteria and can be converted, false otherwise
     */
    public boolean validate(Object object);

}
