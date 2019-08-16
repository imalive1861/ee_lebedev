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

    private Map<Long, FlowerDTO> flowerDTOs = new TreeMap<>();

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean hasError = false;
        String errorString = null;

        getFlowerDTOs(
                request.getParameter("searchNameClick"),
                request.getParameter("searchFlowerByName"),
                request.getParameter("searchPriceClick"),
                request.getParameter("minFlowerPrice"),
                request.getParameter("maxFlowerPrice"));

        if (flowerDTOs.values().isEmpty()) {
            hasError = true;
            errorString = "Flower not found!";
        }
        request.setAttribute("flowerList",flowerDTOs.values());

        HttpSession session = request.getSession();

        if (request.getParameter("addToCardButton") != null) {
            if (!(isAddFlowerToCart(request.getParameter("numberToCart"),
                    request.getParameter("flowerId"), session))) {
                hasError = true;
                errorString = "There are not enough flowers available";
            }
        }

        if (hasError) {
            request.setAttribute("errorString", errorString);
        }

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/view/customer.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    private void getFlowerDTOs(String searchNameClick,
                               String searchFlowerByName,
                               String searchPriceClick,
                               String minFlowerPrice,
                               String maxFlowerPrice){
        List<Flower> flowerList;
        if (searchNameClick != null) {
            flowerList = flowerBusinessService.getFlowerByName(searchFlowerByName);
        } else if (searchPriceClick != null) {
            flowerList = flowerBusinessService.getFlowerByPrice(minFlowerPrice, maxFlowerPrice);
        } else {
            flowerList = flowerBusinessService.getAll();
        }
        for (Flower f : flowerList) {
            flowerDTOs.put(f.getId(), mapper.map(f, FlowerDTO.class));
        }
    }

    private boolean isAddFlowerToCart(String numberToCart,
                                      String flowerId,
                                      HttpSession session){
        if (numberToCart != null && flowerId != null) {
            long flowerIds = Long.parseLong(flowerId);
            int numbersToCart = Integer.parseInt(numberToCart);
            UserDTO userDTO = SessionUtils.getLoginedUser(session);
            return cartService.isAddFlowerToCart(userDTO,flowerDTOs.get(flowerIds),numbersToCart);
        }
        return false;
    }
}