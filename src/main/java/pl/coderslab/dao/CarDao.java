package pl.coderslab.dao;

import pl.coderslab.model.Car;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDao {

//######################################################################################################################################################
//    DAO CCREATE/UPDATE - id==0 :CREATE; id !=0: UPDATE
// SaveToDb - Static. IN connection and object car. OUT -1 - error, otherwise id of the car
//######################################################################################################################################################

    public static int saveToDB(Car car) {
        if (car.getId() == 0) {
            try {
                Connection conn = DbUtil.getConn();
                String sql = "INSERT INTO car(model,brand,yearprod,registration,nextreview,owner_id) VALUES (?, ?, ?, ?, ?, ?)";
                String[] generatedColumns = {"ID"};
                PreparedStatement preparedStatement = conn.prepareStatement(sql, generatedColumns);
                preparedStatement.setString(1, car.getModel());
                preparedStatement.setString(2, car.getBrand());
                preparedStatement.setInt(3, car.getYearProd());
                preparedStatement.setString(4, car.getRegistration());
                preparedStatement.setString(5, java.sql.Date.valueOf(car.getNextReview()).toString());
                preparedStatement.setInt(6, car.getOwnerId());
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
            String sql = "UPDATE car SET model=?,brand=?,yearprod=?,registration=?,nextreview=?,owner_id=? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setInt(3, car.getYearProd());
            preparedStatement.setString(4, car.getRegistration());
            preparedStatement.setString(5, java.sql.Date.valueOf(car.getNextReview()).toString());
            preparedStatement.setInt(6, car.getOwnerId());
            preparedStatement.setInt(7, car.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return -1;
        }
        return car.getId();
    } // End SaveToDB

//######################################################################################################################################################
    // DAO READ 1 car by id
    // RETURNS OBJECT CAR or OBJECT NULL;
//######################################################################################################################################################

    public static Car loadCarById(int id) {
        String sql = "SELECT * FROM car where id=?";
        Car loadedCar = new Car();
        try {
            Connection conn = DbUtil.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) loadedCar = (uploadCar(resultSet));

        } catch (SQLException e) {
            return null;
        }
        return loadedCar;
    }

//######################################################################################################################################################
    // READ ALL CARS
    // Returns Array List of Object CAR or NULL
//######################################################################################################################################################

    public static List<Car> loadAllCars() {
        ArrayList<Car> cars = new ArrayList<Car>();
        String sql = "SELECT * FROM car";
        try {
            Connection conn = DbUtil.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) cars.add(uploadCar(resultSet));

        } catch (SQLException e) {
            return null;
        }
        return cars;
    }

//######################################################################################################################################################
    // DAO DELETE
    // Deletes CAR and returns true (deleted) or false (delete unsuccessfull)
//######################################################################################################################################################

    public static boolean delete(Car car) {
        if (car.getId() != 0) {
            String sql = "DELETE FROM car WHERE id=?";
            try {
                Connection conn = DbUtil.getConn();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, car.getId());
                preparedStatement.executeUpdate();
                car.setId(0);
            } catch (SQLException e) {
                return false;
            }
        }
        return true;
    }

//######################################################################################################################################################
    // DAO LOAD CARS BY OWNER ID
    // Returns list of all cars of Owner(ID).
//######################################################################################################################################################

    public static List<Car> loadAllCars_User(int ownId) {
        ArrayList<Car> cars = new ArrayList<Car>();
        String sql = "SELECT * FROM car WHERE owner_id=?";
        try {
            Connection conn = DbUtil.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, ownId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) cars.add(uploadCar(resultSet));

        } catch (SQLException e) {
            return null;
        }
        return cars;
    }

//######################################################################################################################################################
    // loads Object CAR with data from resultSet
    // returns O CAR or null id SQR exception occurs;
//######################################################################################################################################################

    private static Car uploadCar (ResultSet resultSet) {
        Car loadedCar = new Car();
        try {
            loadedCar.setId(resultSet.getInt("id"));
            loadedCar.setModel(resultSet.getString("model"));
            loadedCar.setBrand(resultSet.getString("brand"));
            loadedCar.setYearProd(resultSet.getInt("yearprod"));
            loadedCar.setRegistration(resultSet.getString("registration"));
            loadedCar.setNextReview(resultSet.getDate("nextreview").toLocalDate());
            loadedCar.setOwnerId(resultSet.getInt("owner_id"));
        } catch (SQLException e) {
            return null;
        }
        return loadedCar;
    }

//######################################################################################################################################################
}
