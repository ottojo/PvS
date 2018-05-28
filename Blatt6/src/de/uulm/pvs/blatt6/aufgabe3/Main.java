package de.uulm.pvs.blatt6.aufgabe3;

import java.io.*;

public class Main {
    private static final String string = "Josef Ulm josef@ulm.de;Flo Kuchen flo@ulm.de;Jens Senden jens@ulm.de";

    public static void main(String[] args) {
        // a
        String array[][] = new String[3][3];

        int l = 0;
        for (String line : string.split(";")) {
            int c = 0;
            for (String col : line.split(" ")) {
                array[l][c] = col;
                c++;
            }
            l++;
        }


        // b
        try {
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("emails.csv"));
            for (String[] line : array) {
                for (int field = 0; field < line.length; field++) {
                    outputStream.write(line[field].getBytes());
                    if (field != line.length - 1) outputStream.write(',');
                }
                outputStream.write('\n');
            }
            outputStream.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }


        // c
        try {
            BufferedReader reader = new BufferedReader(new FileReader("emails.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length < 3) continue;
                System.out.printf("Vorname: %s, Nachname: %s, email: %s%n", fields[0], fields[1], fields[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
