package de.uulm.pvs.blatt4.aufgabe2;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageController {
    public ImageController() {
        ImageViewWindow imageViewWindow = new ImageViewWindow();

        // Load Image from File
        imageViewWindow.addLoadFromFileButtonActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            // Allows the user to display only some common image file types
            fileChooser.addChoosableFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.getName().toLowerCase().endsWith(".jpg")
                            || f.getName().endsWith(".png")
                            || f.getName().endsWith(".bmp");
                }

                @Override
                public String getDescription() {
                    return "JPG, PNG, BMP Bilder.";
                }
            });

            // Action Listener to load the image once selected
            fileChooser.addActionListener(fileChooserAction -> {
                try {
                    BufferedImage newPicture = ImageIO.read(fileChooser.getSelectedFile());
                    imageViewWindow.setPicture(newPicture);
                } catch (IOException exception) {
                    // Display error message
                    JOptionPane.showMessageDialog(
                            null,
                            "Fehler beim Lesen der Datei: " + exception.toString(),
                            "Fehler",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
            fileChooser.showOpenDialog(null);
        });

        // Load image from URL
        imageViewWindow.addLoadFromUrlButtonActionListener(e -> {
            // Display Dialog for URL entry
            String url = JOptionPane.showInputDialog("Please enter URL");
            try {
                BufferedImage newPicture = ImageIO.read(new URL(url));
                imageViewWindow.setPicture(newPicture);
            } catch (Exception exception) {
                // Display error message
                JOptionPane.showMessageDialog(
                        null,
                        "Fehler beim Laden aus URL: " + exception.toString(),
                        "Fehler",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Show Window
        imageViewWindow.setVisible(true);
    }
}
