package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.Flower;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FlowerAccess {
    void saveFlower(Flower flower);

    void delete(long id);

    void update(Flower user);

    Flower get(long id);

    List<Flower> getAll();

    List<Flower> getFlowerByName(String name);

    List<Flower> getFlowerByPrice(BigDecimal min, BigDecimal max);

    BigDecimal getFlowerMaxPrice();
}
