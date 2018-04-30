package de.uulm.blatt2.aufgabe3;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate d = LocalDate.now();
        System.out.printf("Now: %s: %s%n", d, d.getDayOfWeek());
        d = LocalDate.now().minusWeeks(4);
        System.out.printf("4 Weeks ago: %s: %s%n", d, d.getDayOfWeek());
        d = LocalDate.now().minusMonths(1);
        System.out.printf("1 Month ago: %s: %s%n", d, d.getDayOfWeek());
    }
}
