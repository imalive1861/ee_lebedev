package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.entity.Cart;
import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.entity.Order;
import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.service.business.cart.CartBusinessService;
import com.accenture.flowershop.be.service.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.service.business.order.OrderBusinessService;
import com.accenture.flowershop.be.service.business.user.UserBusinessService;
import com.accenture.flowershop.be.utils.SessionUtils;
import com.accenture.flowershop.fe.dto.UserDTO;
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
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    /**
     * Ссылка на бизнес уровень для сущности Cart.
     */
    @Autowired
    private CartBusinessService cartBusinessService;
    /**
     * Ссылка на бизнес уровень для сущности Cart.
     */
    @Autowired
    private OrderBusinessService orderBusinessService;
    /**
     * Ссылка на бизнес уровень для сущности User.
     */
    @Autowired
    private UserBusinessService userBusinessService;
    /**
     * Ссылка на бизнес уровень для сущности User.
     */
    @Autowired
    private FlowerBusinessService flowerBusinessService;
    /**
     * Вспомогательный сервис.
     */
    @Autowired
    private OrderDtoService orderDtoService;
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
        UserDTO userDTO = SessionUtils.getLoginedUser(session);
        User user = userDtoService.fromDto(userDTO);
        String login = userDTO.getLogin();

        if (isNotBlank(request.getParameter("createOrder"))) {
            createOrder(user);
            if (!hasError) {
                cleanCart(login);
            }
        }

        if (isNotBlank(request.getParameter("payOrder"))) {
            startPayOrder(user, request.getParameter("orderId"));
        }

        if (isNotBlank(request.getParameter("cleanCart"))) {
            cleanCart(login);
            outputString = "Cart clean right now!";
        }

        if (isNotBlank(request.getParameter("deletePosition"))) {
            String cartId = request.getParameter("flowerId");
            if (isNotBlank(cartId)) {
                cartBusinessService.deleteFlowerFromCart(cartId, login);
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
     * Создает заказ.
     *
     * @param user - объект User
     */
    private void createOrder(User user) {
        Order order = cartBusinessService.getCartById(user.getLogin());
        if (!order.getCarts().isEmpty()) {
            order.setDateCreate(new Date());
            order.setUser(user);
            user.getOrders().add(order);
            orderBusinessService.save(order);
            userBusinessService.update(user);
            cartBusinessService.updateAll(order.getCarts());
            hasError = false;
        }
    }

    /**
     * Начинает оплату заказа.
     */
    private void startPayOrder(User user, String orderId) {
        if (!isNotBlank(orderId)) {
            outputString = "Something went wrong!";
            return;
        }
        payOrder(user, orderId);
    }

    /**
     * Оплачивает заказа.
     *
     * @param user    - объект User
     * @param orderId - идентификатор заказа
     */
    private void payOrder(User user, String orderId) {
        Order order = null;
        for (Order o : user.getOrders()) {
            if (orderId.equals(o.getId())) {
                order = o;
            }
        }
        if (order == null) {
            outputString = "Order not found!";
            return;
        }
        if (!order.getStatus().equals(OrderStatus.OPENED)) {
            outputString = "Order has already been paid or closed! O_o";
            return;
        }
        Set<Cart> carts = order.getCarts();
        for (Cart cart : carts) {
            cart.setSumPriceWithDiscount(
                    cartBusinessService.getSumPriceWithDiscount(
                            cart.getFlower().getPrice(),
                            user.getDiscount(),
                            cart.getNumber()));
        }
        cartBusinessService.countAllSumPrice(order);
        BigDecimal sumPrice = order.getSumPriceWithDiscount();
        BigDecimal cash = user.getCash();
        if (sumPrice.compareTo(cash) > 0) {
            outputString = "Need more gold!";
            return;
        }
        user.setCash(cash.subtract(sumPrice).setScale(2, RoundingMode.UP));
        for (Cart c : carts) {
            Flower flower = c.getFlower();
            flower.setNumber(flower.getNumber() - c.getNumber());
        }
        order.setStatus(OrderStatus.PAID);
        orderBusinessService.save(order);
        hasError = false;
    }

    /**
     * Очищает корзину пользователя.
     *
     * @param login - логин пользователя
     */
    private void cleanCart(String login) {
        cartBusinessService.clear(login);
    }
}
