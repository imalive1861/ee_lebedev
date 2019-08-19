package com.accenture.flowershop.be.service.business.flower;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.repository.flower.FlowerRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Реализация интерфейса FlowerBusinessService.
 */
@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService{

    /**
     * Ссылка на уровень доступа к базе данных для сущности Flower.
     */
    @Autowired
    private FlowerRepository flowerRepository;
    /**
     * Логгер.
     */
    @Autowired
    private Logger LOG;

    public FlowerBusinessServiceImpl(){}

    @Override
    public void update(Flower flower){
        flowerRepository.saveAndFlush(flower);
        LOG.debug("Number of Flower with id = {} has been changed to = {}",
                flower.getId(), flower.getNumber());
    }

    @Override
    public Flower get(long id) {
            return flowerRepository.getOne(id);
    }

    @Override
    public List<Flower> getAll() {
        return flowerRepository.findAll();
    }

    @Override
    public List<Flower> getFlowerByName(String name) {
        if (name.equals("")){
            return getAll();
        }
        return flowerRepository.findAllFlowerByName(name);
    }

    @Override
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
