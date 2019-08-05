package com.accenture.flowershop.fe.ws;

import com.accenture.flowershop.be.service.business.flower.FlowerBusinessService;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;

@WebService
public class FlowersStockWebServiceImpl implements FlowersStockWebService {

    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Override
    public void increaseFlowersStockSize(int count) {
        for (FlowerDTO f: flowerBusinessService.getAll()){
            f.setNumber(f.getNumber() + count);
            flowerBusinessService.updateFlower(f);
        }
    }
}
