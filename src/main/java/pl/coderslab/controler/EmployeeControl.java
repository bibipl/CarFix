package pl.coderslab.controler;


import pl.coderslab.model.Employee;
import pl.coderslab.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EmployeeControl", urlPatterns ="/EmployeeControl")
public class EmployeeControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");

        response.getWriter().append("Cześć Jestem w POST");
        Employee newEmployee = new Employee();
        newEmployee.setName(request.getParameter("name"));
        newEmployee.setSurname(request.getParameter("surname"));
        newEmployee.setAddress(request.getParameter("address"));
        newEmployee.setPhone(request.getParameter("phone"));
        newEmployee.setNote(request.getParameter("note"));
        try {
            Float pricePErHour = Float.parseFloat(request.getParameter("hourPrice"));
            newEmployee.setHourPrice(pricePErHour);
        } catch (NumberFormatException e) {
            newEmployee.setHourPrice(0); // jeśli zły format to zero!
        }
        try {
            newEmployee.saveToDB(DbUtil.getConn());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String opt = request.getParameter("opt");
        switch (opt) {
            case "1": { // wyświetla listę pracowników
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
            case "2": {
                request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
                break;
            }
            case "3": {
                String ident = request.getParameter("ident");
                response.getWriter().append("Szczegóły pracownika o Identyfikatorze: " + ident);
                break;
            }
            case "4": {
                String ident = request.getParameter("ident");
                response.getWriter().append("Modyfikuj pracownika o Identyfikatorze: " + ident);
                break;
            }case "5": {
                String ident = request.getParameter("ident");
                response.getWriter().append("Usuń pracownika o Identyfikatorze: " + ident);
                break;
            }

        }
    }
}
