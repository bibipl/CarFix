package pl.coderslab.controler;

import pl.coderslab.dao.CarDao;
import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Car;
import pl.coderslab.model.Employee;
import pl.coderslab.model.OrdCarEmpl;
import pl.coderslab.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderControl", urlPatterns = "/OrderControl")
public class OrderControl extends HttpServlet {

//####################################################################################################################################
    // POST
//####################################################################################################################################

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String orderId = request.getParameter("orderId");
        int ordId;
        try {
            ordId = Integer.parseInt(orderId);
        } catch (NumberFormatException e) {
            ordId=0;
        }
        Order newOrder= new Order(); // new order is empty (ADD)
        if (ordId!=0) { // MODIFY
            newOrder = OrderDao.loadOrderById(ordId); // new order is ready to (MODIFY)
        }
        try {
            if (request.getParameter("planStartDate")!="" && request.getParameter("planStartDate")!=null)
                newOrder.setPlanStartDate(LocalDate.parse(request.getParameter("planStartDate")));
            if (request.getParameter("status") != null) {
                newOrder.setStatus(Integer.parseInt(request.getParameter("status")));
                switch (newOrder.getStatus()) {
                    case 1: {
                        if (newOrder.getPlanStartDate()==null)
                            newOrder.setPlanStartDate(LocalDate.now());
                        newOrder.setRealStartDate(null);
                        newOrder.setRealEndDate(null);
                        break;
                    }
                    case 2: {
                        if (newOrder.getPlanStartDate()==null)
                            newOrder.setPlanStartDate(LocalDate.now());
                        newOrder.setRealStartDate(null);
                        newOrder.setRealEndDate(null);
                        break;
                    }
                    case 3: {
                        if (newOrder.getPlanStartDate()==null)
                            newOrder.setPlanStartDate(LocalDate.now());
                        if (newOrder.getRealStartDate()==null)
                            newOrder.setRealStartDate(LocalDate.now());
                        newOrder.setRealEndDate(null);
                        break;
                    }case 4: {
                        if (newOrder.getPlanStartDate()==null)
                            newOrder.setPlanStartDate(LocalDate.now());
                        if (newOrder.getRealStartDate()==null)
                            newOrder.setRealStartDate(LocalDate.now());
                        if (newOrder.getRealEndDate()==null)
                            newOrder.setRealEndDate(LocalDate.now());
                        break;
                    }case 5: {
                        newOrder.setPlanStartDate(null);
                        newOrder.setRealStartDate(null);
                        newOrder.setRealEndDate(null);
                        break;
                    }
                }
            }
            if (request.getParameter("employeeId")!=null)
                newOrder.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
            newOrder.setProblemDescript(request.getParameter("problemDescript"));
            newOrder.setFixDescript(request.getParameter("fixDescript"));

            if (request.getParameter("carId")!=null)
                newOrder.setCarId(Integer.parseInt(request.getParameter("carId")));
            if (request.getParameter("valueServ")!=null)
                newOrder.setValueServ(Float.parseFloat(request.getParameter("valueServ")));
            if (request.getParameter("valueParts")!=null)
                newOrder.setValueParts(Float.parseFloat(request.getParameter("valueParts")));

            newOrder.setHourPrice(EmployeeDao.loadEmployeeById(newOrder.getEmployeeId()).getHourPrice());

            if (request.getParameter("numOfHours")!=null)
                newOrder.setNumOfHours(Float.parseFloat(request.getParameter("numOfHours")));
            if ((newOrder.getValueParts() != null) && (newOrder.getHourPrice() != null) && (newOrder.getNumOfHours() != null))
                newOrder.setValueServ(newOrder.getValueParts()+newOrder.getHourPrice()*newOrder.getNumOfHours());
            else newOrder.setValueServ((float)0);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        OrderDao.saveToDB(newOrder); // PUT "NEW" OR "MODIFIED" to MYSQL

        ListAllOrders(request, response);
    }

//####################################################################################################################################
    // GET
//####################################################################################################################################

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String opt = request.getParameter("opt");

