package com.accenture.flowershop.be.service.business.flower;

import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

public interface FlowerBusinessService {
    void saveNewFlower(Flower flower);

    void delete(Flower flower);

    List<Flower> getAll();

    Flower getById(Integer id);
}
