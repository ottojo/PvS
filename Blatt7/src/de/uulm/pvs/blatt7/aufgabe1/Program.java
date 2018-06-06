package de.uulm.pvs.blatt7.aufgabe1;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class Program {

    public static void main(String[] args) {
        Car mercedes = new Mercedes("NO-OB-1337", "1.2.2018", 5,
                4, 4, "Model 3", 7);
        System.out.println("Created Mercedes:");
        System.out.println(mercedes);
        try (XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("mercedes.xml")))) {
            e.writeObject(mercedes);
            System.out.println("Mercedes has been saved to mercedes.xml");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }


        try (XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream("mercedes.xml")))) {
            Object result = d.readObject();
            System.out.println("Read Mercedes from mercedes.xml: ");
            System.out.println(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Car porsche = new Porsche("PR-0-420", "2.2.2018", 2,
                4, 2, "911", 1000);
        System.out.println("Created Porsche:");
        System.out.println(porsche);

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream("porsche.bin")))) {
            objectOutputStream.writeObject(porsche);

            System.out.println("Porsche has been saved to porsche.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream("porsche.bin")))) {
            Object result = objectInputStream.readObject();
            System.out.println("Read Porsche from porsche.bin: ");
            System.out.println(result);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
