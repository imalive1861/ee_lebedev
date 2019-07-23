package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.service.business.card.CardService;
import com.accenture.flowershop.be.service.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.utils.SessionUtils;
import com.accenture.flowershop.fe.dto.CustomerCardDTO;
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
    private CardService cardService;

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

        try {
            request.setAttribute("flowerList", flowerBusinessService.getAll().values());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

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
                    HttpSession session = request.getSession();
                    String login = SessionUtils.getLoginedUser(session).getLogin();
                    CustomerCardDTO flower = cardService.getCardById(login, flowerIds);
                    if (flower != null) {
                        if (numbersToCard <= (flowerDTO.getNumber() -
                                flower.getNumber())){
                            cardService.editCard(login, flowerIds, numbersToCard,
                                    (flowerDTO.getPrice().multiply(new BigDecimal(numbersToCard))));
                        } else {
                            hasError = true;
                            errorString = "There are not enough flowers available";
                        }
                    } else {
                        cardService.addNewFlowerToCard(login, flowerDTO, numbersToCard,
                                (flowerDTO.getPrice().multiply(new BigDecimal(numbersToCard))));
                    }
                }
            }
        }

        if (hasError) {
            request.setAttribute("errorString", errorString);
        }

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/customer.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
