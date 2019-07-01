package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FlowerAccessImpl implements FlowerAccess {

    private List<Flower> flowers = new ArrayList<>();

    public void saveFlower(String name, double price, int number) {
        flowers.add(new Flower(setNextId(), name, price, number));
    }

    private int setNextId(){
        int i = 0;
        for (Flower user: flowers){
            if (user.getId() > i){
                i = user.getId();
            }
        }
        return i;
    }

    public void delete(Flower flower) {
        flowers.remove(flower);
    }

    public List<Flower> getAll() {
        return flowers;
    }

    public Flower getById(int id) {
        return flowers.get(id);
    }
}
