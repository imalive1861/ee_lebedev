package com.accenture.flowershop.fe.service.dto.flowerdto;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FlowerDtoServiceImpl implements FlowerDtoService {

    @Autowired
    private Mapper mapper;

    @Override
    public FlowerDTO toDto(Flower flower) {
        return mapper.map(flower, FlowerDTO.class);
    }

    @Override
    public Flower fromDto(FlowerDTO flowerDTO) {
        return mapper.map(flowerDTO, Flower.class);
    }

    @Override
    public List<FlowerDTO> toDtoList(Set<Flower> flowers) {
        return flowers.stream().map(this::toDto).collect(Collectors.toList());
    }
}
