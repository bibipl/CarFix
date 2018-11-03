package pl.coderslab.dao;

import pl.coderslab.model.Order;
import pl.coderslab.model.Report1;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.time.LocalDate;
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
                String sql = "INSERT INTO repair(plan_start_date,real_start_date, employee_id,problem_descript,fix_descript,stat,car_id,value_serv,value_parts," +
                        "hour_price,num_of_hours, real_end_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                if (order.getNumOfHours() != null)preparedStatement.setFloat(11, order.getNumOfHours());
                else preparedStatement.setFloat(11, 0);
                if (order.getRealEndDate() != null) preparedStatement.setString(12, java.sql.Date.valueOf(order.getRealEndDate()).toString());
                else preparedStatement.setString(12, null);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
                conn.close();
            } catch (SQLException e) {
                return -1;
            }
        } else try {
            Connection conn = DbUtil.getConn();
            String sql = "UPDATE repair SET plan_start_date=?,real_start_date=?,employee_id=?,problem_descript=?,fix_descript=?," +
                         "stat=?,car_id=?,value_serv=?,value_parts=?,hour_price=?,num_of_hours=?,real_end_date=? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
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

            if( order.getEmployeeId()!=0 ) {
                preparedStatement.setFloat(10, EmployeeDao.loadEmployeeById(order.getEmployeeId()).getHourPrice());
            } else {
                preparedStatement.setFloat(10, 0);
            }
           /* if (order.getHourPrice() != null)preparedStatement.setFloat(10, order.getHourPrice());
            else preparedStatement.setFloat(10, 0); we adjust automatically hourprice. This is to be deleted*/

            if (order.getNumOfHours() != null)preparedStatement.setFloat(11, order.getNumOfHours());
            else preparedStatement.setFloat(11, 0);
            if (order.getRealEndDate() != null) preparedStatement.setString(12, java.sql.Date.valueOf(order.getRealEndDate()).toString());
            else preparedStatement.setString(12, null);
            preparedStatement.setInt(13, order.getId());
            preparedStatement.executeUpdate();
            conn.close();
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
        conn.close();
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
        String sql = "SELECT * FROM repair ORDER BY real_start_date DESC ";
        try {
            Connection conn = DbUtil.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) orders.add(uploadOrder(resultSet));
        conn.close();
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
                conn.close();
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
        String sql = "SELECT * FROM repair WHERE car_id=?";
        askSQL(carId, sql);
        return askSQL(carId, sql);
    }

//######################################################################################################################################################
    // DAO LOAD "ORDERS" BY "EMPLOYEE" ID
    // Returns list of all cars of Owner(ID).
//######################################################################################################################################################

    public static List<Order> loadAllOrders_Empl(int emplId) {
        String sql = "SELECT * FROM repair WHERE employee_id=?";
        return askSQL(emplId, sql);
    }
//######################################################################################################################################################
    // DAO LOAD "ORDERS" BY "PERIOD OF TIME"
    // Returns list of all cars of Owner(ID).
//######################################################################################################################################################

    public static List<Order> loadOrders_PeriodEnd(String dateS, String dateE) {
        String sql = "SELECT * FROM repair WHERE real_end_date>=? AND real_end_date<=?";
        List<Order> temp = new ArrayList<>();
        try {
            Connection conn = DbUtil.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, dateS);
            preparedStatement.setString(2, dateE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) temp.add(uploadOrder(resultSet));
            conn.close();
        } catch (SQLException e) {
            return null;
        }
        return temp;
    }

//######################################################################################################################################################
    // DAO LOAD "ORDERS" BY "PERIOD OF TIME"
    // Returns list of all cars of Owner(ID).
//######################################################################################################################################################

    public static List<Order> loadOrders_PeriodStart(String dateS, String dateE) {
        String sql = "SELECT * FROM repair WHERE real_start_date>=? AND real_start_date<=?";
        List<Order> temp = new ArrayList<>();
        try {
            Connection conn = DbUtil.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, dateS);
            preparedStatement.setString(2, dateE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) temp.add(uploadOrder(resultSet));
            conn.close();
        } catch (SQLException e) {
            return null;
        }
        return temp;
    }
//######################################################################################################################################################
    // DAO ask SQL - extractet to ask sql question "sql" with "id" parameter and return list of matching "Order"
    // Returns list of matching objects "ORDER"
//######################################################################################################################################################

    private static List<Order> askSQL(int id, String sql) {
        List<Order> ordersRead = new ArrayList<Order>();
        try {
            Connection conn = DbUtil.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) ordersRead.add(uploadOrder(resultSet));
            conn.close();
        } catch (SQLException e) {
            return null;
        }
        return ordersRead;
    }

//######################################################################################################################################################
    // loads Object CAR with data from resultSet
    // returns O CAR or null id SQR exception occurs;
//######################################################################################################################################################

    private static Order uploadOrder (ResultSet resultSet) {
        Order loadedOrder = new Order();
        try {
            loadedOrder.setId(resultSet.getInt("id"));
            if (resultSet.getDate("plan_start_date") != null)
                loadedOrder.setPlanStartDate(resultSet.getDate("plan_start_date").toLocalDate());
            else loadedOrder.setPlanStartDate (null);
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
            loadedOrder.setNumOfHours(resultSet.getFloat("num_of_hours"));
            if (resultSet.getDate("real_end_date") != null)
                loadedOrder.setRealEndDate(resultSet.getDate("real_end_date").toLocalDate());
            else loadedOrder.setRealEndDate (null);

        } catch (SQLException e) {
            return null;
        }
        return loadedOrder;
    }

//######################################################################################################################################################


}