        switch (opt) {
            case "1": { // Show all ORDERS
                ListAllOrders(request, response);
                break;

            }case "2": { // ADD "ORDER"
                List<Employee> employee = EmployeeDao.loadAllEmployees();
                List<Car> car = CarDao.loadAllCars();
                request.setAttribute("cars", car);
                request.setAttribute("employees", employee);
                request.getRequestDispatcher("orders/orderAdd.jsp").forward(request, response);
                break;

            } case "3": { // SHOW DETAILS
                String ident = request.getParameter("ident");
                Order order =new Order();
                try {
                    int orderId = Integer.parseInt(ident);
                    order = OrderDao.loadOrderById (orderId);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                Car car = CarDao.loadCarById(order.getCarId());
                Employee employee = EmployeeDao.loadEmployeeById(order.getEmployeeId());
                request.setAttribute("orders", order);
                request.setAttribute("cars", car);
                request.setAttribute("employees", employee);
                request.getRequestDispatcher("orders/orderShowById.jsp").forward(request, response);
                break;

            }case "4": { // MODIFY ORDER
                String ident = request.getParameter("ident");
                Order order =new Order();
                try {
                    int orderId = Integer.parseInt(ident);
                    order = OrderDao.loadOrderById (orderId);
                } catch (NumberFormatException a) {
                    a.printStackTrace();
                }
                List<Employee> employee = EmployeeDao.loadAllEmployees();
                List<Car> car = CarDao.loadAllCars();
                request.setAttribute("orders", order);
                request.setAttribute("cars", car);
                request.setAttribute("employees", employee);
                request.getRequestDispatcher("orders/orderModif.jsp").forward(request, response);
                break;

            }case "5": { // DELETE1 - SHOW AND ASK
                String ident = request.getParameter("ident");
                Order order = new Order();
                try {
                    int orderId = Integer.parseInt(ident);
                    order = OrderDao.loadOrderById (orderId);
                }catch (NumberFormatException a) {
                    a.printStackTrace();
                }
                Car car = CarDao.loadCarById(order.getCarId());
                Employee employee = EmployeeDao.loadEmployeeById(order.getEmployeeId());
                request.setAttribute("orders", order);
                request.setAttribute("cars", car);
                request.setAttribute("employees", employee);
                request.getRequestDispatcher("orders/orderDelete.jsp").forward(request, response);
                break;

            }case "6": { // DELETE2 - ERASE
                String ident = request.getParameter("ident");
                try {
                    int orderId = Integer.parseInt(ident);
                    Order order = OrderDao.loadOrderById (orderId);
                    OrderDao.delete(order);
                }catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                ListAllOrders(request, response);
                break;
            }
        }
    }

//####################################################################################################################################
    // Extracted Method. Creates OrdCarEmpl {Car, Order, Employee} - to disply conviniently
//####################################################################################################################################

    private static List<OrdCarEmpl> CreateComboData(List<Order> orders) {
    List<OrdCarEmpl> ordCarEmpls = new ArrayList<>();
    try {
        for (Order ord : orders) {  // for each order we pack kobo with equivalen car and employee
            OrdCarEmpl hybrid = new OrdCarEmpl();
            hybrid.setOrder(ord);
            hybrid.setCar(CarDao.loadCarById(ord.getCarId()));
            hybrid.setEmployee(EmployeeDao.loadEmployeeById(ord.getEmployeeId()));
            ordCarEmpls.add(hybrid);
        }
    } catch (NumberFormatException a) {
        a.printStackTrace();
    }
    return ordCarEmpls;
}
//####################################################################################################################################
    // Extracted Method. Lists all orders.
//####################################################################################################################################

    private void ListAllOrders(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<OrdCarEmpl> ordCarEmpls = new ArrayList<>(); // pobierzemy kombo dane
            List<Order> orders = OrderDao.loadAllOrders(); // najpierw zelecenia
            ordCarEmpls = CreateComboData(orders);  // load Combo data - order+equivalen car and employee
            request.setAttribute("ordCarEmpl", ordCarEmpls);
            request.getRequestDispatcher("orders/orderShowAll.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }catch (IOException a) {
            a.printStackTrace();
        }
    }

//####################################################################################################################################

}

