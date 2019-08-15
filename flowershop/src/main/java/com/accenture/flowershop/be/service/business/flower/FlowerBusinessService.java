package com.accenture.flowershop.be.service.business.flower;

import com.accenture.flowershop.be.entity.Flower;

import java.util.List;

public interface FlowerBusinessService {
    void update(Flower flower);
    List<Flower> getAll();
    List<Flower> getFlowerByName(String name);
    List<Flower> getFlowerByPrice(String minFlowerPrice, String maxFlowerPrice);
    Flower get(long id);
}
