package pl.coderslab.controler;

import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
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
            if (request.getParameter("planStartDate")!=null)
                newOrder.setPlanStartDate(LocalDate.parse(request.getParameter("planStartDate")));
            if (request.getParameter("realStartDate")!=null)
                newOrder.setRealStartDate(LocalDate.parse(request.getParameter("realStartDate")));
            if (request.getParameter("employeeId")!=null)
                newOrder.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
            newOrder.setProblemDescript(request.getParameter("problemDescript"));
            newOrder.setFixDescript(request.getParameter("fixDescript"));

            if (request.getParameter("status")!=null)
                newOrder.setStatus(Integer.parseInt(request.getParameter("status")));
            if (request.getParameter("carId")!=null)
                newOrder.setCarId(Integer.parseInt(request.getParameter("carId")));
String temp=request.getParameter("valueServ");///test
            if (request.getParameter("valueServ")!=null)
                newOrder.setValueServ(Float.parseFloat(request.getParameter("valueServ")));
            if (request.getParameter("valueParts")!=null)
                newOrder.setValueParts(Float.parseFloat(request.getParameter("valueParts")));
            if (request.getParameter("hourPrice")!=null)
                newOrder.setHourPrice(Float.parseFloat(request.getParameter("hourPrice")));
            if (request.getParameter("numOfHours")!=null)
                newOrder.setStatus(Integer.parseInt(request.getParameter("numOfHours")));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        OrderDao.saveToDB(newOrder); // PUT "NEW" OR "MODIFIED" to MYSQL

        List<Order> orders = OrderDao.loadAllOrders();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("orderShowAll.jsp").forward(request, response);
    }

//####################################################################################################################################
    // GET
//####################################################################################################################################

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String opt = request.getParameter("opt");

        switch (opt) {
            case "1": { // SHOW ALL "ORDERS"
                List<Order> orders = OrderDao.loadAllOrders();
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("orderShowAll.jsp").forward(request, response);
                break;
            }
            case "2": { // ADD "ORDER"

                    request.getRequestDispatcher("orderAdd.jsp").forward(request, response);

                break;
            }
            case "3": { // SHOW DETAILS
                String ident = request.getParameter("ident");
                Order order =new Order();
                try {
                    int orderId = Integer.parseInt(ident);
                    order = OrderDao.loadOrderById (orderId);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                request.setAttribute("orders", order);
                request.getRequestDispatcher("orderShowById.jsp").forward(request, response);
                break;
            }
            case "4": { // MODIFY ORDER
                String ident = request.getParameter("ident");
                Order order =new Order();
                try {
                    int orderId = Integer.parseInt(ident);
                    order = OrderDao.loadOrderById (orderId);
                } catch (NumberFormatException a) {
                    a.printStackTrace();
                }
                request.setAttribute("orders", order);
                request.getRequestDispatcher("orderModif.jsp").forward(request, response);
                break;
            }case "5": { // DELETE1 - SHOW AND ASK
                String ident = request.getParameter("ident");
                try {
                    int orderId = Integer.parseInt(ident);
                    Order order = OrderDao.loadOrderById (orderId);
                    request.setAttribute("orders", order);
                    request.getRequestDispatcher("orderDelete.jsp").forward(request, response);
                }catch (NumberFormatException a) {
                    a.printStackTrace();
                }
                break;
            }case "6": { // DELETE2 - ERASE
                String ident = request.getParameter("ident");
                try {
                    int orderId = Integer.parseInt(ident);
                    Order order = OrderDao.loadOrderById (orderId);
                    boolean deleted =OrderDao.delete(order);
                    List<Order> orders = OrderDao.loadAllOrders();
                    request.setAttribute("orders", orders);
                    request.setAttribute("deleted", deleted);// if false we will display "could not remove"
                    request.getRequestDispatcher("orderShowAll.jsp").forward(request, response);
                }catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            }case "7": { // SHOW ORDERS BY "CAR" ID
                String ident = request.getParameter("ident");
                try {
                    int carId = Integer.parseInt(ident);
                    List<Order> orders = OrderDao.loadAllOrders_Car(carId);
                    request.setAttribute("orders", orders);
                    request.getRequestDispatcher("orderShowAll.jsp").forward(request, response);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            }case "8": { // SHOW ORDERS BY "EMPLOYEE" ID
                String ident = request.getParameter("ident");
                try {
                    int emplId = Integer.parseInt(ident);
                    List<Order> orders = OrderDao.loadAllOrders_Empl(emplId);
                    request.setAttribute("orders", orders);
                    request.getRequestDispatcher("orderShowAll.jsp").forward(request, response);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

//####################################################################################################################################
}

