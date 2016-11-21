package com.udacitynanodegree.cristhian.capstoneproject.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

@IgnoreExtraProperties
public class Person {

    private String uId;
    private String email;
    private String lastName;
    private String name;
    private List<Vehicle> vehicles;

    public Person() {
    }

    public Person(String uId, String email, String lastName, String name, List<Vehicle> vehicles) {
        this.uId = uId;
        this.email = email;
        this.lastName = lastName;
        this.name = name;
        this.vehicles = vehicles;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vehicle> getVehicles() {
        if (vehicles == null) {
            vehicles = new ArrayList<>();
        }
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "Person{" +
                "email='" + email + '\'' +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (uId != null ? !uId.equals(person.uId) : person.uId != null) return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null)
            return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        return vehicles != null ? vehicles.equals(person.vehicles) : person.vehicles == null;

    }

    @Override
    public int hashCode() {
        int result = uId != null ? uId.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (vehicles != null ? vehicles.hashCode() : 0);
        return result;
    }
}
