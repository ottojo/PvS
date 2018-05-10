package de.uulm.pvs.blatt4.aufgabe2;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ImageViewWindow imageViewUI = new ImageViewWindow();

        imageViewUI.setPicture(ImageIO.read(new File("2000x1000.png")));

        imageViewUI.setVisible(true);
    }
}
