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
        boolean hasError = false;
        String errorString = null;

        if (numberToCard != null && flowerId != null) {
            long flowerIds = Long.parseLong(flowerId);
            int numbersToCard = Integer.parseInt(numberToCard);
            FlowerDTO flowerDTO = flowerBusinessService.get(flowerIds);
            if (numbersToCard > flowerDTO.getNumber()) {
                hasError = true;
                errorString = "There are not enough flowers available";
            } else {
                if (numbersToCard < 0) {
                    hasError = true;
                    errorString = "Incorrect number";
                } else {
                    if (cardBusinessService.getCardById(flowerIds) != null) {
                        if (numbersToCard <= (flowerDTO.getNumber() -
                                cardBusinessService.getCardById(flowerIds).getNumber())){
                            cardBusinessService.editCard(flowerIds, numbersToCard,
                                    (flowerDTO.getPrice().multiply(new BigDecimal(numbersToCard))));
                        } else {
                            hasError = true;
                            errorString = "There are not enough flowers available";
                        }
                    } else {
                        cardBusinessService.addNewFlowerToCard(flowerDTO.getId(), flowerDTO.getName(),
                                numbersToCard, (flowerDTO.getPrice().multiply(new BigDecimal(numbersToCard))));
                    }
                }
            }
        }

        if (hasError) {
            request.setAttribute("errorString", errorString);
        }

        HttpSession session = request.getSession();
        MyUtils.storeUserCard(session, cardBusinessService.getCard());

        try {
            request.setAttribute("flowerList", flowerBusinessService.getAll().values());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/customer.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
