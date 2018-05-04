package de.uulm.pvs.aufgabe3;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /*
        Calculating the Sum of all lines in the file "bigInput.txt" in the current
        directory using Scanner and FileInputStream.
        */
        try {
            long startTime = System.currentTimeMillis();
            Scanner scanner = new Scanner(new FileInputStream("bigInput.txt"));
            long sum = 0;
            while (scanner.hasNextLong()) {
                sum += scanner.nextLong();
            }
            long time = System.currentTimeMillis() - startTime;
            System.out.printf("Sum is %d%n", sum);
            System.out.printf("Reading using Scanner and FIleInputStream took %dms%n", time);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        /*
        Calculating the Sum of all lines in the file "bigInput.txt" in the current
        directory using BufferedReader and FileReader, parsing using Integer.parseInt().
        */
        try {
            long startTime = System.currentTimeMillis();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("bigInput.txt"));
            long sum = 0;

            String s;
            while (true) {
                s = bufferedReader.readLine();
                if (s != null) sum += Integer.parseInt(s);
                else break;
            }

            long time = System.currentTimeMillis() - startTime;
            System.out.printf("Sum is %d%n", sum);
            System.out.printf("Reading using BufferedReader and FIleReader took %dms%n", time);
        } catch (IOException e) {
            System.out.println("Error reading file:");
            e.printStackTrace();
        }

        /*
        Scanner braucht deutlich länger, da er zum einen einen kleineren Buffer von 1KB (BufferedReader: 8KB) hat,
        und weil Scanner während dem Einlesen bereits versucht, die Eingabe zu parsen, was nicht sehr effizient ist.
        Bei der IMplementierung mit BufferedReader hingegen wird jeweils eine Zeile gelesen und diese dann geparst.
         */
    }
}
