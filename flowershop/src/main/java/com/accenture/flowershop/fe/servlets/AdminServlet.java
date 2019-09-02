package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.service.business.order.OrderBusinessService;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.service.dto.orderdto.OrderService;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Сервлет для работы с администратором. Используется для просмотра и закрытия заказов.
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

    /**
     * Ссылка на бизнес уровень для сущности Order.
     */
    @Autowired
    private OrderBusinessService orderBusinessService;
    /**
     * Вспомогательный сервис.
     */
    @Autowired
    private OrderService orderService;

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

    /**
     * Запрос GET. Проверяет нажаты ли кнопки и выводит данные на форму.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("closeOrderButton") != null) {
            closeOrder(request.getParameter("orderId"));
        }

        dataOutput(request);

        RequestDispatcher dispatcher =
                this.getServletContext().getRequestDispatcher("/view/admin.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Запрос POST. Перенаправляет запрос на GET.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * Отметить заказ как закрытый (CLOSED).
     * @param orderId - идентификатор заказа
     */
    private void closeOrder(String orderId) {
        if (orderId != null) {
            OrderDTO orderDTO = orderDTOsMap.get(Long.parseLong(orderId));
            orderBusinessService.close(orderService.fromDto(orderDTO));
        }
    }

    /**
     * Карта существующих заказов.
     */
    private Map<Long, OrderDTO> orderDTOsMap;

    /**
     * Заполнение карты существующих заказов и вывод информации о заказах на форму.
     * @param request - объект HttpServletRequest
     */
    private void dataOutput(HttpServletRequest request) {
        List<OrderDTO> orderDTOs = orderService.toDtoList(orderBusinessService.getAll());
        orderDTOsMap = orderDTOs.stream().collect(Collectors.toMap(
                OrderDTO::getId,
                o -> o
        ));
        request.setAttribute("orderList", orderDTOs);
    }
}
