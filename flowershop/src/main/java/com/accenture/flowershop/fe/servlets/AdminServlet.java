package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.service.business.cart.CartBusinessService;
import com.accenture.flowershop.be.service.business.order.OrderBusinessService;
import com.accenture.flowershop.fe.dto.mappers.CartMapper;
import com.accenture.flowershop.fe.dto.mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

    @Autowired
    private OrderBusinessService orderBusinessService;
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartBusinessService cartBusinessService;
    @Autowired
    private CartMapper cartMapper;

    public AdminServlet() {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        closeOrder(request.getParameter("orderId"), request);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/view/admin.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    private void closeOrder(String orderId, HttpServletRequest request){
        if (orderId != null){
            orderBusinessService.close(Long.parseLong(orderId));
        }
        request.setAttribute("orderList", orderMapper.orderToOrderDtos(orderBusinessService.getAll()));
        request.setAttribute("cartList", cartMapper.cartToCartDtos(cartBusinessService.getAll()));
    }
}
