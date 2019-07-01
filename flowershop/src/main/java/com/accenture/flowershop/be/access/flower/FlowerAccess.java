package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowerAccess {
    void saveFlower(String name, double price, int number);

    void delete(Flower flower);

    List<Flower> getAll();

    Flower getById(int id);
}
