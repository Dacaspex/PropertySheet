package com.dacaspex.propertysheet.util;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Keys on which a text field should not validate the input.
 */
public final class Keys {

    private static ArrayList<Integer> invalidKeys = new ArrayList<>(Arrays.asList(
            // Move keys
            KeyEvent.VK_LEFT,
            KeyEvent.VK_RIGHT,
            KeyEvent.VK_UP,
            KeyEvent.VK_DOWN,

            // Other keys
            KeyEvent.VK_SHIFT,
            KeyEvent.VK_ALT,
            KeyEvent.VK_CONTROL
    ));

    public static boolean ignoreKey(int key) {
        return invalidKeys.contains(key);
    }
}
