package tests;

import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.property.IntegerProperty;

import javax.swing.*;
import java.awt.*;

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

            IntegerProperty prop = new IntegerProperty("Iterations", 2);

            propertyTable = new PropertySheet();
            propertyTable.addProperty(prop, Integer.class);
            propertyTable.addProperty(prop, Integer.class);

        }

    }
}
