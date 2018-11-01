package pl.coderslab.model;
import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
public class Client {
    // variables.

    private int id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String phone;

// java.sql.Date sqlDate = new java.sql.Date(now.getTime()); date util to date sql
    // constructors - id by default=0;
    public Client() {
    }

    public Client(String name, String surname, LocalDate birthDate, String phone) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }

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

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }



}
