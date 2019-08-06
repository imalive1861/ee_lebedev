package com.accenture.flowershop.services.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface FlowersStockWebService {
    @WebMethod
    void increaseFlowersStockSize(@WebParam(name ="countOfFlowers") int count);
}