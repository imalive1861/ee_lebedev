package com.accenture.flowershop.be.service.business.flower;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlowerBusinessService {
    void saveFlower(FlowerDTO flowerDTO);

    void delete(Flower flower);

    void updateFlower(FlowerDTO flowerDTO);

    List<FlowerDTO> getAll();

    FlowerDTO get(long id);
}
