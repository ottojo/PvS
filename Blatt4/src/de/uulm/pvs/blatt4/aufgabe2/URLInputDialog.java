package de.uulm.pvs.blatt4.aufgabe2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

class URLInputDialog extends JFrame {

    private JTextField urlTextField;
    private LinkedList<UrlSelected> listener = new LinkedList<>();

    URLInputDialog() {
        getContentPane().setLayout(new BorderLayout());
        setTitle("Enter URL");
        getContentPane().add(new Label("Please enter URL:"), BorderLayout.PAGE_START);

        urlTextField = new JTextField(30);
        getContentPane().add(urlTextField, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton okButton = new JButton("OK");
        buttons.add(okButton);
        okButton.addActionListener(e -> {
            listener.forEach(u -> u.urlSelected(urlTextField.getText()));
            close();
        });
        urlTextField.addActionListener(e->okButton.doClick());
        JButton cancelButton = new JButton("Cancel");
        buttons.add(cancelButton);
        cancelButton.addActionListener(e -> close());
        getContentPane().add(buttons, BorderLayout.PAGE_END);

        pack();
    }

    private void close() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    void addOnSelectedListener(UrlSelected u) {
        listener.add(u);
    }

    @FunctionalInterface
    public interface UrlSelected {
        void urlSelected(String url);
    }
}
