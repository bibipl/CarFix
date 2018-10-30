package pl.coderslab.controler;


import pl.coderslab.model.Employee;
import pl.coderslab.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EmployeeControl", urlPatterns ="/EmployeeControl")
public class EmployeeControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        Employee newEmployee = new Employee();
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
        try {
            newEmployee.saveToDB(DbUtil.getConn());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            List<Employee> workers = Employee.loadAllEmployees(DbUtil.getConn());
            if (workers.size() != 0) {
                request.setAttribute("empl", workers);
                request.getRequestDispatcher("showEmployees.jsp").forward(request, response);
            } else {
                response.getWriter().append("Nie ma nic do wyświetlenia");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String opt = request.getParameter("opt");
        switch (opt) {
            case "1": { // show all employees
                List<Employee> workers = new ArrayList<>();
                try {
                    workers = Employee.loadAllEmployees(DbUtil.getConn());
                    if (workers.size() != 0) {
                        request.setAttribute("empl", workers);
                        request.getRequestDispatcher("showEmployees.jsp").forward(request, response);
                    } else {
                        response.getWriter().append("Nie ma nic do wyświetlenia");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "2": { // add new employee
                request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
                break;
            }
            case "3": { // show details by id
                String ident = request.getParameter("ident");
                Employee empl =new Employee();
                try {
                    empl = Employee.loadEmployeeById (DbUtil.getConn(), Integer.parseInt(ident));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NumberFormatException a) {
                    a.printStackTrace();
                }
                request.setAttribute("empl", empl);
                request.getRequestDispatcher("showEmplById.jsp").forward(request, response);
                break;
            }
            case "4": {
                String ident = request.getParameter("ident");
                response.getWriter().append("Modyfikuj pracownika o Identyfikatorze: " + ident);
                // wyświetlić formularz z wpisanymi wartościami domyślnymi
                // Formularz wysłac do POSTA
                // save to DB z POSTA ma już 2 opcje.
                // jeśli id=0  to dopisuje nowy
                // jeśli id!+0 to modyfikuje.
                // trzeba przesać do POSTA ident!! jak to zrobić bez formularza ?


                break;
            }case "5": {
                String ident = request.getParameter("ident");
                try {
                    Employee empl = Employee.loadEmployeeById (DbUtil.getConn(), Integer.parseInt(ident));
                    request.setAttribute("empl", empl);
                    request.getRequestDispatcher("employDeleteId.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }catch (NumberFormatException a) {
                    a.printStackTrace();
                }
                break;
            }case "6": {
                String ident = request.getParameter("ident");
                try {
                    Connection con = DbUtil.getConn();
                    Employee empl = Employee.loadEmployeeById (con, Integer.parseInt(ident));
                    empl.delete(con);
                    List<Employee> workers = new ArrayList<>();
                        workers = Employee.loadAllEmployees(con);
                        if (workers.size() != 0) {
                            request.setAttribute("empl", workers);
                            request.getRequestDispatcher("showEmployees.jsp").forward(request, response);
                        } else {
                            response.getWriter().append("Nie ma nic do wyświetlenia");
                        }
                } catch (SQLException e) {
                    e.printStackTrace();
                }catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
