package ru.cafe.web;

import ru.cafe.service.ServiceImpl;
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
    ServiceImpl serviceImpl;
    {
        try {
            serviceImpl = new ServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setAttribute("traffic", serviceImpl.getAvgTraffic(serviceImpl.calcAvg()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/traffic.jsp").forward(request, response);

    }



}

