package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FlowerAccess {
    void saveFlower(String name, BigDecimal price, int number);

    void delete(long id);

    void update(Flower user);

    Flower get(long id);

    List<Flower> getAll();
}
