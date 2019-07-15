package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.service.business.card.CardBusinessService;
import com.accenture.flowershop.be.service.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.utils.MyUtils;
import com.accenture.flowershop.fe.dto.FlowerDTO;
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
import java.util.Map;

@WebServlet(name = "CustomerServlet", urlPatterns = { "/customer" })
public class CustomerServlet extends HttpServlet {
    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Autowired
    private CardBusinessService cardBusinessService;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String numberToCard = request.getParameter("numberToCard");
        String flowerId = request.getParameter("flowerId");

        String errorString = null;
        Map<Long, FlowerDTO> flowers;
        HttpSession session = request.getSession();

        if (numberToCard != null && flowerId != null) {
            long flowerIds = Long.parseLong(flowerId);
            int numbersToCard = Integer.parseInt(numberToCard);
            FlowerDTO flowerDTO = flowerBusinessService.get(flowerIds);
            if (numbersToCard < flowerDTO.getNumber()) {
                cardBusinessService.addFlowerToCard(flowerDTO.getId(), flowerDTO.getName(),
                        numbersToCard, (flowerDTO.getPrice().multiply(new BigDecimal(numbersToCard))));
                MyUtils.storeUserCard(session, cardBusinessService.getCard());
            }
        }

        try {
            flowers = flowerBusinessService.getAll();
            request.setAttribute("flowerList", flowers.values());

        } catch (NullPointerException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        request.setAttribute("errorString", errorString);


        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/customer.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
