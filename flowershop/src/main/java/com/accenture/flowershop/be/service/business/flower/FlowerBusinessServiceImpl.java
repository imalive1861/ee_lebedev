package com.accenture.flowershop.be.service.business.flower;

import com.accenture.flowershop.be.access.flower.FlowerAccess;
import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.utils.LoggerUtils;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import com.accenture.flowershop.fe.dto.mappers.FlowerMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService{

    private Logger LOG = LoggerUtils.getLOG();

    private FlowerAccess flowerAccess;
    private FlowerMapper flowerMapper;

    @Autowired
    public FlowerBusinessServiceImpl(FlowerAccess flowerAccess, FlowerMapper flowerMapper){
        this.flowerAccess = flowerAccess;
        this.flowerMapper = flowerMapper;
    }

    public void saveFlower(FlowerDTO flowerDTO) {
        flowerAccess.saveFlower(flowerMapper.flowerDtoToFlower(flowerDTO));
    }

    public void updateFlower(FlowerDTO flowerDTO){
        flowerAccess.update(flowerMapper.flowerDtoToFlower(flowerDTO));
    }

    public void delete(Flower flower) {
        if(flower != null) {
            flowerAccess.delete(flower.getId());
        }
    }

    public FlowerDTO get(long id) {
        if(id != 0) {
            return flowerMapper.flowerToFlowerDto(flowerAccess.get(id));
        }
        return null;
    }

    @Override
    public List<FlowerDTO> getAll() {
        return flowerMapper.flowerToFlowerDtos(flowerAccess.getAll());
    }

    public List<FlowerDTO> getFlowerByName(String name) {
        return flowerMapper.flowerToFlowerDtos(flowerAccess.getFlowerByName(name));
    }

    public List<FlowerDTO> getFlowerByPrice(BigDecimal min, BigDecimal max) {
        return flowerMapper.flowerToFlowerDtos(flowerAccess.getFlowerByPrice(min,max));
    }
}
