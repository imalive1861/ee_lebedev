package com.accenture.flowershop.be.service.business.flower;

import com.accenture.flowershop.be.access.flower.FlowerAccess;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService{

    private static final Logger LOG = LoggerFactory.getLogger(FlowerBusinessServiceImpl.class);

    @Autowired
    private FlowerAccess flowerAccess;

    public void saveNewFlower(Flower flower) {
        flowerAccess.saveFlower(flower.getName(), flower.getPrice(), flower.getNumber());
        LOG.debug("Order with name = {} price = {} was created", flower.getName(), flower.getPrice(), flower.getNumber());
    }

    public void delete(Flower flower) {
        if(flower!=null) {
            //flowerAccess.delete(flower);
        }
    }

    public List<Flower> getAll() {
        return flowerAccess.getAll();
    }

    public Flower getById(Integer id) {
        if(id!=null) {
            //return flowerAccess.getById(id);
        }
        return null;
    }
}
