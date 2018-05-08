package de.uulm.pvs.aufgabe1;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * WindowAdapter which exits the program when the window is closed.
 * Prints to console on window open and close
 */
public class WindowDestroyer extends WindowAdapter {

    public void windowClosing(WindowEvent e) {
        System.out.println("Closed");
        System.exit(0);
    }

    public void windowOpened(WindowEvent e) {
        System.out.println("Opened");
    }
}