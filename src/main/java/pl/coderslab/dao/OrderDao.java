package pl.coderslab.dao;

import pl.coderslab.model.Order;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
//######################################################################################################################################################
//    DAO CREATE/UPDATE - id==0 :CREATE; id !=0: UPDATE
// SaveToDb - Static. IN object order. OUT -1 - error, otherwise id
//######################################################################################################################################################

    public static int saveToDB(Order order) {
        if (order.getId() == 0) {
            try {
                Connection conn = DbUtil.getConn();
                String sql = "INSERT INTO repair(plan_start_date,real_start_date, employee_id,problem_descript,fix_descript,stat,car_id,value_serv,value_parts,hour_price,num_of_hours) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                String[] generatedColumns = {"ID"};
                PreparedStatement preparedStatement = conn.prepareStatement(sql, generatedColumns);

                if (order.getPlanStartDate() != null) preparedStatement.setString(1, java.sql.Date.valueOf(order.getPlanStartDate()).toString());
                else preparedStatement.setString(1, null);
                if (order.getRealStartDate() != null) preparedStatement.setString(2, java.sql.Date.valueOf(order.getRealStartDate()).toString());
                else preparedStatement.setString(2, null);
                preparedStatement.setInt(3, order.getEmployeeId());
                preparedStatement.setString(4, order.getProblemDescript());
                preparedStatement.setString(5, order.getFixDescript());
                preparedStatement.setInt(6, order.getStatus());
                preparedStatement.setInt(7, order.getCarId());
                if (order.getValueServ() != null) preparedStatement.setFloat(8, order.getValueServ());
                else preparedStatement.setFloat(8, 0);
                if (order.getValueParts() != null)preparedStatement.setFloat(9, order.getValueParts());
                else preparedStatement.setFloat(9, 0);
                if (order.getHourPrice() != null)preparedStatement.setFloat(10, order.getHourPrice());
                else preparedStatement.setFloat(10, 0);
                preparedStatement.setInt(11, order.getNumOfHours());
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                return -1;
            }
        } else try {
            Connection conn = DbUtil.getConn();
            String sql = "UPDATE repair SET plan_start_date=?,real_start_date=?,employee_id=?,problem_descript=?,fix_descript=?," +
                         "stat=?,car_id=?,value_serv=?,value_parts=?,hour_price=?,num_of_hours=? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, java.sql.Date.valueOf(order.getPlanStartDate()).toString());
            preparedStatement.setString(2, java.sql.Date.valueOf(order.getRealStartDate()).toString());
            preparedStatement.setInt(3, order.getEmployeeId());
            preparedStatement.setString(4, order.getProblemDescript());
            preparedStatement.setString(5, order.getFixDescript());
            preparedStatement.setInt(6, order.getStatus());
            preparedStatement.setInt(7, order.getCarId());
            preparedStatement.setFloat(8, order.getValueServ());
            preparedStatement.setFloat(9, order.getValueParts());
            preparedStatement.setFloat(10, order.getHourPrice());
            preparedStatement.setInt(11, order.getNumOfHours());
            preparedStatement.setInt(12, order.getNumOfHours());
            preparedStatement.executeUpdate();
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return -1;
        }
        return order.getId();
    } // End SaveToDB

//######################################################################################################################################################
    // DAO READ 1 order by id
    // RETURNS OBJECT "ORDER" or OBJECT "NULL";
//######################################################################################################################################################

    public static Order loadOrderById(int id) {
        String sql = "SELECT * FROM repair where id=?";
        Order loadedOrder = new Order();
        try {
            Connection conn = DbUtil.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) loadedOrder = (uploadOrder(resultSet));

        } catch (SQLException e) {
            return null;
        }
        return loadedOrder;
    }

//######################################################################################################################################################
    // READ ALL "ORDERS"
    // Returns Array List of Object "ORDER" or "NULL"
//######################################################################################################################################################

    public static List<Order> loadAllOrders() {
        ArrayList<Order> orders = new ArrayList<Order>();
        String sql = "SELECT * FROM repair";
        try {
            Connection conn = DbUtil.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) orders.add(uploadOrder(resultSet));

        } catch (SQLException e) {
            return null;
        }
        return orders;
    }

//######################################################################################################################################################
    // DAO DELETE
    // Deletes "ORDER" and returns true (deleted) or false (delete unsuccessfull)
//######################################################################################################################################################

    public static boolean delete(Order order) {
        if (order.getId() != 0) {
            String sql = "DELETE FROM repair WHERE id=?";
            try {
                Connection conn = DbUtil.getConn();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, order.getId());
                preparedStatement.executeUpdate();
                order.setId(0);
            } catch (SQLException e) {
                return false;
            }
        }
        return true;
    }

//######################################################################################################################################################
    // DAO LOAD "ORDERS" BY "CAR" ID
    // Returns list of all cars of Owner(ID).
//######################################################################################################################################################

    public static List<Order> loadAllOrders_Car(int carId) {
        ArrayList<Order> orders = new ArrayList<Order>();
        String sql = "SELECT * FROM repair WHERE car_id=?";
        try {
            Connection conn = DbUtil.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, carId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) orders.add(uploadOrder(resultSet));

        } catch (SQLException e) {
            return null;
        }
        return orders;
    }

//######################################################################################################################################################
    // DAO LOAD "ORDERS" BY "EMPLOYEE" ID
    // Returns list of all cars of Owner(ID).
//######################################################################################################################################################

    public static List<Order> loadAllOrders_Empl(int emplId) {
        ArrayList<Order> orders = new ArrayList<Order>();
        String sql = "SELECT * FROM repair WHERE employee__id=?";
        try {
            Connection conn = DbUtil.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, emplId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) orders.add(uploadOrder(resultSet));

        } catch (SQLException e) {
            return null;
        }
        return orders;
    }

//######################################################################################################################################################
    // loads Object CAR with data from resultSet
    // returns O CAR or null id SQR exception occurs;
//######################################################################################################################################################

    private static Order uploadOrder (ResultSet resultSet) {
        Order loadedOrder = new Order();
        try {
            loadedOrder.setId(resultSet.getInt("id"));
            loadedOrder.setPlanStartDate(resultSet.getDate("plan_start_date").toLocalDate());
            if (resultSet.getDate("real_start_date") != null)
                loadedOrder.setRealStartDate(resultSet.getDate("real_start_date").toLocalDate());
            else loadedOrder.setRealStartDate (null);
            loadedOrder.setEmployeeId(resultSet.getInt("employee_Id"));
            loadedOrder.setProblemDescript(resultSet.getString("problem_descript"));
            loadedOrder.setFixDescript(resultSet.getString("fix_descript"));
            loadedOrder.setStatus(resultSet.getInt("stat"));
            loadedOrder.setCarId(resultSet.getInt("car_id"));
            loadedOrder.setValueServ(resultSet.getFloat("value_serv"));
            loadedOrder.setValueParts(resultSet.getFloat("value_parts"));
            loadedOrder.setHourPrice(resultSet.getFloat("hour_price"));
            loadedOrder.setNumOfHours(resultSet.getInt("num_of_hours"));
        } catch (SQLException e) {
            return null;
        }
        return loadedOrder;
    }

//######################################################################################################################################################
}
