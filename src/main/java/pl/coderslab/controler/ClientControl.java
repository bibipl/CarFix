package pl.coderslab.controler;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.model.Client;
import pl.coderslab.dao.ClientDao;
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
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "ClientControl", urlPatterns = "/ClientControl")
public class ClientControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String clId = request.getParameter("clId");
        int cId=0;
        try {
            cId = Integer.parseInt(clId);
        } catch (NumberFormatException e) {
            cId=0;
        }
        Client newClient = ClientDao.loadClientById(cId); // or real or NULL

        newClient.setName(request.getParameter("name"));
        newClient.setSurname(request.getParameter("surname"));
        newClient.setBirthDate(LocalDate.parse(request.getParameter("birthDate").toString()));
        newClient.setPhone(request.getParameter("phone"));

        ClientDao.saveToDB(newClient); // if newClient.id=0 - new one if !=0 - old one to modify

        List<Client> cl = ClientDao.loadAllClients();
        if (cl.size() != 0) {
            request.setAttribute("cl", cl);
            request.getRequestDispatcher("clientShowAll.jsp").forward(request, response);
        } else {
            response.getWriter().append("Nie ma nic do wyświetlenia");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String opt = request.getParameter("opt");
        switch (opt) {
            case "1": { // show all clients
                List<Client> cl = new ArrayList<>();

                cl = ClientDao.loadAllClients();
                if (cl.size() != 0) {
                    request.setAttribute("cl", cl);
                    request.getRequestDispatcher("clientShowAll.jsp").forward(request, response);
                } else {
                    response.getWriter().append("Nie ma nic do wyświetlenia");
                }
                break;
            }
            case "2": { // add new client
                request.getRequestDispatcher("clientAdd.jsp").forward(request, response);
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
                request.getRequestDispatcher("clientShowById.jsp").forward(request, response);
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
                request.getRequestDispatcher("clientModif.jsp").forward(request, response);
                break;
            }case "5": {
                String ident = request.getParameter("ident");
                try {
                    Client cl = ClientDao.loadClientById (Integer.parseInt(ident));
                    request.setAttribute("cl", cl);
                    request.getRequestDispatcher("clientDelete.jsp").forward(request, response);
                }catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            }case "6": {
                String ident = request.getParameter("ident");
                try {
                    Client cl = ClientDao.loadClientById (Integer.parseInt(ident));
                    ClientDao.delete(cl);
                    List<Client> clients = new ArrayList<>();
                    clients = ClientDao.loadAllClients();
                        request.setAttribute("cl", clients);
                        request.getRequestDispatcher("clientShowAll.jsp").forward(request, response);
                }catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
