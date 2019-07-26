package com.accenture.flowershop.fe.dto.mappers;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlowerMapper {
    FlowerDTO flowerToFlowerDto(Flower flower);
    Flower flowerDtoToFlower(FlowerDTO flowerDTO);
    List<FlowerDTO> flowerToFlowerDtos(List<Flower> flowers);
}
