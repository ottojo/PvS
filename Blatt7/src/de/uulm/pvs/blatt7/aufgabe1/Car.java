package de.uulm.pvs.blatt7.aufgabe1;

import java.io.Serializable;

public class Car implements Serializable {
    private static final long serialVersionUID = 8616098304601089686L;
    protected String licensePlate;
    protected String productionDate;
    protected int numberPassengers;
    protected int numberWheels = 4;
    protected int numberDoors;

    public Car() {

    }

    public Car(String licensePlate, String productionDate, int numberPassengers, int numberWheels, int numberDoors) {
        this.licensePlate = licensePlate;
        this.productionDate = productionDate;
        this.numberPassengers = numberPassengers;
        this.numberWheels = numberWheels;
        this.numberDoors = numberDoors;
    }

    @Override
    public String toString() {
        return "Car{" +
                "licensePlate='" + licensePlate + '\'' +
                ", productionDate='" + productionDate + '\'' +
                ", numberPassengers=" + numberPassengers +
                ", numberWheels=" + numberWheels +
                ", numberDoors=" + numberDoors +
                '}';
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public int getNumberPassengers() {
        return numberPassengers;
    }

    public void setNumberPassengers(int numberPassengers) {
        this.numberPassengers = numberPassengers;
    }

    public int getNumberWheels() {
        return numberWheels;
    }

    public void setNumberWheels(int numberWheels) {
        this.numberWheels = numberWheels;
    }

    public int getNumberDoors() {
        return numberDoors;
    }

    public void setNumberDoors(int numberDoors) {
        this.numberDoors = numberDoors;
    }
}
