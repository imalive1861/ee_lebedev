package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.service.business.order.OrderBusinessService;
import com.accenture.flowershop.fe.dto.CartDTO;
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
import java.util.List;

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    @Autowired
    private CartService cartService;
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private UserMapper userMapper;

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

        boolean hasError = true;
        String errorString = null;

        UserDTO userDTO = SessionUtils.getLoginedUser(session);
        List<CartDTO> cartDTO = SessionUtils.getUserCart(session);
        BigDecimal allSum = cartService.getAllSumPrice(userDTO.getDiscount(), userDTO.getLogin());

        request.setAttribute("allSum", allSum);

        if (request.getParameter("createOrder") != null) {
            if (userBusinessService.checkCash(userMapper.userDtoToUser(userDTO), allSum)) {
                createOrder(userDTO, allSum, cartDTO);
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
        }

        SessionUtils.storeLoginedUser(session,
                userMapper.userToUserDto(userBusinessService.getByLogin(userDTO.getLogin())));
        response.sendRedirect(request.getContextPath() + "/customer");
    }

    private void createOrder(UserDTO userDTO, BigDecimal allSum, List<CartDTO> cartDTO) {
        orderBusinessService.create(allSum,
                cartMapper.cartDtosToCart(cartDTO),
                userMapper.userDtoToUser(userDTO));
        cartService.clear(userDTO.getLogin());
    }
}
