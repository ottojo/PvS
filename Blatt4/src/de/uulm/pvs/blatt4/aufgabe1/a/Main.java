package de.uulm.pvs.blatt4.aufgabe1.a;

import de.uulm.pvs.blatt4.aufgabe1.ColorWithName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
        JFrame comboboxFrame = new JFrame("Combobox Stuff");
        comboboxFrame.setSize(200, 200);
        comboboxFrame.setLayout(new FlowLayout());
        comboboxFrame.getContentPane().setBackground(Color.RED);

        JComboBox<ColorWithName> comboBox = new JComboBox<>(ColorWithName.RGB);

        // Listener for Combobox
        comboBox.addActionListener(e -> {
            // Set Window Color to selected color
            if (comboBox.getSelectedItem() != null)
                comboboxFrame.getContentPane().setBackground(((ColorWithName) comboBox.getSelectedItem()).color);
        });
        comboboxFrame.getContentPane().add(comboBox);

        // Exit program on window close
        comboboxFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        comboboxFrame.setVisible(true);
    }
}

