package com.accenture.flowershop.be.service.business.flower;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.repository.flower.FlowerRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class FlowerBusinessServiceImpl implements FlowerBusinessService{

    @Autowired
    private FlowerRepository flowerRepository;
    @Autowired
    private Logger LOG;

    public FlowerBusinessServiceImpl(){}

    public void update(Flower flower){
        flowerRepository.saveAndFlush(flower);
        LOG.debug("Number of Flower with id = {} has been changed to = {}",
                flower.getId(), flower.getNumber());
    }

    public Flower get(long id) {
            return flowerRepository.getOne(id);
    }

    public List<Flower> getAll() {
        return flowerRepository.findAll();
    }

    public List<Flower> getFlowerByName(String name) {
        if (name.equals("")){
            return getAll();
        }
        return flowerRepository.findAllFlowerByName(name);
    }

    public List<Flower> getFlowerByPrice(String minFlowerPrice, String maxFlowerPrice) {
        if (minFlowerPrice.equals("") && maxFlowerPrice.equals("")){
            return getAll();
        }
        if (minFlowerPrice.equals("")){
            BigDecimal max = new BigDecimal(Double.parseDouble(maxFlowerPrice));
            return flowerRepository.getFlowerByMaxPrice(max);
        }
        if (maxFlowerPrice.equals("")){
            BigDecimal min = new BigDecimal(Double.parseDouble(minFlowerPrice));
            return flowerRepository.getFlowerByMinPrice(min);
        }
        BigDecimal min = new BigDecimal(Double.parseDouble(minFlowerPrice));
        BigDecimal max = new BigDecimal(Double.parseDouble(maxFlowerPrice));
        return flowerRepository.getFlowerByMinPriceAndMaxPrice(min, max);
    }
}
