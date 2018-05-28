package de.uulm.pvs.blatt6.aufgabe2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadStrings {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("arraylist.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
