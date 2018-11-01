package pl.coderslab.controler;


import pl.coderslab.model.Employee;
import pl.coderslab.dao.EmployeeDao;
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
            request.getRequestDispatcher("employShowAll.jsp").forward(request, response);
        } else {
            response.getWriter().append("Nie ma nic do wyświetlenia");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String opt = request.getParameter("opt");
        switch (opt) {
            case "1": { // show all employees
                List<Employee> workers = new ArrayList<>();

                    workers = EmployeeDao.loadAllEmployees();
                    if (workers.size() != 0) {
                        request.setAttribute("empl", workers);
                        request.getRequestDispatcher("employShowAll.jsp").forward(request, response);
                    } else {
                        response.getWriter().append("Nie ma nic do wyświetlenia");
                    }

                break;
            }
            case "2": { // add new employee
                request.getRequestDispatcher("employAdd.jsp").forward(request, response);
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
                request.getRequestDispatcher("employShowById.jsp").forward(request, response);
                break;
            }
            case "4": {
                String ident = request.getParameter("ident");
                Employee empl =new Employee();
                try {
                    empl = EmployeeDao.loadEmployeeById (Integer.parseInt(ident));
                } catch (NumberFormatException a) {
                    a.printStackTrace();
                }
                request.setAttribute("empl", empl);
                request.getRequestDispatcher("employModif.jsp").forward(request, response);
                break;
            }case "5": {
                String ident = request.getParameter("ident");
                try {
                    Employee empl = EmployeeDao.loadEmployeeById (Integer.parseInt(ident));
                    request.setAttribute("empl", empl);
                    request.getRequestDispatcher("employDelete.jsp").forward(request, response);
                }catch (NumberFormatException a) {
                    a.printStackTrace();
                }
                break;
            }case "6": {
                String ident = request.getParameter("ident");
                try {
                    Employee empl = EmployeeDao.loadEmployeeById (Integer.parseInt(ident));
                    EmployeeDao.delete(empl);
                    List<Employee> workers = new ArrayList<>();
                        workers = EmployeeDao.loadAllEmployees();
                        if (workers.size() != 0) {
                            request.setAttribute("empl", workers);
                            request.getRequestDispatcher("employShowAll.jsp").forward(request, response);
                        } else {
                            response.getWriter().append("Nie ma nic do wyświetlenia");
                        }
                }catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
