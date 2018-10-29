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


    public static Employee save(Employee employee){
        if (this.id == 0) { // jeżeli 0 to zapisuje nowego
            List<String> fieldsToString = new ArrayList();
            fieldsToString.add(this.getName());
            fieldsToString.add(this.getSurname());
            fieldsToString.add(this.getAddress());
            fieldsToString.add(this.getPhone());
            fieldsToString.add(this.getNote());
            fieldsToString.add(String.valueOf(this.getHourPrice()));
            String sql = "INSERT INTO employee(name, surname, address, phone, note, hourPrice) VALUES (?, ?, ?, ?, ?, ?)";
            try {
                Integer newId = DbService.insertIntoDatabase(sql, fieldsToString);
                if (newId = null) return null; // jeśli null to nie udało się zapisać i zwraca pusty Employee;
                else {
                    /// wczytaj emloyeebyId  i zwróć wczytanego
                }
            } catch (SQLException e) {
                e.printStackTrace(); /// tu obsłużyć błąd napisem jakimś....
            }
        }
        } else {// jeżeli id nie zero to jest to modyfikacja
            String sql = "UPDATE employee SET name=?, surname=?, address=?, phone=?, note=?, hourPrice=? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, this.userName);
            preparedStatement.setString(2, this.email);
            preparedStatement.setString(3, this.password);
            preparedStatement.setInt(4, this.userGroup.getId()); // pobieramy z pola GR ident GRid
            preparedStatement.setInt(5, this.getId()); // pobieramy z pola GR ident GRid

            preparedStatement.executeUpdate();
        }

        return null;
    }

    public static void delete(Solution solution){
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(solution.getId()));

        try {
            DbService.executeUpdate("DELETE FROM solution WHERE id=?",params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Solution findById(int id){
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        List<Solution> list = prepareSolutions("SELECT id, description, created, updated FROM solution WHERE id=?",params);
        if(list!=null && list.size()>0) {
            return list.get(0);
        }
        return null;
    }

} // end Class
