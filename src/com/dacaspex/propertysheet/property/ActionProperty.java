package com.dacaspex.propertysheet.property;

import com.dacaspex.propertysheet.action.Action;
import com.dacaspex.propertysheet.validator.NullValidator;

public class ActionProperty extends AbstractProperty<Action> {

    private String actionName;

    public ActionProperty(String actionName, Action action) {
        super("", action, new NullValidator());

        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }
}
