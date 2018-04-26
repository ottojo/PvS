package de.uulm.blatt2.aufgabe1;

import java.util.Objects;

public class MyDate implements Comparable<MyDate> {
    private int day;
    private int month;
    private int year;

    public MyDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyDate myDate = (MyDate) o;
        return day == myDate.day &&
                month == myDate.month &&
                year == myDate.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public int compareTo(MyDate o) {
        if (o.equals(this)) return 0;
        if (year < o.year) return -1;
        if (year > o.year) return 1;
        if (month < o.month) return -1;
        if (month > o.month) return 1;
        return day < o.day ? -1 : 1;
    }
}
