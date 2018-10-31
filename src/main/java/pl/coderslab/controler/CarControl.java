package pl.coderslab.controler;

import pl.coderslab.model.Car;
import pl.coderslab.dao.CarDao;
import pl.coderslab.model.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "CarControl", urlPatterns = "/CarControl")
public class CarControl extends HttpServlet {

//####################################################################################################################################
    // POST
//####################################################################################################################################

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String carId = request.getParameter("carId");
        int cId;
        try {
            cId = Integer.parseInt(carId);
        } catch (NumberFormatException e) {
            cId=0;
        }
        Car newCar= new Car(); // new car is empty (ADD)
        if (cId!=0) { // MODIFY
            newCar = CarDao.loadCarById(cId); // new car is ready to (MODIFY)
        }
        try {
            newCar.setModel(request.getParameter("model"));
            newCar.setBrand(request.getParameter("brand"));
            newCar.setYearProd(Integer.parseInt(request.getParameter("yearProd")));
            newCar.setRegistration(request.getParameter("registration"));
            newCar.setNextReview(LocalDate.parse(request.getParameter("nextReview")));
            if(request.getParameter("ownerId") != null) {
                newCar.setOwnerId(Integer.parseInt(request.getParameter("ownerId")));
            }else newCar.setOwnerId(0);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        CarDao.saveToDB(newCar); // PUT NEW OR MODIFIED to MYSQL

        List<Car> cars = CarDao.loadAllCars();
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("carShowAll.jsp").forward(request, response);
    }

//####################################################################################################################################
    // GET
//####################################################################################################################################

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String opt = request.getParameter("opt");

        switch (opt) {
            case "1": { // SHOW ALL CARS
                List<Car> cars = CarDao.loadAllCars();
                request.setAttribute("cars", cars);
                request.getRequestDispatcher("carShowAll.jsp").forward(request, response);
                break;
            }
            case "2": { // ADD CAR
                String ident = request.getParameter("ident");
                int clId=0;
                try {
                    clId = Integer.parseInt(ident);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if (clId!=0) {
                    request.setAttribute("clId", clId);
                    request.getRequestDispatcher("carAdd.jsp").forward(request, response);
                } else {
                    List<Car> cars = CarDao.loadAllCars();
                    request.setAttribute("cars", cars);
                    request.getRequestDispatcher("carShowAll.jsp").forward(request, response);
                }
                break;
            }
            case "3": { // SHOW DETAILS
                String ident = request.getParameter("ident");
                Car car =new Car();
                try {
                    int carId = Integer.parseInt(ident);
                    car = CarDao.loadCarById (carId);
                } catch (NumberFormatException a) {
                    a.printStackTrace();
                }
                request.setAttribute("cars", car);
                request.getRequestDispatcher("carShowById.jsp").forward(request, response);
                break;
            }
            case "4": { // MODIFY CAR
                String ident = request.getParameter("ident");
                Car car =new Car();
                try {
                    int carId = Integer.parseInt(ident);
                    car = CarDao.loadCarById (carId);
                } catch (NumberFormatException a) {
                    a.printStackTrace();
                }
                request.setAttribute("cars", car);
                request.getRequestDispatcher("carModif.jsp").forward(request, response);
                break;
            }case "5": { // DELETE1 - SHOW AND ASK
                String ident = request.getParameter("ident");
                try {
                    int carId = Integer.parseInt(ident);
                    Car car = CarDao.loadCarById (carId);
                    request.setAttribute("cars", car);
                    request.getRequestDispatcher("carDelete.jsp").forward(request, response);
                }catch (NumberFormatException a) {
                    a.printStackTrace();
                }
                break;
            }case "6": { // DELETE2 - ERASE
                String ident = request.getParameter("ident");
                try {
                    int carId = Integer.parseInt(ident);
                    Car car = CarDao.loadCarById (carId);
                    boolean deleted =CarDao.delete(car);
                    List<Car> cars = CarDao.loadAllCars();
                    request.setAttribute("cars", cars);
                    request.setAttribute("deleted", deleted);// if false we will display "could not remove"
                                        request.getRequestDispatcher("carShowAll.jsp").forward(request, response);
                }catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            }case "7": { // SHOW CARS BY CLIENT ID
                String ident = request.getParameter("ident");
                try {
                    int ownId = Integer.parseInt(ident);
                    List<Car> cars = CarDao.loadAllCars_User(ownId);
                    request.setAttribute("cars", cars);
                    request.getRequestDispatcher("carShowAll.jsp").forward(request, response);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
//####################################################################################################################################
}
