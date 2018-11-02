package pl.coderslab.controler;

import pl.coderslab.dao.ClientDao;
import pl.coderslab.model.*;
import pl.coderslab.dao.CarDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CarControl", urlPatterns = "/CarControl")
public class CarControl extends HttpServlet {

//####################################################################################################################################
    // POST
//####################################################################################################################################

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String carId = request.getParameter("carId");
        String ownerId = request.getParameter("ownerId");
        int cId;
        int oId;
        try {
            cId = Integer.parseInt(carId);
        } catch (NumberFormatException e) {
            cId=0;
        }
        try {
            oId = Integer.parseInt(ownerId);
        } catch (NumberFormatException e) {
            oId=0;
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
        List<Car> cars = new ArrayList<>();
        if (oId == 0) {
            cars = CarDao.loadAllCars();
        } else {
            cars = CarDao.loadAllCars_User(oId);
        }
        Client cl = ClientDao.loadClientById(oId);
        request.setAttribute("cars", cars);
        request.setAttribute("cl", cl);
        request.getRequestDispatcher("cars/carShowByClient.jsp").forward(request, response);
    }

//####################################################################################################################################
    // GET
//####################################################################################################################################

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String opt = request.getParameter("opt");

        switch (opt) {
            case "1": { // SHOW ALL CARS
                ListAllCars(request, response);
                break;
            }
            case "2": { // ADD CAR
                String ident = request.getParameter("ident");
                int clId;
                int carId=0;
                try {
                    clId = Integer.parseInt(ident);
                } catch (NumberFormatException e) {
                    clId=0;
                }
                if (clId!=0) {
                    Client cl = ClientDao.loadClientById(clId);
                    request.setAttribute("carId", carId);
                    request.setAttribute("cl", cl);
                    request.getRequestDispatcher("cars/carAdd.jsp").forward(request, response);
                }
                List<Client> cl = ClientDao.loadAllClients();
                if (cl.size() != 0) {
                    request.setAttribute("cl", cl);
                    request.getRequestDispatcher("clients/clientShowAll.jsp").forward(request, response);
                } else {
                    response.getWriter().append("Nie ma nic do wyświetlenia");
                }
                break;
            }
            case "3": { // SHOW DETAILS
                String ident = request.getParameter("ident");
                Car car =new Car();
                Client cl = new Client();
                try {
                    int carId = Integer.parseInt(ident);
                    car = CarDao.loadCarById (carId);
                    cl = ClientDao.loadClientById(car.getOwnerId());
                } catch (NumberFormatException a) {
                    a.printStackTrace();
                }
                request.setAttribute("cars", car);
                request.setAttribute("cl", cl);
                request.getRequestDispatcher("cars/carShowById.jsp").forward(request, response);
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
                request.getRequestDispatcher("cars/carModif.jsp").forward(request, response);
                break;
            }case "5": { // DELETE1 - SHOW AND ASK
                String ident = request.getParameter("ident");
                try {
                    int carId = Integer.parseInt(ident);
                    Car car = CarDao.loadCarById (carId);
                    request.setAttribute("cars", car);
                    request.getRequestDispatcher("cars/carDelete.jsp").forward(request, response);
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
                                        request.getRequestDispatcher("cars/carShowAll.jsp").forward(request, response);
                }catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            }case "7": { // SHOW CARS BY CLIENT ID
                String ident = request.getParameter("ident");
                try {
                    int ownId = Integer.parseInt(ident);
                    List<Car> cars = CarDao.loadAllCars_User(ownId);
                    Client client = ClientDao.loadClientById(ownId);
                    request.setAttribute("cars", cars);
                    request.setAttribute("cl", client);
                    request.getRequestDispatcher("cars/carShowByClient.jsp").forward(request, response);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            }case "8": { // HISTORY BY CAR ID
                String ident = request.getParameter("ident");
                try {
                    int ownId = Integer.parseInt(ident);
                    List<Car> cars = CarDao.loadAllCars_User(ownId);
                    Client client = ClientDao.loadClientById(ownId);
                    request.setAttribute("cars", cars);
                    request.setAttribute("cl", client);
                    request.getRequestDispatcher("cars/carShowByClient.jsp").forward(request, response);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            }

        }
    }
//####################################################################################################################################
    // Extracted Method. Creates OrdCarEmpl {Car, Order, Employee} - to disply conviniently
//####################################################################################################################################

    private static List<OrClEmCa> CreateComboData(List<Car> cars) {
        List<OrClEmCa> orClEmCas = new ArrayList<>();
        try {
            for (Car car : cars) {  // for each car we pack kombo with equivalen car and employee
                OrClEmCa hybrid = new OrClEmCa();
                hybrid.setCar(car);
                hybrid.setClient(ClientDao.loadClientById(car.getOwnerId()));
                orClEmCas.add(hybrid);
            }
        } catch (NumberFormatException a) {
            a.printStackTrace();
        }
        return orClEmCas;
    }
//####################################################################################################################################
    // Extracted Method. Lists all cars.
//####################################################################################################################################

    private void ListAllCars(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<OrClEmCa> orClEmCas = new ArrayList<>(); // pobierzemy kombo dane
            List<Car> cars = CarDao.loadAllCars(); // najpierw zelecenia
            orClEmCas = CreateComboData(cars);  // load Combo data - order+equivalen car and employee
            request.setAttribute("orClEmCa", orClEmCas);
            request.getRequestDispatcher("cars/carShowAll.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }catch (IOException a) {
            a.printStackTrace();
        }
    }

//####################################################################################################################################
}
