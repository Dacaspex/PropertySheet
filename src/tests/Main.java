package tests;

import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.PropertySheetOptions;
import com.dacaspex.propertysheet.event.PropertySheetEventAdapter;
import com.dacaspex.propertysheet.property.*;
import com.dacaspex.propertysheet.property.selection.Item;
import com.dacaspex.propertysheet.property.selection.SelectionProperty;
import com.dacaspex.propertysheet.validator.CompoundValidator;
import com.dacaspex.propertysheet.validator.StringValidator;
import com.dacaspex.propertysheet.validator.doubleNumber.DoubleRangeValidator;
import com.dacaspex.propertysheet.validator.doubleNumber.DoubleValidator;
import com.dacaspex.propertysheet.validator.doubleNumber.DoubleZeroPolicyValidator;
import com.dacaspex.propertysheet.validator.integer.IntegerValidatorFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class used for testing. Do not use this class
 */
public class Main {

    public static void main(String[] args) {
        (new Main()).run();
    }

    public void run() {
        new Frame();
    }

    class Frame extends JFrame {

        private static final long serialVersionUID = -5491899016615981173L;

        private final int PREFERRED_WIDTH = 500;
        private final int PREFERRED_HEIGHT = 600;

        private PropertySheet propertyTable;
        private JScrollPane scrollPane;

        public Frame() {
            setVisible(true);
            buildGUI();
        }

        public void buildGUI() {

            setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            buildPropertyTable();

            scrollPane = new JScrollPane(propertyTable);
            getContentPane().add(scrollPane);

            pack();
        }

        public void buildPropertyTable() {

            IntegerProperty prop1 = new IntegerProperty("Iterations", 2);
            IntegerProperty prop11 = new IntegerProperty(
                    "Integer",
                    8,
                    new IntegerValidatorFactory()
                            .setLowerBound(5)
                            .setUpperBound(15)
                            .setParity(true)
                            .build()
            );
            ColorProperty prop2 = new ColorProperty("Color", Color.BLUE);
            BooleanProperty prop3 = new BooleanProperty("Boolean", true);
            FloatProperty prop4 = new FloatProperty("Float", 1.2f);
            StringProperty prop5 = new StringProperty("String", "Test string");
            StringProperty prop6 = new StringProperty("String 2", "test", new StringValidator(
                    new String[]{"test", "test 2", "foo"}
            ));
            SelectionProperty prop7 = new SelectionProperty<>(
                    "Selection",
                    new ArrayList<>(Arrays.asList(
                            new Item<>(new TestItem(), "Item 1"),
                            new Item<>(new TestItem(), "Item 2")
                    ))
            );
            DoubleProperty prop8 = new DoubleProperty("Double", 2.34,
                    new CompoundValidator(
                            new DoubleValidator(),
                            new DoubleRangeValidator(-1.2, 45.33, true, false),
                            new DoubleZeroPolicyValidator(false)
                    )
            );
            ActionProperty prop9 = new ActionProperty("Press me", () -> {
                System.out.println("Pressed");
            });

            propertyTable = new PropertySheet(new PropertySheetOptions.Builder().build());
            propertyTable.addEventListener(new EventListener());
            propertyTable.clear();

            propertyTable.addProperty(prop1);
            propertyTable.addProperty(prop11);
            propertyTable.addProperty(prop4);
            propertyTable.addProperty(prop5);
            propertyTable.addProperty(prop8);
            propertyTable.addProperty(prop2);
            propertyTable.addProperty(prop3);
            propertyTable.addProperty(prop7);
            propertyTable.addProperty(prop9);

            repaint();
        }
    }

    class EventListener extends PropertySheetEventAdapter {
        @Override
        public void onPropertyUpdated(Property property) {
            System.out.println(property.getName() + " : " + property.getValue());
        }
    }

    private class TestItem {
    }
}
