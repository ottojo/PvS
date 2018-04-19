package com.jonasotto.pvs.aufgabe2_4;

public class Pentagon implements GeomCalculation {

    private int length;
    private static final double AREA_FACTOR = 0.25 * Math.sqrt(25 + 10 * Math.sqrt(5));

    public Pentagon(int length) {
        this.length = length;
    }

    @Override
    public double getPerimeter() {
        return 5 * length;
    }

    @Override
    public double getArea() {
        return this.length * this.length * AREA_FACTOR;
    }
}
