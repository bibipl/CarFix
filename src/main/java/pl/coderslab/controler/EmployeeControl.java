package pl.coderslab.controler;


import pl.coderslab.model.Order;
import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Employee;
import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.model.Car;
import pl.coderslab.model.OrdCarEmpl;

import pl.coderslab.dao.CarDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EmployeeControl", urlPatterns ="/EmployeeControl")
public class EmployeeControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String emplId = request.getParameter("emplId");
        int eId=0;  // we asume - does not exist (create)
        try {
            eId = Integer.parseInt(emplId); // unless it exists (update)
        } catch (NumberFormatException e) {
           eId=0;
        }
        Employee newEmployee = EmployeeDao.loadEmployeeById(eId); // or real or NULL

         newEmployee.setName(request.getParameter("name"));
         newEmployee.setSurname(request.getParameter("surname"));
         newEmployee.setAddress(request.getParameter("address"));
         newEmployee.setPhone(request.getParameter("phone"));
         newEmployee.setNote(request.getParameter("note"));

        try {
            Float pricePerHour = Float.parseFloat(request.getParameter("hourPrice"));
            newEmployee.setHourPrice(pricePerHour);
        } catch (NumberFormatException e) {
            newEmployee.setHourPrice(0); // jeśli zły format to zero!
        }

        EmployeeDao.saveToDB(newEmployee); // if newEmployee.id=0 - new one if !=0 - old one to modify
        List<Employee> workers = EmployeeDao.loadAllEmployees();
        if (workers.size() != 0) {
            request.setAttribute("empl", workers);
            request.getRequestDispatcher("employees/employShowAll.jsp").forward(request, response);
        } else {
            response.getWriter().append("Nie ma nic do wyświetlenia");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String opt = request.getParameter("opt");
        switch (opt) {
            case "1": { // show all employees
                List<Employee> workers = new ArrayList<>();

                    workers = EmployeeDao.loadAllEmployees();
                    if (workers.size() != 0) {
                        request.setAttribute("empl", workers);
                        request.getRequestDispatcher("employees/employShowAll.jsp").forward(request, response);
                    } else {
                        response.getWriter().append("Nie ma nic do wyświetlenia");
                    }

                break;
            }
            case "2": { // add new employee
                request.getRequestDispatcher("employees/employAdd.jsp").forward(request, response);
                break;
            }
            case "3": { // show details by id
                String ident = request.getParameter("ident");
                Employee empl =new Employee();
                try {
                    empl = EmployeeDao.loadEmployeeById (Integer.parseInt(ident));
                } catch (NumberFormatException a) {
                    a.printStackTrace();
                }
                request.setAttribute("empl", empl);
                request.getRequestDispatcher("employees/employShowById.jsp").forward(request, response);
                break;
            }
            case "4": { // MODIFY EMPLOYEE
                String ident = request.getParameter("ident");
                Employee empl =new Employee();
                try {
                    empl = EmployeeDao.loadEmployeeById (Integer.parseInt(ident));
                } catch (NumberFormatException a) {
                    a.printStackTrace();
                }
                request.setAttribute("empl", empl);
                request.getRequestDispatcher("employees/employModif.jsp").forward(request, response);
                break;
            }case "5": { // DELETE 1 - are you sure?
                String ident = request.getParameter("ident");
                try {
                    Employee empl = EmployeeDao.loadEmployeeById (Integer.parseInt(ident));
                    request.setAttribute("empl", empl);
                    request.getRequestDispatcher("employees/employDelete.jsp").forward(request, response);
                }catch (NumberFormatException a) {
                    a.printStackTrace();
                }
                break;
            }case "6": {// DELETE 2 - ERASE
                String ident = request.getParameter("ident");
                try {
                    Employee empl = EmployeeDao.loadEmployeeById (Integer.parseInt(ident));
                    EmployeeDao.delete(empl);
                    List<Employee> workers = new ArrayList<>();
                        workers = EmployeeDao.loadAllEmployees();
                        if (workers.size() != 0) {
                            request.setAttribute("empl", workers);
                            request.getRequestDispatcher("employees/employShowAll.jsp").forward(request, response);
                        } else {
                            response.getWriter().append("Nie ma nic do wyświetlenia");
                        }
                }catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            }case "7": { // Show all ORDERS by EMPLOYEE status - working
                String ident = request.getParameter("ident"); // ident = empl id
                Employee empl =new Employee();
                try {
                    empl = EmployeeDao.loadEmployeeById (Integer.parseInt(ident));
                    List<OrdCarEmpl> ordCarEmpls = new ArrayList<>();
                    List<Order> orders = OrderDao.loadAllOrders_Empl(empl.getId());
                    for (Order ord : orders) {
                        // Here if we want all orders of emloyee just erase "i
                        if (ord.getStatus()==3) { // w naprawie
                            OrdCarEmpl hybrid = new OrdCarEmpl();
                            hybrid.setOrder(ord);
                            hybrid.setCar(CarDao.loadCarById(ord.getCarId()));
                            ordCarEmpls.add(hybrid);
                        }
                    }
                    request.setAttribute("ordCarEmpl", ordCarEmpls);
                    request.setAttribute("empl", empl);
                    request.getRequestDispatcher("orders/orderShowByEmpl.jsp").forward(request, response);
                } catch (NumberFormatException a) {
                    a.printStackTrace();
                }
                break;
            }case "8": { // Show all ORDERS by EMPLOYEE - status - no matter
                String ident = request.getParameter("ident"); // ident = empl id
                Employee empl =new Employee();
                try {
                    empl = EmployeeDao.loadEmployeeById (Integer.parseInt(ident));
                    List<OrdCarEmpl> ordCarEmpls = new ArrayList<>();
                    List<Order> orders = OrderDao.loadAllOrders_Empl(empl.getId());
                    for (Order ord : orders) {
                            OrdCarEmpl hybrid = new OrdCarEmpl();
                            hybrid.setOrder(ord);
                            hybrid.setCar(CarDao.loadCarById(ord.getCarId()));
                            ordCarEmpls.add(hybrid);
                    }
                    request.setAttribute("ordCarEmpl", ordCarEmpls);
                    request.setAttribute("empl", empl);
                    request.getRequestDispatcher("orders/orderShowByEmpl.jsp").forward(request, response);
                } catch (NumberFormatException a) {
                    a.printStackTrace();
                }
                break;
            }
        }
    }
}
