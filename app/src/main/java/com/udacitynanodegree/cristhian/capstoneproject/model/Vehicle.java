package com.udacitynanodegree.cristhian.capstoneproject.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItem;

import java.util.List;

@IgnoreExtraProperties
public class Vehicle implements GenericItem {

    private String year;
    private String make;
    private String model;
    private String submodel;
    private String engine;
    private List<AutoPart> autoParts;

    public Vehicle() {
    }

    public Vehicle(String year, String make, String model, String submodel, String engine, List<AutoPart> autoParts) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.submodel = submodel;
        this.engine = engine;
        this.autoParts = autoParts;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public String getSubmodel() {
        return submodel;
    }

    public void setSubmodel(String submodel) {
        this.submodel = submodel;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public List<AutoPart> getAutoParts() {
        return autoParts;
    }

    public void setAutoParts(List<AutoPart> autoParts) {
        this.autoParts = autoParts;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "year='" + year + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", submodel='" + submodel + '\'' +
                ", engine='" + engine + '\'' +
                ", autoParts=" + autoParts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (year != null ? !year.equals(vehicle.year) : vehicle.year != null) return false;
        if (make != null ? !make.equals(vehicle.make) : vehicle.make != null) return false;
        if (model != null ? !model.equals(vehicle.model) : vehicle.model != null) return false;
        if (submodel != null ? !submodel.equals(vehicle.submodel) : vehicle.submodel != null)
            return false;
        if (engine != null ? !engine.equals(vehicle.engine) : vehicle.engine != null) return false;
        return autoParts != null ? autoParts.equals(vehicle.autoParts) : vehicle.autoParts == null;

    }

    @Override
    public int hashCode() {
        int result = year != null ? year.hashCode() : 0;
        result = 31 * result + (make != null ? make.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (submodel != null ? submodel.hashCode() : 0);
        result = 31 * result + (engine != null ? engine.hashCode() : 0);
        result = 31 * result + (autoParts != null ? autoParts.hashCode() : 0);
        return result;
    }

    @Override
    public int getType() {
        return 0;
    }
}
