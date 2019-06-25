package com.accenture.flowershop.be.business.flower;

import com.accenture.flowershop.be.access.flower.FlowerAccess;
import com.accenture.flowershop.be.access.flower.FlowerAccessImpl;
import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

public class FlowerBusinessServiceImpl implements FlowerBusinessService{

    private FlowerAccess flowerAccess = new FlowerAccessImpl();

    public void save(Flower flower) {
        if(flower!=null) {
            List<Flower> flowers = flowerAccess.getAll();
            if(!flowers.isEmpty()) {
                Flower lastFlower = flowers.get(flowers.size() - 1);
                flower.setId(lastFlower.getId() + 1);
                flowerAccess.save(flower);
            }
        }
    }

    public void delete(Flower flower) {
        if(flower!=null) {
            flowerAccess.delete(flower);
        }
    }

    public List<Flower> getAll() {
        return flowerAccess.getAll();
    }

    public Flower getById(Integer id) {
        if(id!=null) {
            return flowerAccess.getById(id);
        }
        return null;
    }
}
