package com.accenture.flowershop.shop.services.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Интерфейс, реализующий веб-сервис для пополнения имеющегося количества цветков.
 */
@WebService
public interface FlowersStockWebService {
    /**
     * Пополнение имеющегося количества цветков.
     *
     * @param count - количество цветков для пополнения
     */
    @WebMethod
    void increaseFlowersStockSize(@WebParam(name = "countOfFlowers") int count);
}
