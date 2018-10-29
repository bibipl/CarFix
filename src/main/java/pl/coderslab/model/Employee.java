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

    // Dao Create/Update
    // Zapisz do  BD zapisuje nowy element do BD ub zmodyfikowany element. Poznajemy po id==0
    public void saveToDB(Connection conn) throws SQLException {
        if (this.id == 0) {
            String sql = "INSERT INTO employee(name,surname,address,phone,note,hourprice) VALUES (?, ?, ?, ?, ?, ?)";
            String[] generatedColumns = {"ID"};
            PreparedStatement preparedStatement = conn.prepareStatement(sql, generatedColumns);
            preparedStatement.setString(1, this.name);
            preparedStatement.setString(2, this.surname);
            preparedStatement.setString(3, this.address);
            preparedStatement.setString(4, this.phone);
            preparedStatement.setString(5, this.note);
            preparedStatement.setFloat(6, this.hourPrice);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        } else {
            String sql = "UPDATE employee SET name=?,surname=?, address=?,phone=?,note=?,hourprice=? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, this.name);
            preparedStatement.setString(2, this.surname);
            preparedStatement.setString(3, this.address);
            preparedStatement.setString(4, this.phone);
            preparedStatement.setString(5, this.note);
            preparedStatement.setFloat(6, this.hourPrice);
            preparedStatement.setInt(7, this.getId());
            preparedStatement.executeUpdate();
        }
    }
    // DAO READ
    // Wczytaj 1 employee po id. Metoda statyczna dlatego przekzujemy dodatkowo id
    static public Employee loadEmployeeById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM employee where id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Employee loadedEmployee = new Employee();
            loadedEmployee.id = resultSet.getInt("id");
            loadedEmployee.name = resultSet.getString("name");
            loadedEmployee.surname = resultSet.getString("surname");
            loadedEmployee.address = resultSet.getString("address");
            loadedEmployee.phone = resultSet.getString("phone");
            loadedEmployee.note = resultSet.getString("note");
            loadedEmployee.hourPrice=resultSet.getFloat("hourprice");
            return loadedEmployee;}
        return null;}

    // Wczytaj wszystkich z BD
    static public List<Employee> loadAllEmployees(Connection conn) throws SQLException {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        String sql = "SELECT * FROM employee";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Employee loadedEmployee = new Employee();
            loadedEmployee.id = resultSet.getInt("id");
            loadedEmployee.name = resultSet.getString("name");
            loadedEmployee.surname = resultSet.getString("surname");
            loadedEmployee.address = resultSet.getString("address");
            loadedEmployee.phone = resultSet.getString("phone");
            loadedEmployee.note = resultSet.getString("note");
            loadedEmployee.hourPrice = resultSet.getFloat("hourprice");
            employees.add(loadedEmployee);
        }
        return employees;
    }
    // DAO DELETE
    // usuń employee zBD
    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM employee WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0;
        }
    }
    // converts
    public void convertStingToEmployee (List<String> emplFields){
        Employee employee = new Employee();
        String sId = emplFields.get(0);
        try {
            employee.id=Integer.parseInt(emplFields.get(0));
            employee.name = emplFields.get(1);
            employee.surname = emplFields.get(2);
            employee.address = emplFields.get(3);
            employee.phone = emplFields.get(4);
            employee.note = emplFields.get(5);
            employee.hourPrice = Float.parseFloat(emplFields.get(6));
        } catch (NumberFormatException e) {
            e.printStackTrace(); /// tou można lepiej poprowadzić błąd....
        }
    }

    public List<String> convertEmployeeToString () {
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

