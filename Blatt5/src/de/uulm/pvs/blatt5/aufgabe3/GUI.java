package de.uulm.pvs.blatt5.aufgabe3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    public GUI() {

        setSize(400, 400);
        getContentPane().setLayout(new GridLayout(1, 4));

        JTextArea welcome = new JTextArea("Hallo!");
        getContentPane().add(welcome);

        // Button mit MouseListener

        JButton button = new JButton("Button");
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                welcome.setText("button clicked");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                welcome.setText("button pressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                welcome.setText("button released");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                welcome.setText("mouse entered");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                welcome.setText("mouse exited");
            }
        });
        getContentPane().add(button);


        // Button für Mauszeiger Positionsanzeige

        JButton mousePositionButton = new JButton("Knopf");
        mousePositionButton.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mousePositionButton.setText(e.getX() + "x" + e.getY());
            }
        });
        mousePositionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                mousePositionButton.setText("Knopf");
            }
        });
        getContentPane().add(mousePositionButton);

        // JPanel
        JPanel panel = new JPanel();

        JLabel movingLabel = new JLabel("huiiii");

        panel.addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                movingLabel.setBounds(movingLabel.getX(), movingLabel.getY() + e.getUnitsToScroll(),
                        movingLabel.getWidth(), movingLabel.getHeight());
            }
        });
        panel.add(movingLabel);
        getContentPane().add(panel);

    }

    public void showGUI() {
        setVisible(true);
    }
}
