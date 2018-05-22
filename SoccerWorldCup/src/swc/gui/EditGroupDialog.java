package swc.gui;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class EditGroupDialog extends JDialog {

    private List<OnSuccessfulEditListener> onSuccessfulEditListeners = new LinkedList<>();
    private List<OnCancelListener> cancelListeners = new LinkedList<>();

    public EditGroupDialog(Window owner, String currentGroupTitle) {
        super(owner, "Edit Group");
        setModal(true);
        getContentPane().setLayout(new GridLayout(2, 1));
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel("Titel:");
        JTextField inputField = new JTextField(currentGroupTitle);
        inputField.setColumns(20);
        inputPanel.add(label);
        inputPanel.add(inputField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton applyButton = new JButton("Apply changes");
        applyButton.addActionListener(e -> {
            if (EditGroupDialog.class.getResource("/data/icon/" + inputField.getText() + ".gif") == null) {
                JOptionPane.showMessageDialog(this,
                        "Ungültiger Name!",
                        "Fehler",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                onSuccessfulEditListeners.forEach(s -> s.applyChanges(inputField.getText()));
                dispose();
            }
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            cancelListeners.forEach(OnCancelListener::cancel);
            dispose();
        });
        buttonPanel.add(applyButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(inputPanel);
        getContentPane().add(buttonPanel);

        pack();
        setLocationRelativeTo(getOwner());
        setResizable(false);
    }

    public void addOnSuccessfulEditListener(OnSuccessfulEditListener listener) {
        onSuccessfulEditListeners.add(listener);
    }

    public void addOnCancelListener(OnCancelListener listener) {
        cancelListeners.add(listener);
    }

    @FunctionalInterface
    public interface OnSuccessfulEditListener {
        void applyChanges(String newTitle);
    }

    @FunctionalInterface
    public interface OnCancelListener {
        void cancel();
    }

}
