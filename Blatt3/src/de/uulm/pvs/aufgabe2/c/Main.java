package de.uulm.pvs.aufgabe2.c;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        JFrame myWindow = new JFrame("FlowLayout Calculator");
        myWindow.setSize(250, 250);
        myWindow.getContentPane().setLayout(new FlowLayout());

        // Ergebnisanzeige als erstes Element, also links oben
        JLabel resultDisplay = new JLabel("Result here");
        myWindow.getContentPane().add(resultDisplay);

        String[][] labels = {{"+", "1", "2", "3"},
                {"-", "4", "5", "6"},
                {"x", "7", "8", "9"},
                {":", "0", "=", "C"}};
        for (String[] row : labels) {
            // Jede Zeile Buttons als eigenes JPanel mit FlowLayout
            JPanel buttonRow = new JPanel(new FlowLayout());
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
