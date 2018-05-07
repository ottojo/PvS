package de.uulm.pvs.aufgabe2.b;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        JFrame myWindow = new JFrame();
        myWindow.setSize(250, 200);
        myWindow.getContentPane().setLayout(new BorderLayout(3, 3));

        JLabel resultDisplay = new JLabel("Result here");
        myWindow.getContentPane().add(resultDisplay, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 3, 3));

        String[][] labels = {{"+", "1", "2", "3"},
                {"-", "4", "5", "6"},
                {"x", "7", "8", "9"},
                {":", "0", "=", "C"}};
        for (String[] row : labels) {
            for (String label : row) {
                buttonPanel.add(new JButton(label));
            }
        }

        myWindow.add(buttonPanel, BorderLayout.CENTER);

        myWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("Fenster geöffnet.");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Fenster wird geschlossen.");
                System.exit(0);
            }
        });

        myWindow.setVisible(true);
    }
}
