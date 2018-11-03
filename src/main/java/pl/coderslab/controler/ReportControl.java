package pl.coderslab.controler;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Employee;
import pl.coderslab.model.Order;
import pl.coderslab.model.Report1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ReportControl", urlPatterns = "/ReportControl")
public class ReportControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String reportId = request.getParameter("repId");
        String start = request.getParameter("start");
        String end = request.getParameter("end");

        switch (reportId) {
            case "1": {
                List<Order> orders = OrderDao.loadOrders_PeriodStart(start, end);
                List<Employee> empl = EmployeeDao.loadAllEmployees();
                List<Report1> repOne = new ArrayList<>();
                for (Employee itemE : empl) {
                    Report1 temp = new Report1();
                    temp.setName(itemE.getName()+" "+itemE.getSurname());
                    Float sum = (float)0.0;
                    for (Order itemO : orders) {
                        if (itemE.getId()==itemO.getEmployeeId() && itemO!=null) {
                            sum+=itemO.getNumOfHours();
                        }
                    }
                    temp.setNumOfHours(sum);
                    repOne.add(temp);
                }
                request.setAttribute("start", start);
                request.setAttribute("end", end);
                request.setAttribute("repOne", repOne);
                request.getRequestDispatcher("reports/rep1.jsp").forward(request, response);
                break;
            }case "2": {
                List<Order> orders = OrderDao.loadOrders_PeriodEnd(start, end);
                Float revenues = (float)0;
                Float parts = (float)0;
                Float service = (float)0;
                Float profit = (float)0;
                for (Order itemO : orders) {
                   if (itemO.getValueServ() != null) revenues+=itemO.getValueServ();
                   if (itemO.getValueParts() != null) parts+=itemO.getValueParts();
                   if (itemO.getValueServ() != null && itemO.getNumOfHours() != null) service+=(itemO.getHourPrice()*itemO.getNumOfHours());
                }
                profit = revenues-parts-service;
                request.setAttribute("revenues", String.format("%.2f", revenues));
                request.setAttribute("parts", String.format("%.2f", parts));
                request.setAttribute("service", String.format("%.2f", service));
                request.setAttribute("profit", String.format("%.2f", profit));
                request.getRequestDispatcher("reports/reportProfit.jsp").forward(request, response);
                break;
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String opt = request.getParameter("opt");

        switch (opt) {
            case "1": { // Show all ORDERS
                LocalDate ldt = LocalDate.now();
                request.setAttribute("ldt", ldt);
                request.getRequestDispatcher("reports/menu.jsp").forward(request, response);
                break;
            }
        }
    }
}
