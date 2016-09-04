package com.udacitynanodegree.cristhian.capstoneproject.managers.persistence.models;

import com.google.firebase.database.IgnoreExtraProperties;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItem;

@IgnoreExtraProperties
public class Vehicle implements GenericItem {

    private String engine;
    private String make;
    private String model;
    private String transmission;
    private String year;

    public Vehicle() {
    }

    public Vehicle(String engine, String make, String model, String transmission, String year) {
        this.engine = engine;
        this.make = make;
        this.model = model;
        this.transmission = transmission;
        this.year = year;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "engine='" + engine + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", transmission='" + transmission + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vehicle vehicle = (Vehicle) o;

        if (engine != null ? !engine.equals(vehicle.engine) : vehicle.engine != null) {
            return false;
        }
        if (make != null ? !make.equals(vehicle.make) : vehicle.make != null) {
            return false;
        }
        if (model != null ? !model.equals(vehicle.model) : vehicle.model != null) {
            return false;
        }
        return transmission != null ? transmission.equals(vehicle.transmission) : vehicle.transmission == null && (year != null ? year.equals(vehicle.year) : vehicle.year == null);

    }

    @Override
    public int hashCode() {
        int result = engine != null ? engine.hashCode() : 0;
        result = 31 * result + (make != null ? make.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (transmission != null ? transmission.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }

    @Override
    public int getType() {
        return 0;
    }
}
