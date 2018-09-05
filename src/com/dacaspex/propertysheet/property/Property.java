package com.dacaspex.propertysheet.property;

import com.dacaspex.propertysheet.validator.Validator;

/**
 * Property interface
 *
 * @param <T> Type of the property
 */
public interface Property<T> {

    /**
     * @return The (display) name of the property
     */
    public String getName();

    /**
     * @return The actual value of the property
     */
    public T getValue();

    /**
     * Sets the value of the property
     *
     * @param value The incoming value of the desired type
     */
    public void setValue(T value);

    /**
     * @return A non-null validator for this property which should at least check whether
     * the type is correct
     */
    public Validator getValidator();
}
