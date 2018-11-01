package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Employee {

// variables.
    private int id;
    private String name;
    private String surname;
    private String address;
    private String phone;
    private String note;
    private float hourPrice;
// constructors - id by default=0;
    public Employee() {
    }

    public Employee(String name, String surname, String address, String phone, String note, float hourPrice) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.note = note;
        this.hourPrice = hourPrice;
    }
// getters and setters;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public float getHourPrice() {
        return hourPrice;
    }

    public void setHourPrice(float hourPrice) {
        this.hourPrice = hourPrice;
    }

}

