package com.accenture.flowershop.services.ws;

import com.accenture.flowershop.be.service.business.flower.FlowerBusinessService;
import com.accenture.flowershop.fe.dto.FlowerDTO;
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
        for (FlowerDTO f: flowerBusinessService.getAll()){
            f.setNumber(f.getNumber() + count);
            flowerBusinessService.update(f);
        }
    }
}
