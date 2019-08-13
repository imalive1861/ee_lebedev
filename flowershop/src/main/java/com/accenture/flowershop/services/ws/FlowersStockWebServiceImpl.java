package com.accenture.flowershop.services.ws;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.service.business.flower.FlowerBusinessService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;

@WebService(endpointInterface = "com.accenture.flowershop.services.ws.FlowersStockWebService")
public class FlowersStockWebServiceImpl implements FlowersStockWebService {

    private FlowerBusinessService flowerBusinessService;

    @Autowired
    public FlowersStockWebServiceImpl(FlowerBusinessService flowerBusinessService){
        this.flowerBusinessService = flowerBusinessService;
    }

    @Override
    public void increaseFlowersStockSize(int count) {
        for (Flower f: flowerBusinessService.getAll()){
            f.setNumber(f.getNumber() + count);
            flowerBusinessService.update(f);
        }
    }
}
