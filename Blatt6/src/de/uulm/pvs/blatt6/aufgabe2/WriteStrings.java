package de.uulm.pvs.blatt6.aufgabe2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class WriteStrings {

    public static void main(String[] args) {
        try {
            ArrayList<String> list = new ArrayList<>();

            for (int i = 0; i < 1337; i++) {
                list.add(randomString());
            }

            OutputStream fileOutput = new FileOutputStream("arraylist.txt");
            for (String s : list) {
                fileOutput.write((s + "\n").getBytes());
            }
            fileOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String randomString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            result.append((char) (65 + (Math.random() * 26)));
        }
        return result.toString();
    }
}
