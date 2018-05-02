package de.uulm.blatt2.aufgabe2;

import de.uulm.blatt2.aufgabe1.MyDate;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        /*
        Liste: Kann sortiert werden, da es sich um eine geordnete Liste handelt
        Collection: Kann auch z.B ein ungeordnetes "Set" sein
         */

        List<MyDate> list = new LinkedList<>();
        list.add(new MyDate(1, 2, 1000));
        list.add(new MyDate(4, 8, 99));
        list.add(new MyDate(1, 2, 2018));
        list.add(new MyDate(9, 9, 6000));
        list.add(new MyDate(2, 2, 2018));
        list.add(new MyDate(1, 4, 2018));
        list.add(new MyDate(20, 4, 1337));
        list.add(new MyDate(20, 4, 1337)); // Duplicate, should be detected later
        list.add(new MyDate(14, 3, 2015));
        Collections.sort(list);

        System.out.println(list);

        HashSet<MyDate> hashSet = new HashSet<>();
        for (MyDate date : list) {
            if (!hashSet.add(date))
                System.out.printf("Date %s is already in the hashmap.\n", date);
        }
    }
}
