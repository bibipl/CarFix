package pl.coderslab.dao;
import pl.coderslab.model.Employee;
import pl.coderslab.service.DbService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {



//    public static Employee save(Employee employee){
//        if (employee.getId() == 0) { // jeżeli 0 to zapisuje nowego
//            List<String> fieldsToString = new ArrayList();
//            fieldsToString = employee.convertEmployeeToString(); // change object to string list
//            String sql = "INSERT INTO employee (name, surname, address, phone, note, hourPrice) VALUES (?, ?, ?, ?, ?, ?)";
//            try {
//                Integer newId = DbService.insertIntoDatabase(sql, fieldsToString);
//                if (newId = null) return null; // jeśli null to nie udało się zapisać i zwraca pusty Employee;
//                else {
//                    fieldsToString = DbService.getData("SELECT name, surname, address, phone, note, hourPrice from employee where id=?")
//
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        else {// jeżeli id nie zero to jest to modyfikacja
//            String sql = "UPDATE employee SET name=?, surname=?, address=?, phone=?, note=?, hourPrice=? WHERE id = ?";
//            String sId = String.valueOf(employee.getId());
//        }
//
//        return null;
//    }



} // end Class
