package com.accenture.flowershop.be.service.business.flower;

import com.accenture.flowershop.be.access.flower.FlowerAccess;
import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService{

    private static final Logger LOG = LoggerFactory.getLogger(FlowerBusinessServiceImpl.class);

    private FlowerAccess flowerAccess;

    private Map<Long, FlowerDTO> flowerDTOs;

    @Autowired
    public FlowerBusinessServiceImpl(FlowerAccess flowerAccess){
        this.flowerAccess = flowerAccess;
        flowerDTOs = new TreeMap<>();
        getAllFlowerToFlowerDTO();
    }

    public void saveFlower(FlowerDTO flowerDTO) {
        flowerAccess.saveFlower(toFlower(flowerDTO));
        LOG.debug("Order with name = {} price = {} was created", flowerDTO.getName(), flowerDTO.getPrice());
    }

    public void updateFlower(FlowerDTO flowerDTO){
        flowerAccess.update(toFlower(flowerDTO));
    }

    public void delete(Flower flower) {
        if(flower != null) {
            flowerAccess.delete(flower.getId());
        }
    }

    private Flower toFlower(FlowerDTO flowerDTO){
        Flower flower = flowerAccess.get(flowerDTO.getId());
        if(flower != null) {
            flower.setNumber(flowerDTO.getNumber());
            return flowerAccess.get(flowerDTO.getId());
        }
        return new Flower(flowerDTO.getName(),flowerDTO.getPrice(),
                flowerDTO.getNumber());
    }

    private FlowerDTO toFlowerDTO(Flower flower){
        if(flowerDTOs.get(flower.getId()) != null) {
            return flowerDTOs.get(flower.getId());
        }
        return new FlowerDTO(flower.getId(), flower.getName(),flower.getPrice(),
                flower.getNumber());
    }

    public void getAllFlowerToFlowerDTO(){
        flowerDTOs.clear();
        for (Flower f: flowerAccess.getAll()) {
            FlowerDTO flowerDTO = toFlowerDTO(f);
            flowerDTOs.put(flowerDTO.getId(), flowerDTO);
        }
    }

    public Map<Long, FlowerDTO> getAll() {
        return flowerDTOs;
    }

    public FlowerDTO get(long id) {
        if(id != 0) {
            return flowerDTOs.get(id);
        }
        return null;
    }

    public Flower getDAO(long id){
        return flowerAccess.get(id);
    }
}
