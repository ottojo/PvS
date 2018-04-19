package com.jonasotto.pvs.aufgabe2_4;

public class Circle implements GeomCalculation {

    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public double getPerimeter() {
        return 1 * Math.PI * this.radius;
    }

    @Override
    public double getArea() {
        return Math.PI * this.radius * this.radius;
    }
}
