package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.service.business.cart.CartBusinessService;
import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.be.utils.SessionUtils;
import com.accenture.flowershop.fe.enums.OrderStatus;
import com.accenture.flowershop.fe.service.dto.orderdto.OrderDtoService;
import com.accenture.flowershop.fe.service.dto.userdto.UserDtoService;
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

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    /**
     * Ссылка на бизнес уровень для сущности User.
     */
    @Autowired
    private UserBusinessService userBusinessService;
    /**
     * Вспомогательный сервис.
     */
    @Autowired
    private OrderDtoService orderDtoService;
    /**
     * Ссылка на бизнес уровень для сущности Cart.
     */
    @Autowired
    private CartBusinessService cartBusinessService;
    /**
     * Вспомогательный сервис.
     */
    @Autowired
    private UserDtoService userDtoService;
    /**
     * Наличие ошибки. Пока true переход на другую страницу не осуществляется.
     */
    private boolean hasError = true;
    /**
     * Информационное сообщение.
     */
    private String outputString = null;

    public OrderServlet() {
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
     * Запрос POST. Перенаправляет запрос на GET.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = SessionUtils.getLoginedUser(session).getLogin();

        if (isNotBlank(request.getParameter("createOrder"))) {
            startCreateOrder(login);
        }

        if (isNotBlank(request.getParameter("cleanCart"))) {
            cleanCart(login);
            outputString = "Cart clean right now!";
        }

        if (isNotBlank(request.getParameter("deletePosition"))) {
            String cartId = request.getParameter("flowerId");
            if (isNotBlank(cartId)) {
                cartBusinessService.deleteFlowerFromCart(Long.parseLong(cartId), login);
            }
            outputString = "Position delete!";
        }
        request.setAttribute("outputString", outputString);

        Order order = cartBusinessService.getCartById(login);
        request.setAttribute("userCart", orderDtoService.toDto(order));

        SessionUtils.storeLoginedUser(session,
                userDtoService.toDto(userBusinessService.getByLogin(login)));

        if (hasError) {
            outputString = null;
            request.getRequestDispatcher("/view/order.jsp").forward(request, response);
        } else {
            cleanCart(login);
            hasError = true;
            response.sendRedirect(request.getContextPath() + "/order");
        }
    }

    /**
     * Запрос GET. Получает пользователя и его корзину из сессии.
     * Проверяет нажаты ли кнопки и выводит данные на форму.
     * При наличии ошибки выводит сообщение об ошибке.
     * При отсутствии ошибки перенаправляет на страницу с ассортиментом товаров.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Начинает создание заказа.
     *
     * @param login - логин пользователя
     */
    private void startCreateOrder(String login) {
        if (checkUserCash(login)) {
            createOrder(login);
            hasError = false;
            return;
        }
        outputString = "Need more gold!";
    }

    /**
     * Очищает корзину пользователя.
     *
     * @param login - логин пользователя
     */
    private void cleanCart(String login) {
        cartBusinessService.clear(login);
    }

    /**
     * Создает заказ.
     *
     * @param login - логин пользователя
     */
    private void createOrder(String login) {
        User user = userBusinessService.getByLogin(login);
        Order order = cartBusinessService.getCartById(login);
        order.setDateCreate(new Date());
        order.setStatus(OrderStatus.PAID);
        order.setUser(user);
        user.getOrders().add(order);
        userBusinessService.update(user);
    }

    /**
     * Проверяет наличие средств у пользователя для совершения покупки.
     *
     * @return true - если средств достаточно и платеж совершен,
     * false - если средств недостаточно
     */
    private boolean checkUserCash(String login) {
        User user = userBusinessService.getByLogin(login);
        BigDecimal sumPrice = cartBusinessService.getCartById(login).getSumPrice();
        BigDecimal cash = user.getCash();
        if (sumPrice.compareTo(cash) < 0) {
            user.setCash(cash.subtract(sumPrice).setScale(2, RoundingMode.UP));
            return true;
        }
        return false;
    }
}
