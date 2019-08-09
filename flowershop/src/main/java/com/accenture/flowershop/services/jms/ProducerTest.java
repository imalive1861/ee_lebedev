package com.accenture.flowershop.services.jms;

import com.accenture.flowershop.be.service.marshgalling.user.UserMarshallingService;
import com.accenture.flowershop.fe.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public UserDTO saleRequest(UserDTO userDTO) {
        try {
            userMarshallingService.userMarshallingObjectToXML(userDTO);
            prod.producer();
            Sale sale = (Sale) cons.consumer();
            userDTO.setDiscount(sale.getSale());
        } catch (Exception e){
            e.printStackTrace();
        }
        return userDTO;
    }


}
