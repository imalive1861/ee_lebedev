package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

public interface FlowerAccess {
    void save(Flower flower);

    void delete(Flower flower);

    List<Flower> getAll();

    Flower getById(Integer id);
}
