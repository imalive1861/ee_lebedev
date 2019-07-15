package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.fe.dto.FlowerDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    public OrderServlet(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Map<FlowerDTO, Integer> card = (Map<FlowerDTO, Integer>) request.getSession().getAttribute("userCard");


        request.getRequestDispatcher("/WEB-INF/view/order.jsp").forward(request, response);
    }
}
