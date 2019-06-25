package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

public interface FlowerBusinessService {
    void save(Flower flower);

    void delete(Flower flower);

    List<Flower> getAll();

    Flower getById(Integer id);
}
