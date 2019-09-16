package com.accenture.flowershop.services.ws;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.service.business.flower.FlowerBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import java.util.List;

/**
 * Реализация интерфейса FlowersStockWebService.
 */
@WebService(endpointInterface = "com.accenture.flowershop.services.ws.FlowersStockWebService")
public class FlowersStockWebServiceImpl implements FlowersStockWebService {

    /**
     * Ссылка на бизнес уровень для сущности Flower.
     */
    private FlowerBusinessService flowerBusinessService;

    @Autowired
    public FlowersStockWebServiceImpl(FlowerBusinessService flowerBusinessService) {
        this.flowerBusinessService = flowerBusinessService;
    }

    @Override
    @Transactional
    public void increaseFlowersStockSize(int count) {
        List<Flower> flowers = flowerBusinessService.getAll();
        for (Flower f : flowers) {
            f.setNumber(f.getNumber() + count);
        }
        flowerBusinessService.updateAll(flowers);
    }
}
