package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.service.business.card.CardBusinessService;
import com.accenture.flowershop.be.service.business.card.CardService;
import com.accenture.flowershop.be.service.business.order.OrderBusinessService;
import com.accenture.flowershop.be.utils.SessionUtils;
import com.accenture.flowershop.fe.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardBusinessService cardBusinessService;

    @Autowired
    private OrderBusinessService orderBusinessService;

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
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String createOrder = request.getParameter("createOrder");
        String cleanCard = request.getParameter("cleanCard");
        boolean hasError = false;
        String errorString = null;
        SessionUtils.storeAllSumUserCard(session,
                cardService.getAllSumPrice(SessionUtils.getLoginedUser(session).getSale()));


        if (createOrder != null) {
            OrderDTO orderDTO = orderBusinessService.openOrder();
            if (cardBusinessService.saveCardToOrder(
                    orderDTO,
                    SessionUtils.getAllSum(session),
                    SessionUtils.getUserCard(session),
                    SessionUtils.getLoginedUser(session))) {

                cardService.clear();
                response.sendRedirect(request.getContextPath() + "/customer");
            } else {
                hasError = true;
                errorString = "Need more gold!";
            }
        } else if (cleanCard != null){
            cardService.clear();
            hasError = true;
            errorString = "Card clean right now!";
        } else {
            request.getRequestDispatcher("/WEB-INF/view/order.jsp").forward(request, response);
        }

        if (hasError) {
            request.setAttribute("errorString", errorString);
            request.getRequestDispatcher("/WEB-INF/view/order.jsp").forward(request, response);
        }
    }
}
