package de.uulm.pvs.aufgabe2.a;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        JFrame myWindow = new JFrame("GridLayout Calculator");
        myWindow.setSize(250, 200);

        // Gridlayout 5x1: 5 Zeilen (Ergebnis und 4 Zeilen Buttons)
        myWindow.getContentPane().setLayout(new GridLayout(5, 1, 3, 3));

        // Ergebnisanzeige
        JLabel resultDisplay = new JLabel("Result here");
        myWindow.getContentPane().add(resultDisplay);

        // Hinzufügen der Buttons
        String[][] labels = {{"+", "1", "2", "3"},
                {"-", "4", "5", "6"},
                {"x", "7", "8", "9"},
                {":", "0", "=", "C"}};
        for (String[] row : labels) {
            // 1x4 Zeile von Buttons
            JPanel buttonRow = new JPanel(new GridLayout(1, 4, 3, 0));
            for (String label : row) {
                buttonRow.add(new JButton(label));
            }
            myWindow.getContentPane().add(buttonRow);
        }

        // WindowListener für korrektes Beenden des Programms
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
