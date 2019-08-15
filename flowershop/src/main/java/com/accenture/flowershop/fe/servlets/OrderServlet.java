package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.fe.servicedto.cartdto.CartService;
import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.be.utils.SessionUtils;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.dozer.Mapper;
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
import java.math.BigDecimal;

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserBusinessService userBusinessService;

    @Autowired
    private Mapper mapper;

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

        boolean hasError = true;
        String errorString = null;

        UserDTO userDTO = SessionUtils.getLoginedUser(session);

        Order order = mapper.map(SessionUtils.getUserCart(session),Order.class);

        BigDecimal allSum = cartService.getAllSumPrice(userDTO.getDiscount(), userDTO.getLogin());
        request.setAttribute("allSum", allSum);

        if (request.getParameter("createOrder") != null) {
            User user = mapper.map(userDTO, User.class);
            if (userBusinessService.checkCash(user, allSum)) {
                userBusinessService.createOrder(user, allSum, order);
                hasError = false;
            } else {
                errorString = "Need more gold!";
            }
        }

        if (request.getParameter("cleanCart") != null){
            cartService.clear(userDTO.getLogin());
            errorString = "Cart clean right now!";
        }

        if (hasError) {
            request.setAttribute("errorString", errorString);
            request.getRequestDispatcher("/view/order.jsp").forward(request, response);
        } else {
            SessionUtils.storeLoginedUser(session,
                    mapper.map(userBusinessService.getByLogin(userDTO.getLogin()), UserDTO.class));
            response.sendRedirect(request.getContextPath() + "/customer");
        }
    }
}
