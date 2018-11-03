package pl.coderslab.controler;

import jdk.nashorn.internal.ir.IfNode;
import pl.coderslab.model.Client;
import pl.coderslab.dao.ClientDao;
import pl.coderslab.model.Car;
import pl.coderslab.dao.CarDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "ClientControl", urlPatterns = "/ClientControl")
public class ClientControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String clId = request.getParameter("clId");
        String src = request.getParameter("src");
        String birth =request.getParameter("birthDate");
        if (src != null && src!="") { // search client
            List<Client> clients = ClientDao.loadClientsByName(src);
            request.setAttribute("cl", clients);
            request.getRequestDispatcher("clients/clientShowAll.jsp").forward(request, response);
        } else {
            int cId = 0;
            try {
                cId = Integer.parseInt(clId);
            } catch (NumberFormatException e) {
                cId = 0;
            }
            Client newClient = ClientDao.loadClientById(cId); // or real or NULL

            newClient.setName(request.getParameter("name"));
            newClient.setSurname(request.getParameter("surname"));
            try {
                newClient.setBirthDate(LocalDate.parse(request.getParameter("birthDate").toString()));
            } catch (DateTimeParseException e) {
                // null allowed
            }
            newClient.setPhone(request.getParameter("phone"));

            ClientDao.saveToDB(newClient); // if newClient.id=0 - new one if !=0 - old one to modify

            List<Client> cl = ClientDao.loadAllClients();
            if (cl.size() != 0) {
                request.setAttribute("cl", cl);
                request.getRequestDispatcher("clients/clientShowAll.jsp").forward(request, response);
            } else {
                response.getWriter().append("Nie ma nic do wyświetlenia");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String opt = request.getParameter("opt");
        switch (opt) {
            case "1": { // show all clients
                List<Client> cl = ClientDao.loadAllClients();
                if (cl.size() != 0) {
                    request.setAttribute("cl", cl);
                    request.getRequestDispatcher("clients/clientShowAll.jsp").forward(request, response);
                } else {
                    response.getWriter().append("Nie ma nic do wyświetlenia");
                }
                break;
            }
            case "2": { // add new client
                request.getRequestDispatcher("clients/clientAdd.jsp").forward(request, response);
                break;
            }
            case "3": { // show details by id
                String ident = request.getParameter("ident");
                Client cl =new Client();
                try {
                    cl = ClientDao.loadClientById (Integer.parseInt(ident));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                request.setAttribute("cl", cl);
                request.getRequestDispatcher("clients/clientShowById.jsp").forward(request, response);
                break;
            }
            case "4": {
                String ident = request.getParameter("ident");
                Client cl =new Client();
                try {
                    cl = ClientDao.loadClientById (Integer.parseInt(ident));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                request.setAttribute("cl", cl);
                request.getRequestDispatcher("clients/clientModif.jsp").forward(request, response);
                break;
            }case "5": {
                String ident = request.getParameter("ident");
                try {
                    Client cl = ClientDao.loadClientById (Integer.parseInt(ident));
                    request.setAttribute("cl", cl);
                    request.getRequestDispatcher("clients/clientDelete.jsp").forward(request, response);
                }catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            }case "6": { // DELETE Client
                String ident = request.getParameter("ident");
                try {
                    Client cl = ClientDao.loadClientById (Integer.parseInt(ident));
                    ClientDao.delete(cl);
                    List<Client> clients = new ArrayList<>();
                    clients = ClientDao.loadAllClients();
                        request.setAttribute("cl", clients);
                        request.getRequestDispatcher("clients/clientShowAll.jsp").forward(request, response);
                }catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            }case "7": {// Show CLIENTS Cars
                String ident = request.getParameter("ident");
                try {
                    List<Car> cars = CarDao.loadAllCars_User(Integer.parseInt(ident));
                    Client cl = ClientDao.loadClientById(Integer.parseInt(ident));
                    request.setAttribute("cl", cl);
                    request.setAttribute("cars", cars);
                    request.getRequestDispatcher("cars/carShowByClient.jsp").forward(request, response);
                }catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            }case "8": {// FIND the  client
                request.getRequestDispatcher("cars/carShowByClient.jsp").forward(request, response);

                break;
            }
        }
    }
}
