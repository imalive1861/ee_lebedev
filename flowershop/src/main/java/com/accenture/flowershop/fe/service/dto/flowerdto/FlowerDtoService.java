package com.accenture.flowershop.fe.service.dto.flowerdto;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.fe.dto.FlowerDTO;

import java.util.List;
import java.util.Set;

public interface FlowerDtoService {
    FlowerDTO toDto(Flower flower);

    Flower fromDto(FlowerDTO flowerDTO);

    List<FlowerDTO> toDtoList(Set<Flower> flowers);
}
