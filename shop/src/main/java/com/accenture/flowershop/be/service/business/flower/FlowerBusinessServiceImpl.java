package com.accenture.flowershop.be.service.business.flower;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.repository.flower.FlowerRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Реализация интерфейса FlowerBusinessService.
 */
@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService {

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

    public FlowerBusinessServiceImpl() {
    }

    @Override
    public void update(Flower flower) {
        flowerRepository.saveAndFlush(flower);
        LOG.debug("Number of Flower with id = {} has been changed to = {}",
                flower.getId(), flower.getNumber());
    }

    @Override
    public void updateAll(List<Flower> flowers) {
        flowerRepository.saveAll(flowers);
    }

    @Override
    public Flower get(Long id) {
        return flowerRepository.getOne(id);
    }

    @Override
    public List<Flower> getAll() {
        return flowerRepository.findAll();
    }

    @Override
    public List<Flower> getFlowerByNameOrMinPriceAndMaxPrice(
            String name,
            BigDecimal minFlowerPrice,
            BigDecimal maxFlowerPrice) {
        return flowerRepository.getFlowerByNameOrMinPriceAndMaxPrice(
                name,
                minFlowerPrice,
                maxFlowerPrice
        );
    }
}
