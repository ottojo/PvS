package de.uulm.pvs.blatt7.aufgabe2;

import de.uulm.pvs.blatt7.aufgabe1.Car;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.util.Arrays;

public class Program {
    public static void main(String[] args) {

        Car car = new Car("FN-AB-123", "1.2.2003", 4, 4, 4);
        System.out.println("Created Car:");
        System.out.println(car);

        try (BufferedOutputStream bufferedOutputStream =
                     new BufferedOutputStream(new FileOutputStream("car.xml"))) {
            writeCarToXml(car, bufferedOutputStream);
            System.out.println("Car has been saved to car.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("car.xml"))) {
            System.out.println("Read Car from car.xml: ");
            System.out.println(getCarFromXml(bufferedInputStream));
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeCarToXml(Car car, OutputStream outputStream) throws IOException {
        Element rootElement = new Element("Car");
        Element licensePlate = new Element("licensePlate").addContent(car.getLicensePlate());
        Element productionDate = new Element("productionDate").addContent(car.getProductionDate());
        Element numberPassengers = new Element("numberPassengers").addContent(car.getNumberPassengers() + "");
        Element numberWheels = new Element("numberWheels").addContent(car.getNumberWheels() + "");
        Element numberDoors = new Element("numberDoors").addContent(car.getNumberDoors() + "");

        rootElement.addContent(Arrays.asList(licensePlate, productionDate, numberPassengers, numberWheels, numberDoors));
        new XMLOutputter(Format.getPrettyFormat()).output(new Document(rootElement), outputStream);
    }

    public static Car getCarFromXml(InputStream inputStream) throws JDOMException, IOException {
        Document document = new SAXBuilder().build(inputStream);
        Car car = new Car();
        Element rootElement = document.getRootElement();

        car.setLicensePlate(rootElement.getChild("licensePlate").getContent().get(0).getValue());
        car.setProductionDate(rootElement.getChild("productionDate").getContent().get(0).getValue());
        car.setNumberPassengers(Integer.parseInt(rootElement.getChild("numberPassengers").getContent().get(0).getValue()));
        car.setNumberWheels(Integer.parseInt(rootElement.getChild("numberWheels").getContent().get(0).getValue()));
        car.setNumberDoors(Integer.parseInt(rootElement.getChild("numberDoors").getContent().get(0).getValue()));

        return car;
    }
}
