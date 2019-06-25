package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FlowerAccessImpl implements FlowerAccess {

    private List<Flower> flowers = new ArrayList<>();

    public void save(Flower flower) {
        flowers.add(flower);
    }

    public void delete(Flower flower) {
        flowers.remove(flower);
    }

    public List<Flower> getAll() {
        return flowers;
    }

    public Flower getById(Integer id) {
        return flowers.get(id);
    }
}
