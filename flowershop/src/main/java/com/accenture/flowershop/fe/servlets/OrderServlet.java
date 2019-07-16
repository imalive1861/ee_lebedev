package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.service.business.card.CardBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    @Autowired
    private CardBusinessService cardBusinessService;

    public OrderServlet(){
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String createOrder = request.getParameter("createOrder");

        if (createOrder != null) {System.out.println("Knopochka najata");}

        try {
            request.setAttribute("allSum", cardBusinessService.getAllSumPrice());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/view/order.jsp").forward(request, response);
    }
}
