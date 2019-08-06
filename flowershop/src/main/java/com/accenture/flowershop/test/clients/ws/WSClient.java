package com.accenture.flowershop.test.clients.ws;

import com.accenture.flowershop.services.ws.FlowersStockWebService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class WSClient {
    public static void main(String[] args){
        Timer timer = new Timer();
        timer.schedule(new AddFlowers(), 0, 10 * 60 * 1000);
    }
}
class AddFlowers extends TimerTask {
    public void run() {
        Random random = new Random();
        int num = 10 + random.nextInt(30 - 10);
        try {
            add(num);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    private void add(int num) throws MalformedURLException{
        URL wsdlURL = new URL("http://localhost:8080/ws/flowers?wsdl");
        QName SERVICE_NAME = new QName(
                "http://ws.fe.flowershop.accenture.com/", "FlowersStockWebServiceImplService");
        Service service = Service.create(wsdlURL, SERVICE_NAME);
        FlowersStockWebService client = service.getPort(FlowersStockWebService.class);
        client.increaseFlowersStockSize(num);
    }
}