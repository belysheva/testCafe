package ru.cafe.web;

import ru.cafe.service.Service;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/getTrafficAvg")
public class TrafficServlet extends HttpServlet {
//TODO handle exceptions properly
    Service service;
    {
        try {
            service = new Service();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setAttribute("traffic", service.trafficToJSON(service.calcAvg()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/traffic.jsp").forward(request, response);

    }



}

