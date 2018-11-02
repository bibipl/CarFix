package pl.coderslab.dao;

import pl.coderslab.model.Client;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

//######################################################################################################################################################
//    DAO CREATE/UPDATE - id==0 :CREATE; id !=0: UPDATE
// SaveToDb - Static. IN connection and object car. OUT -1 - error, otherwise id of the "EMPLOYEE"
//######################################################################################################################################################

    public static int saveToDB(Client cl) {
        if (cl.getId() == 0) {
            try {
                Connection conn = DbUtil.getConn();
                String sql = "INSERT INTO client(name,surname,birthdate,phone) VALUES (?, ?, ?, ?)";
                String[] generatedColumns = {"ID"};
                PreparedStatement preparedStatement = conn.prepareStatement(sql, generatedColumns);
                preparedStatement.setString(1, cl.getName());
                preparedStatement.setString(2, cl.getSurname());
                preparedStatement.setString(3, java.sql.Date.valueOf(cl.getBirthDate()).toString());
                preparedStatement.setString(4, cl.getPhone());
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    cl.setId(rs.getInt(1));
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
            String sql = "UPDATE client SET name=?,surname=?,birthdate=?,phone=? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, cl.getName());
            preparedStatement.setString(2, cl.getSurname());
            preparedStatement.setString(3, java.sql.Date.valueOf(cl.getBirthDate()).toString());
            preparedStatement.setString(4, cl.getPhone());
            preparedStatement.setInt(5, cl.getId());
            preparedStatement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return -1;
        }
        return cl.getId();
    } // End SaveToDB

//######################################################################################################################################################
    // DAO READ 1 "EMPLOYEE" by id
    // RETURNS OBJECT "EMPLOYEE" or OBJECT NULL;
//######################################################################################################################################################

    public static Client loadClientById(int id) {
        String sql = "SELECT * FROM client where id=?";
        Client loadedClient = new Client();
        try {
            Connection conn = DbUtil.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) loadedClient = (uploadClient(resultSet));
            conn.close();
        } catch (SQLException e) {
            return null;
        }
        return loadedClient;
    }

//######################################################################################################################################################
    // READ ALL "EMPLOYEE"s
    // Returns Array List of Object "EMPLOYEE" or "NULL"
//######################################################################################################################################################

    public static List<Client> loadAllClients() {
        ArrayList<Client> employees = new ArrayList<Client>();
        String sql = "SELECT * FROM client ORDER BY surname";
        try {
            Connection conn = DbUtil.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) employees.add(uploadClient(resultSet));
            conn.close();
        } catch (SQLException e) {
            return null;
        }
        return employees;
    }
//######################################################################################################################################################
    // READ ALL "EMPLOYEE"s by name.
    // Returns Array List of Object "EMPLOYEE" or "NULL"
//######################################################################################################################################################

    public static List<Client> loadClientsByName(String findHim) {
        ArrayList<Client> employees = new ArrayList<Client>();
        String sql = "SELECT * FROM client WHERE surname LIKE ?";
        try {
            Connection conn = DbUtil.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, "%"+findHim+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) employees.add(uploadClient(resultSet));
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

    public static boolean delete(Client client) {
        if (client.getId() != 0) {
            String sql = "DELETE FROM client WHERE id=?";
            try {
                Connection conn = DbUtil.getConn();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, client.getId());
                preparedStatement.executeUpdate();
                client.setId(0);
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

    private static Client uploadClient (ResultSet resultSet) {
        Client loadedClient = new Client();
        try {
            loadedClient.setId(resultSet.getInt("id"));
            loadedClient.setName(resultSet.getString("name"));
            loadedClient.setSurname(resultSet.getString("surname"));
            loadedClient.setBirthDate(resultSet.getDate("birthDate").toLocalDate());
            loadedClient.setPhone(resultSet.getString("phone"));
        } catch (SQLException e) {
            return null;
        }
        return loadedClient;
    }

//######################################################################################################################################################
}


