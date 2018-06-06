package de.uulm.pvs.blatt7.aufgabe1;

public class Porsche extends Car {
    private static final long serialVersionUID = 8936338967238741013L;
    private String model;
    private int ps;

    public Porsche() {

    }

    public Porsche(String licensePlate, String productionDate, int numberPassengers, int numberWheels, int numberDoors,
                   String model, int ps) {
        super(licensePlate, productionDate, numberPassengers, numberWheels, numberDoors);
        this.model = model;
        this.ps = ps;
    }

    @Override
    public String toString() {
        return "Porsche{" +
                "model='" + model + '\'' +
                ", ps=" + ps +
                ", licensePlate='" + licensePlate + '\'' +
                ", productionDate='" + productionDate + '\'' +
                ", numberPassengers=" + numberPassengers +
                ", numberWheels=" + numberWheels +
                ", numberDoors=" + numberDoors +
                '}';
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }
}
