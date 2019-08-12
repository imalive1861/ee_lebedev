package com.accenture.flowershop.be.service.business.flower;

import com.accenture.flowershop.be.repository.flower.FlowerRepository;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.dto.mappers.FlowerMapper;
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
    private FlowerMapper flowerMapper;
    @Autowired
    private Logger LOG;

    public FlowerBusinessServiceImpl(){}

    public void update(FlowerDTO flowerDTO){
        flowerRepository.saveAndFlush(flowerMapper.flowerDtoToFlower(flowerDTO));
        LOG.debug("Number of Flower with id = {} has been changed to = {}",
                flowerDTO.getId(), flowerDTO.getNumber());
    }

    public FlowerDTO get(long id) {
        if(id != 0) {
            return flowerMapper.flowerToFlowerDto(flowerRepository.getOne(id));
        }
        return null;
    }

    public List<FlowerDTO> getAll() {
        return flowerMapper.flowerToFlowerDtos(flowerRepository.findAll());
    }

    public List<FlowerDTO> getFlowerByName(String name) {
        return flowerMapper.flowerToFlowerDtos(flowerRepository.findAllFlowerByName(name));
    }

    public List<FlowerDTO> getFlowerByPrice(BigDecimal min, BigDecimal max) {
        return flowerMapper.flowerToFlowerDtos(flowerRepository.getFlowerByMinPriceAndMaxPrice(min,max));
    }

    public BigDecimal getFlowerMaxPrice(){
        return flowerRepository.getFlowerMaxPrice();
    }
}
