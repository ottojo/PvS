package swc.gui;

import swc.data.SoccerWC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Mainframe extends JFrame {
    private SoccerWC worldCup;

    public Mainframe(SoccerWC worldCup) throws HeadlessException {
        super("Soccer World Cup");
        this.worldCup = worldCup;

        setSize(500, 500);

        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");

        JMenuItem loadMenuItem = new JMenuItem("Load World Cup");
        fileMenu.add(loadMenuItem);

        JMenuItem newMenuItem = new JMenuItem("New World Cup");
        newMenuItem.addActionListener(e -> new CreateDialog(this, worldCup).setVisible(true));
        fileMenu.add(newMenuItem);

        JMenuItem saveMenuItem = new JMenuItem("Save");
        fileMenu.add(saveMenuItem);

        JMenuItem saveAsMenuItem = new JMenuItem("Save As...");
        fileMenu.add(saveAsMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        // Extra Menu
        JMenu extraMenu = new JMenu("Extra");

        JMenuItem bettingMenuItem = new JMenuItem("World Cup betting");
        extraMenu.add(bettingMenuItem);

        JMenuItem loadFromServerMenuItem = new JMenuItem("Load from server...");
        extraMenu.add(loadFromServerMenuItem);

        menuBar.add(extraMenu);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");

        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Jonas Otto, Davide Domingues",
                "About",
                JOptionPane.INFORMATION_MESSAGE));
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
        setVisible(true);
    }

    public static void main(String[] args) {
        Mainframe m = new Mainframe(new SoccerWC());
        m.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
