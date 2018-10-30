package pl.coderslab.controler;

import pl.coderslab.model.Client;
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
        Client newClient = new Client();

        if (cId!=0) { // we want to modify and we need real id in newClient
            try {
                newClient = Client.loadClientById(DbUtil.getConn(), cId); // we want to get client with real id!=0
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        newClient.setName(request.getParameter("name"));
        newClient.setSurname(request.getParameter("surname"));
        newClient.setBirthDate(LocalDate.parse(request.getParameter("birthDate")));
        newClient.setPhone(request.getParameter("phone"));

        try {
            newClient.saveToDB(DbUtil.getConn()); // if newClient.id=0 - new one if !=0 - old one to modify
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            List<Client> clients = Client.loadAllClients(DbUtil.getConn());
            if (clients.size() != 0) {
                request.setAttribute("cl", clients);
                request.getRequestDispatcher("clientShowAll.jsp").forward(request, response);
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
            case "1": { // show all clients
                List<Client> clients = new ArrayList<>();
                try {
                    clients = Client.loadAllClients(DbUtil.getConn());
                    request.setAttribute("cl", clients);
                    request.getRequestDispatcher("clientShowAll.jsp").forward(request, response);
               } catch (SQLException e) {
                    e.printStackTrace();
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
                    cl = Client.loadClientById (DbUtil.getConn(), Integer.parseInt(ident));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NumberFormatException a) {
                    a.printStackTrace();
                }
                request.setAttribute("cl", cl);
                request.getRequestDispatcher("clientShowById.jsp").forward(request, response);
                break;
            }
            case "4": {
                String ident = request.getParameter("ident");
                Client cl =new Client();
                try {
                    cl = Client.loadClientById (DbUtil.getConn(), Integer.parseInt(ident));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NumberFormatException a) {
                    a.printStackTrace();
                }
                request.setAttribute("cl", cl);
                request.getRequestDispatcher("clientModif.jsp").forward(request, response);
                break;
            }case "5": {
                String ident = request.getParameter("ident");
                try {
                    Client cl = Client.loadClientById (DbUtil.getConn(), Integer.parseInt(ident));
                    request.setAttribute("cl", cl);
                    request.getRequestDispatcher("clientDelete.jsp").forward(request, response);
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
                    Client cl = Client.loadClientById (con, Integer.parseInt(ident));
                    cl.delete(con);
                    List<Client> clients = new ArrayList<>();
                    clients = Client.loadAllClients(con);
                        request.setAttribute("cl", clients);
                        request.getRequestDispatcher("clientShowAll.jsp").forward(request, response);
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
