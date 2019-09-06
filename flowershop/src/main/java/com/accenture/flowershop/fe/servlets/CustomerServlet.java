package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.service.business.cart.CartBusinessService;
import com.accenture.flowershop.be.service.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.utils.SessionUtils;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.service.dto.cartdto.CartDtoService;
import com.accenture.flowershop.fe.service.dto.flowerdto.FlowerDtoService;
import com.accenture.flowershop.fe.service.dto.userdto.UserDtoService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Сервлет для работы с покупателем. Используется:
 * для вывода ассортимента товаров (цветков),
 * для добавления товара в корзину покупателя.
 */
@WebServlet(name = "CustomerServlet", urlPatterns = {"/customer"})
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
    private CartDtoService cartDtoService;
    /**
     * Маппер.
     */
    @Autowired
    private FlowerDtoService flowerDtoService;
    /**
     * Ссылка на бизнес уровень для сущности Cart.
     */
    @Autowired
    private CartBusinessService cartBusinessService;
    /**
     * Ссылка на транспортный уровень для сущности User.
     */
    @Autowired
    private UserDtoService userDtoService;
    /**
     * Наличие ошибки. Пока true переход на другую страницу не осуществляется.
     */
    private boolean hasError = true;
    /**
     * Сообщение об ошибке.
     */
    private String errorString = null;
    /**
     * Карта цветов.
     */
    private Map<Long, FlowerDTO> flowerDTOMap;

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
     * Запрос GET. Проверяет нажаты ли кнопки и выводит данные на форму.
     * При наличии ошибки выводит сообщение об ошибке.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (isNotBlank(request.getParameter("addToCardButton"))) {
            addFlowerToCart(request);
        }

        dataOutput(request);

        request.setAttribute("errorString", errorString);
        request.setAttribute("searchFlowerByName", request.getParameter("searchFlowerByName"));
        request.setAttribute("minFlowerPrice", request.getParameter("minFlowerPrice"));
        request.setAttribute("maxFlowerPrice", request.getParameter("maxFlowerPrice"));

        if (isNotBlank(request.getParameter("cleanSearchClick"))) {
            request.setAttribute("searchFlowerByName", "");
            request.setAttribute("minFlowerPrice", "");
            request.setAttribute("maxFlowerPrice", "");
        }

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
        doGet(request, response);
    }

    /**
     * Вывод информации о цветках на форму.
     *
     * @param request - объект HttpServletRequest
     */
    private void dataOutput(HttpServletRequest request) {
        flowerDTOMap = new HashMap<>();
        mapFlowerDto(getFlowerDTOs(request));
        request.setAttribute("flowerList", flowerDTOMap.values());
    }

    /**
     * Добавить цветок в корзину.
     *
     * @param request - объект HttpServletRequest
     */
    private void addFlowerToCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (isAddFlowerToCart(
                Integer.parseInt(request.getParameter("numberToCart")),
                flowerDTOMap.get(Long.parseLong(request.getParameter("flowerId"))),
                SessionUtils.getLoginedUser(session).getLogin())) {
            hasError = false;
            return;
        }
        errorString = "There are not enough flowers available";
    }

    /**
     * Фильтрация и получение информации о цветках из базы данных.
     *
     * @param request - объект HttpServletRequest
     * @return список цветков (отфильтрованный, если это нужно)
     */
    private List<Flower> getFlowerDTOs(HttpServletRequest request) {
        if (isNotBlank(request.getParameter("searchClick"))) {
            return flowerBusinessService.getFlowerByNameOrMinPriceAndMaxPrice(
                    request.getParameter("searchFlowerByName"),
                    checkPrice(request.getParameter("minFlowerPrice")),
                    checkPrice(request.getParameter("maxFlowerPrice")));
        }
        return flowerBusinessService.getAll();
    }

    /**
     * Заполнение карты цветков.
     *
     * @param flowerList - список цветков
     */
    private void mapFlowerDto(List<Flower> flowerList) {
        List<FlowerDTO> flowerDTOs = flowerDtoService.toDtoList(flowerList);
        flowerDTOMap = flowerDTOs.stream().collect(Collectors.toMap(
                FlowerDTO::getId,
                o -> o
        ));
        if (flowerDTOMap.values().isEmpty()) {
            errorString = "Flower not found!";
        }
    }

    /**
     * Добавление цветка в корзину.
     *
     * @param numberToCart - количество цветков
     * @param flowerDTO    - объект FlowerDTO
     * @param login        - объект UserDTO
     * @return true - если цветок добавлен, false - если цветок не добавлен
     */
    private boolean isAddFlowerToCart(int numberToCart, FlowerDTO flowerDTO, String login) {
        return cartBusinessService.isAddFlowerToCart(
                login,
                flowerDtoService.fromDto(flowerDTO),
                numberToCart);
    }

    private BigDecimal checkPrice(String minFlowerPrice) {
        if (isNotBlank(minFlowerPrice)) {
            return new BigDecimal(Double.parseDouble(minFlowerPrice));
        }
        return null;
    }
}