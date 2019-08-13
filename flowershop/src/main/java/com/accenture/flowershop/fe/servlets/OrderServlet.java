package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.service.business.cart.CartBusinessService;
import com.accenture.flowershop.fe.dto.mappers.CartMapper;
import com.accenture.flowershop.fe.servicedto.cartdto.CartService;
import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.be.utils.SessionUtils;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.dto.mappers.UserMapper;
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
    private CartBusinessService cartBusinessService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private UserMapper userMapper;

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
        String cleanCart = request.getParameter("cleanCart");
        boolean hasError = false;
        String errorString = null;
        UserDTO userDTO = SessionUtils.getLoginedUser(session);
        BigDecimal allSum = cartService.getAllSumPrice(userDTO.getDiscount(), userDTO.getLogin());
        request.setAttribute("allSum", allSum);

        if (createOrder != null) {
            if (cartBusinessService.save(allSum,
                    cartMapper.cartDtosToCart(SessionUtils.getUserCart(session)),
                    userMapper.userDtoToUser(userDTO))) {
                SessionUtils.storeLoginedUser(session,
                        userMapper.userToUserDto(userBusinessService.getByLogin(userDTO.getLogin())));
                response.sendRedirect(request.getContextPath() + "/customer");
            } else {
                hasError = true;
                errorString = "Need more gold!";
            }
        } else if (cleanCart != null){
            cartService.clear(userDTO.getLogin());
            hasError = true;
            errorString = "Cart clean right now!";
        } else {
            request.getRequestDispatcher("/view/order.jsp").forward(request, response);
        }

        if (hasError) {
            request.setAttribute("errorString", errorString);
            request.getRequestDispatcher("/view/order.jsp").forward(request, response);
        }
    }
}
