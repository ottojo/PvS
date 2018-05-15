package de.uulm.pvs.blatt4.aufgabe2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageViewWindow extends JFrame {

    private JLabel thumbnailPictureLabel = new JLabel();
    private JLabel fullPictureLabel = new JLabel();
    private JButton loadFromFileButton;
    private JButton loadFromURLButton;

    public ImageViewWindow() {
        setTitle("Bildanzeige");
        setSize(600, 400);
        getContentPane().setLayout(new BorderLayout());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Bottom Buttons
        JPanel bottomButtonRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        loadFromFileButton = new JButton("Bild aus Datei laden");
        //loadFromFileButton.addActionListener(;
        bottomButtonRow.add(loadFromFileButton);
        loadFromURLButton = new JButton("Bild aus URL laden");
        bottomButtonRow.add(loadFromURLButton);
        getContentPane().add(bottomButtonRow, BorderLayout.PAGE_END);

        // Thumbnail
        JPanel thumbnailPanel = new JPanel(new BorderLayout());
        thumbnailPanel.setBorder(BorderFactory.createTitledBorder("Thumbnail"));
        thumbnailPanel.add(thumbnailPictureLabel);
        thumbnailPictureLabel.setSize(new Dimension(150, 150));
        getContentPane().add(thumbnailPanel, BorderLayout.LINE_START);

        // Image
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBorder(BorderFactory.createTitledBorder("Ganzes Bild"));
        JScrollPane scrollPane = new JScrollPane(fullPictureLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        imagePanel.add(scrollPane);
        getContentPane().add(imagePanel, BorderLayout.CENTER);
    }

    void addLoadFromFileButtonActionListener(ActionListener l) {
        loadFromFileButton.addActionListener(l);
    }

    void addLoadFromUrlButtonActionListener(ActionListener l) {
        loadFromURLButton.addActionListener(l);
    }

    void setPicture(BufferedImage image) throws IOException {
        if (image == null) throw new IOException("Not an image");
        setThumbnail(image);
        setFullPicture(image);
    }

    private void setThumbnail(BufferedImage thumbnailImage) {
        thumbnailPictureLabel.setIcon(
                new ImageIcon(scaleKeepAspectRatio(thumbnailImage, 150, 150)));
    }

    private void setFullPicture(Image fullPicture) {
        fullPictureLabel.setIcon(new ImageIcon(fullPicture));
    }

    /**
     * Scales an Image uniformly to fit in a maximum size.
     *
     * @param image     Input Image
     * @param maxWidth  Maximum Width of Image
     * @param maxHeight Maximum Height of Image
     * @return Image that is of equal or smaller size than specified
     */
    private Image scaleKeepAspectRatio(BufferedImage image, int maxWidth, int maxHeight) {
        double widthScaleFactor = (double) maxWidth / (double) image.getWidth();
        double heightScaleFactor = (double) maxHeight / (double) image.getHeight();
        double scaleFactor = widthScaleFactor < heightScaleFactor ? widthScaleFactor : heightScaleFactor;
        return image.getScaledInstance(
                (int) (image.getWidth() * scaleFactor),
                (int) (image.getHeight() * scaleFactor),
                Image.SCALE_AREA_AVERAGING);
    }

}
