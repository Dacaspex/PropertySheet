package tests;

import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.event.PropertySheetEventAdapter;
import com.dacaspex.propertysheet.property.*;
import com.dacaspex.propertysheet.property.selection.Item;
import com.dacaspex.propertysheet.property.selection.SelectionProperty;
import com.dacaspex.propertysheet.validator.StringValidator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public void run() {
        new Frame();
    }

    public static void main(String[] args) {
        (new Main()).run();
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

        public void update() {

            buildPropertyTable();

            getContentPane().removeAll();
            scrollPane = new JScrollPane(propertyTable);
            getContentPane().add(scrollPane);

            revalidate();
            repaint();

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
            ColorProperty prop2 = new ColorProperty("Color", Color.BLUE);
            BooleanProperty prop3 = new BooleanProperty("Boolean", true);
            FloatProperty prop4 = new FloatProperty("Float", 1.2f);
            StringProperty prop5 = new StringProperty("String", "Test string");
            StringProperty prop6 = new StringProperty("String 2", "test", new StringValidator(
                    new String[]{"test", "test 2", "foo"}
            ));
            SelectionProperty prop7 = new SelectionProperty(
                    "Selection",
                    new ArrayList<>(Arrays.asList(
                            new Item<>(new TestItem(), "Item 1"),
                            new Item<>(new TestItem(), "Item 2")
                    ))
            );

            propertyTable = new PropertySheet();
            propertyTable.addProperty(prop1);
            propertyTable.addProperty(prop2);
            propertyTable.addProperty(prop3);
            propertyTable.addProperty(prop4);
            propertyTable.addProperty(prop5);
            propertyTable.addProperty(prop6);
            propertyTable.addProperty(prop7);

            propertyTable.addEventListener(new EventListener());
        }
    }

    class EventListener extends PropertySheetEventAdapter {
        @Override
        public void onPropertyUpdated(Property property) {
            // TODO
        }
    }

    class TestItem {
    }
}
