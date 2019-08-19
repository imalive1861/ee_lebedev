package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.fe.enums.OrderStatus;
import com.accenture.flowershop.fe.servicedto.cartdto.CartService;
import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.be.utils.SessionUtils;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
import java.math.RoundingMode;
import java.util.Date;

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

    private String errorString = null;
    private boolean hasError = true;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDTO userDTO = SessionUtils.getLoginedUser(session);

        checkButtonClick(request,userDTO,session);
        request.setAttribute("errorString", errorString);

        if (hasError) {
            errorString = null;
            request.getRequestDispatcher("/view/order.jsp").forward(request, response);
        } else {
            cleanCart(session, userDTO);
            hasError = true;
            response.sendRedirect(request.getContextPath() + "/customer");
        }
    }

    @Transactional
    private void checkButtonClick(HttpServletRequest request, UserDTO userDTO, HttpSession session) {
        if (request.getParameter("createOrder") != null) {
            User user = mapper.map(userDTO, User.class);
            Order order = mapper.map(SessionUtils.getUserCart(session),Order.class);
            if (checkUserCash(user, order.getSumPrice())) {
                createOrder(user,order);
                userDTO.setCash(user.getCash());
                hasError = false;
                return;
            }
                errorString = "Need more gold!";
        }

        if (request.getParameter("cleanCart") != null){
            cleanCart(session, userDTO);
            errorString = "Cart clean right now!";
        }
    }

    private void cleanCart(HttpSession session, UserDTO userDTO) {
        SessionUtils.storeUserCart(session, cartService.clear(userDTO.getLogin()));
    }

    private void createOrder(User user, Order order) {
        order.setDateCreate(new Date());
        order.setStatus(OrderStatus.PAID);
        order.setUserId(user);
        user.getOrders().add(order);
        userBusinessService.update(user);
    }

    private boolean checkUserCash(User user, BigDecimal sumPrice){
        BigDecimal cash = user.getCash();
        if (sumPrice.compareTo(cash) < 0) {
            user.setCash(cash.subtract(sumPrice).setScale(2, RoundingMode.UP));
            return true;
        }
        return false;
    }
}
