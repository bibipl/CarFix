package pl.coderslab.dao;

import pl.coderslab.model.Employee;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

//######################################################################################################################################################
//    DAO CREATE/UPDATE - id==0 :CREATE; id !=0: UPDATE
// SaveToDb - Static. IN connection and object car. OUT -1 - error, otherwise id of the "EMPLOYEE"
//######################################################################################################################################################

    public static int saveToDB(Employee empl) {
        if (empl.getId() == 0) {
            try {
                Connection conn = DbUtil.getConn();
                String sql = "INSERT INTO employee(name,surname,address,phone,note,hourprice) VALUES (?, ?, ?, ?, ?, ?)";
                String[] generatedColumns = {"ID"};
                PreparedStatement preparedStatement = conn.prepareStatement(sql, generatedColumns);
                preparedStatement.setString(1, empl.getName());
                preparedStatement.setString(2, empl.getSurname());
                preparedStatement.setString(3, empl.getAddress());
                preparedStatement.setString(4, empl.getPhone());
                preparedStatement.setString(5, empl.getNote());
                preparedStatement.setFloat(6, empl.getHourPrice());
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    empl.setId(rs.getInt(1));
                }
                if (rs.next()) {
                    return rs.getInt(1);
                }
                conn.close();
            } catch (SQLException e) {
                return -1;
            }
        } else try {
            Connection conn = DbUtil.getConn();
            String sql = "UPDATE employee SET name=?,surname=?,address=?,phone=?,note=?,hourprice=? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, empl.getName());
            preparedStatement.setString(2, empl.getSurname());
            preparedStatement.setString(3, empl.getAddress());
            preparedStatement.setString(4, empl.getPhone());
            preparedStatement.setString(5, empl.getNote());
            preparedStatement.setFloat(6, empl.getHourPrice());;
            preparedStatement.setInt(7, empl.getId());
            preparedStatement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return -1;
        }
        return empl.getId();
    } // End SaveToDB

//######################################################################################################################################################
    // DAO READ 1 "EMPLOYEE" by id
    // RETURNS OBJECT "EMPLOYEE" or OBJECT NULL;
//######################################################################################################################################################

    public static Employee loadEmployeeById(int id) {
        String sql = "SELECT * FROM employee where id=?";
        Employee loadedEmployee = new Employee();
        try {
            Connection conn = DbUtil.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) loadedEmployee = (uploadEmployee(resultSet));
            conn.close();
        } catch (SQLException e) {
            return null;
        }
        return loadedEmployee;
    }

//######################################################################################################################################################
    // READ ALL "EMPLOYEE"s
    // Returns Array List of Object "EMPLOYEE" or "NULL"
//######################################################################################################################################################

    public static List<Employee> loadAllEmployees() {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        String sql = "SELECT * FROM employee";
        try {
            Connection conn = DbUtil.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) employees.add(uploadEmployee(resultSet));
            conn.close();
        } catch (SQLException e) {
            return null;
        }
        return employees;
    }

//######################################################################################################################################################
    // DAO DELETE
    // Deletes "EMPLOYEE" and returns true (deleted) or false (delete unsuccessfull)
//######################################################################################################################################################

    public static boolean delete(Employee employee) {
        if (employee.getId() != 0) {
            String sql = "DELETE FROM employee WHERE id=?";
            try {
                Connection conn = DbUtil.getConn();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, employee.getId());
                preparedStatement.executeUpdate();
                employee.setId(0);
                conn.close();
            } catch (SQLException e) {
                return false;
            }
        }
        return true;
    }

//######################################################################################################################################################
    // loads Object "EMPLOYEE" with data from resultSet
    // returns O CAR or null id SQR exception occurs;
//######################################################################################################################################################

    private static Employee uploadEmployee (ResultSet resultSet) {
        Employee loadedEmployee = new Employee();
        try {
            loadedEmployee.setId(resultSet.getInt("id"));
            loadedEmployee.setName(resultSet.getString("name"));
            loadedEmployee.setSurname(resultSet.getString("surname"));
            loadedEmployee.setAddress(resultSet.getString("address"));
            loadedEmployee.setPhone(resultSet.getString("phone"));
            loadedEmployee.setNote(resultSet.getString("note"));
            loadedEmployee.setHourPrice(resultSet.getFloat("hourPrice"));
        } catch (SQLException e) {
            return null;
        }
        return loadedEmployee;
    }

//######################################################################################################################################################
}

