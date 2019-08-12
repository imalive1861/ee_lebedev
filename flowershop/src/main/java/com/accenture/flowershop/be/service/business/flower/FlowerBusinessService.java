package com.accenture.flowershop.be.service.business.flower;

import com.accenture.flowershop.fe.dto.FlowerDTO;

import java.math.BigDecimal;
import java.util.List;

public interface FlowerBusinessService {
    void update(FlowerDTO flowerDTO);

    List<FlowerDTO> getAll();

    List<FlowerDTO> getFlowerByName(String name);

    List<FlowerDTO> getFlowerByPrice(String minFlowerPrice, String maxFlowerPrice);

    FlowerDTO get(long id);
}
