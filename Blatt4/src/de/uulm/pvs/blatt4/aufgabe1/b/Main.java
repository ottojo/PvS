package de.uulm.pvs.blatt4.aufgabe1.b;

import de.uulm.pvs.blatt4.aufgabe1.ColorWithName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        JFrame comboboxFrame = new JFrame("Radiobutton Stuff");
        comboboxFrame.setSize(200, 200);
        comboboxFrame.getContentPane().setLayout(new FlowLayout());
        comboboxFrame.getContentPane().setBackground(Color.RED);

        ButtonGroup buttonGroup = new ButtonGroup();

        // Create JRadioButton for each Color
        Arrays.stream(ColorWithName.RGB).forEach(c -> {
            JRadioButton button = new JRadioButton(c.name);
            // Set background to transparent
            button.setOpaque(false);
            // Add listener to change background color on selection
            button.addActionListener(e -> comboboxFrame.getContentPane().setBackground(c.color));
            buttonGroup.add(button);
            comboboxFrame.getContentPane().add(button);
        });

        comboboxFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        comboboxFrame.setVisible(true);
    }
}
