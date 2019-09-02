package com.accenture.flowershop.services.jms;

import com.accenture.flowershop.be.entity.User;
import com.accenture.flowershop.be.service.marshgalling.user.UserMarshallingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import java.io.IOException;

@Component
public class ProducerTest {

    private UserMarshallingService userMarshallingService;
    private Prod prod;
    private Cons cons;

    @Autowired
    public ProducerTest(UserMarshallingService marshallingService,
                        Prod prod, Cons cons){
        this.userMarshallingService = marshallingService;
        this.prod = prod;
        this.cons = cons;
    }

    public User saleRequest(User user) {
        try {
            userMarshallingService.marshallingObjectToXML(user);
            prod.producer();
            Discount discount = (Discount) cons.consumer();
            user.setDiscount(discount.getDiscount());
        } catch (IOException | JMSException e){
            e.printStackTrace();
        }
        return user;
    }


}
