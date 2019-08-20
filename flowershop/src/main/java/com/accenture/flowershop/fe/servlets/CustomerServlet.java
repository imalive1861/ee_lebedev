package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.servicedto.cartdto.CartService;
import com.accenture.flowershop.be.service.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.utils.SessionUtils;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Сервлет для работы с покупателем. Используется:
 * для вывода ассортимента товаров (цветков),
 * для добавления товара в корзину покупателя.
 */
@WebServlet(name = "CustomerServlet", urlPatterns = { "/customer" })
public class CustomerServlet extends HttpServlet {

    /**
     * Ссылка на бизнес уровень для сущности Flower.
     */
    @Autowired
    private FlowerBusinessService flowerBusinessService;
    /**
     * Ссылка на транспортный уровень для работы с временной корзиной покупателя.
     */
    @Autowired
    private CartService cartService;
    /**
     * Маппер.
     */
    @Autowired
    private Mapper mapper;

    public CustomerServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
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
     * Запрос GET. Проверяет нажаты ли кнопки и выводит данные на форму.
     * При наличии ошибки выводит сообщение об ошибке.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("addToCardButton") != null) {
            addFlowerToCart(request);
        }

        dataOutput(request);

        request.setAttribute("errorString", errorString);

        if (hasError) {
            errorString = null;
        } else {
            hasError = true;
        }

        RequestDispatcher dispatcher =
                this.getServletContext().getRequestDispatcher("/view/customer.jsp");
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
     * Карта цветов.
     */
    private Map<Long, FlowerDTO> flowerDTOs;

    /**
     * Вывод информации о цветках на форму.
     * @param request - объект HttpServletRequest
     */
    private void dataOutput(HttpServletRequest request) {
        flowerDTOs = new TreeMap<>();
        mapFlowerDto(getFlowerDTOs(request));
        request.setAttribute("flowerList",flowerDTOs.values());
    }

    /**
     * Добавить цветок в корзину.
     * @param request - объект HttpServletRequest
     */
    private void addFlowerToCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (isAddFlowerToCart(
                Integer.parseInt(request.getParameter("numberToCart")),
                flowerDTOs.get(Long.parseLong(request.getParameter("flowerId"))),
                SessionUtils.getLoginedUser(session))) {
            hasError = false;
            return;
        }
        errorString = "There are not enough flowers available";
    }

    /**
     * Фильтрация и получение информации о цветках из базы данных.
     * @param request - объект HttpServletRequest
     * @return список цветков (отфильтрованный, если это нужно)
     */
    private List<Flower> getFlowerDTOs(HttpServletRequest request) {
        List<Flower> flowerList;
        if (request.getParameter("searchNameClick") != null) {

            flowerList = flowerBusinessService.getFlowerByName(
                    request.getParameter("searchFlowerByName"));

        } else if (request.getParameter("searchPriceClick") != null) {

            flowerList = flowerBusinessService.getFlowerByPrice(
                    request.getParameter("minFlowerPrice"),
                    request.getParameter("maxFlowerPrice"));

        } else {
            flowerList = flowerBusinessService.getAll();
        }
        return flowerList;
    }

    /**
     * Заполнение карты цветков.
     * @param flowerList - список цветков
     */
    private void mapFlowerDto(List<Flower> flowerList) {
        for (Flower f : flowerList) {
            flowerDTOs.put(f.getId(), mapper.map(f, FlowerDTO.class));
        }
        if (flowerDTOs.values().isEmpty()) {
            errorString = "Flower not found!";
        }
    }

    /**
     * Добавление цветка в корзину.
     * @param numberToCart - количество цветков
     * @param flowerDTO - объект FlowerDTO
     * @param userDTO - объект UserDTO
     * @return true - если цветок добавлен, false - если цветок не добавлен
     */
    @Transactional
    private boolean isAddFlowerToCart(int numberToCart, FlowerDTO flowerDTO, UserDTO userDTO){
        return cartService.isAddFlowerToCart(userDTO,flowerDTO,numberToCart);
    }
}