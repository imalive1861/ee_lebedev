package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.enums.OrderStatus;
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
import java.math.RoundingMode;
import java.util.Date;

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    /**
     * Ссылка на транспортный уровень для работы с временной корзиной покупателя.
     */
    @Autowired
    private CartService cartService;
    /**
     * Ссылка на бизнес уровень для сущности User.
     */
    @Autowired
    private UserBusinessService userBusinessService;
    /**
     * Маппер.
     */
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

    /**
     * Наличие ошибки. Пока true переход на другую страницу не осуществляется.
     */
    private boolean hasError = true;
    /**
     * Сообщение об ошибке.
     */
    private String errorString = null;

    /**
     * Запрос POST. Перенаправляет запрос на GET.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Запрос GET. Получает пользователя и его корзину из сессии.
     * Проверяет нажаты ли кнопки и выводит данные на форму.
     * При наличии ошибки выводит сообщение об ошибке.
     * При отсутствии ошибки перенаправляет на страницу с ассортиментом товаров.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDTO userDTO = SessionUtils.getLoginedUser(session);
        OrderDTO orderDTO = SessionUtils.getUserCart(session);

        if (request.getParameter("createOrder") != null) {
            startCreateOrder(userDTO, orderDTO);
        }

        if (request.getParameter("cleanCart") != null){
            cleanCart(session, userDTO);
            errorString = "Cart clean right now!";
        }

        request.setAttribute("errorString", errorString);

        SessionUtils.storeLoginedUser(session,
                mapper.map(userBusinessService.getByLogin(userDTO.getLogin()),UserDTO.class));

        if (hasError) {
            errorString = null;
            request.getRequestDispatcher("/view/order.jsp").forward(request, response);
        } else {
            cleanCart(session, userDTO);
            hasError = true;
            response.sendRedirect(request.getContextPath() + "/order");
        }
    }

    /**
     * Начинает создание заказа.
     * @param userDTO - объект UserDTO
     * @param orderDTO - объект OrderDTO
     */
    private void startCreateOrder(UserDTO userDTO, OrderDTO orderDTO) {
        User user = mapper.map(userDTO, User.class);
        Order order = mapper.map(orderDTO,Order.class);
        if (checkUserCash(user, order.getSumPrice())) {
            createOrder(user,order);
            hasError = false;
            return;
        }
        errorString = "Need more gold!";
    }

    /**
     * Очищает корзину пользователя.
     * @param session - объект HttpSession
     * @param userDTO - объект UserDTO
     */
    private void cleanCart(HttpSession session, UserDTO userDTO) {
        SessionUtils.storeUserCart(session, cartService.clear(userDTO.getLogin()));
    }

    /**
     * Создает заказ.
     * @param user - объект User
     * @param order - объект Order
     */
    private void createOrder(User user, Order order) {
        order.setDateCreate(new Date());
        order.setStatus(OrderStatus.PAID);
        order.setUserId(user);
        user.getOrders().add(order);
        userBusinessService.update(user);
    }

    /**
     * Проверяет наличие средств у пользователя для совершения покупки.
     * @param user - объект User
     * @param sumPrice - суммарная цена заказа
     * @return true - если средств достаточно и платеж совершен,
     *         false - если средств недостаточно
     */
    private boolean checkUserCash(User user, BigDecimal sumPrice){
        BigDecimal cash = user.getCash();
        if (sumPrice.compareTo(cash) < 0) {
            user.setCash(cash.subtract(sumPrice).setScale(2, RoundingMode.UP));
            return true;
        }
        return false;
    }
}
