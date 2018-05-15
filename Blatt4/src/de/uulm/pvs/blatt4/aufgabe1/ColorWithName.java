package de.uulm.pvs.blatt4.aufgabe1;

import java.awt.*;

/**
 * Used for easy correlation of a color and it's name and correct display of it's name in a JComboBox
 */
public class ColorWithName {
    public Color color;
    public String name;

    /**
     * Array that contains RED, GREEN, BLUE with corresponding name
     */
    public static ColorWithName[] RGB = new ColorWithName[]{
            new ColorWithName(Color.RED, "Red"),
            new ColorWithName(Color.BLUE, "Blue"),
            new ColorWithName(Color.GREEN, "Green"),

    };

    ColorWithName(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}