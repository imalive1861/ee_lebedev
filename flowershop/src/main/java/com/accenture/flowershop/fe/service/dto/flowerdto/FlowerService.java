package com.accenture.flowershop.fe.service.dto.flowerdto;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.fe.dto.FlowerDTO;

import java.util.List;

public interface FlowerService {
    FlowerDTO toDto(Flower flower);
    Flower fromDto(FlowerDTO flowerDTO);
    List<FlowerDTO> toDtoList(List<Flower> flowers);
}
