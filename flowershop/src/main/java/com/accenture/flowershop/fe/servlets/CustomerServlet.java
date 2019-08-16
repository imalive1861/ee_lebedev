package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.servicedto.cartdto.CartService;
import com.accenture.flowershop.be.service.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.utils.SessionUtils;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import org.dozer.Mapper;
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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@WebServlet(name = "CustomerServlet", urlPatterns = { "/customer" })
public class CustomerServlet extends HttpServlet {

    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Autowired
    private CartService cartService;

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

    private boolean hasError = true;
    private String errorString = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        checkButtonClick(request);

        request.setAttribute("errorString", errorString);

        if (hasError) {
            errorString = null;
        } else {
            hasError = true;
        }

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/view/customer.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    private void checkButtonClick(HttpServletRequest request) {
        Map<Long, FlowerDTO> flowerDTOs = mapFlowerDto(getFlowerDTOs(request));
        request.setAttribute("flowerList",flowerDTOs.values());

        HttpSession session = request.getSession();
        if (request.getParameter("addToCardButton") != null) {
            if (isAddFlowerToCart(
                    Integer.parseInt(request.getParameter("numberToCart")),
                    flowerDTOs.get(Long.parseLong(request.getParameter("flowerId"))),
                    SessionUtils.getLoginedUser(session))) {
                hasError = false;
                return;
            }
            errorString = "There are not enough flowers available";
        }
    }

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

    private Map<Long, FlowerDTO> mapFlowerDto(List<Flower> flowerList) {
        Map<Long, FlowerDTO> flowerDTOs = new TreeMap<>();
        for (Flower f : flowerList) {
            flowerDTOs.put(f.getId(), mapper.map(f, FlowerDTO.class));
        }
        if (flowerDTOs.values().isEmpty()) {
            errorString = "Flower not found!";
        }
        return flowerDTOs;
    }

    private boolean isAddFlowerToCart(int numberToCart, FlowerDTO flowerDTO, UserDTO userDTO){
        return cartService.isAddFlowerToCart(userDTO,flowerDTO,numberToCart);
    }
}