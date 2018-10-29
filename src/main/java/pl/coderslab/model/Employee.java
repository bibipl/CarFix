package pl.coderslab.model;

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


    public String getName() {
        return name;
    }

    void setId(int id) { // to convert String into class - only within package
        this.id = id;
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
    public Employee convertStingToEmployee (List<String> emplFields){

        Employee employee = new Employee();
        String sId = emplFields.get(0);
        try {
            employee.id=Integer.parseInt(emplFields.get(0));
            String name = emplFields.get(1);
            employee surname = emplFields.get(2);
            employee address = emplFields.get(3);
            employee phone = emplFields.get(4);
            employee note = emplFields.get(5);
            employee hourPrice = Float.parseFloat(emplFields.get(6));
            return  employee;
        } catch (NumberFormatException e) {
            e.printStackTrace(); /// tou można lepiej poprowadzić błąd....
            return null;
        }
    }

    public List<String> convertEmployeeToString (Employee employee) {
        List<String> emplList = new ArrayList<>();
        emplList.add(String.valueOf(id));
        emplList.add(name);
        emplList.add(surname);
        emplList.add(address);
        emplList.add(phone);
        emplList.add(note);
        emplList.add(String.valueOf(hourPrice));
        return emplList;
    }

}

