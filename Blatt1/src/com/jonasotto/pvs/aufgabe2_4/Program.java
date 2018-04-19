package com.jonasotto.pvs.aufgabe2_4;

import java.util.LinkedList;
import java.util.Locale;

public class Program {
    public static void main(String[] args) {

        // Implementation using Array
        GeomCalculation geomObjects[] = {
                new Pentagon(42),
                new Pentagon(1337),
                new Circle(420)
        };

        double totalArea = 0;
        double totalPerimeter = 0;
        for (int i = 0; i < geomObjects.length; i++) {
            totalArea += geomObjects[i].getArea();
            totalPerimeter += geomObjects[i].getPerimeter();
        }

        System.out.printf(Locale.ROOT, "Using Array: Total Area: %.3f, Total Perimeter: %.3f%n", totalArea, totalPerimeter);


        // Implementation using LinkedList
        LinkedList<GeomCalculation> geomObjectsList = new LinkedList<>();
        geomObjectsList.add(new Pentagon(42));
        geomObjectsList.add(new Pentagon(1337));
        geomObjectsList.add(new Circle(420));

        totalArea = 0;
        totalPerimeter = 0;
        for (GeomCalculation geomObject : geomObjectsList) {
            totalArea += geomObject.getArea();
            totalPerimeter += geomObject.getPerimeter();
        }

        System.out.printf(Locale.ROOT, "Using LinkedList: Total Area: %.3f, Total Perimeter: %.3f%n", totalArea, totalPerimeter);
    }
}
