package com.accenture.flowershop.shop.fe.service.dto.flowerdto;

import com.accenture.flowershop.shop.be.entity.Flower;
import com.accenture.flowershop.shop.fe.dto.FlowerDTO;import com.accenture.flowershop.shop.be.entity.Flower;import com.accenture.flowershop.shop.fe.dto.FlowerDTO;

import java.util.List;
import java.util.Set;

/**
 * Утилитарный класс транспортного уровня для сущности FlowerDTO.
 */
public interface FlowerDtoService {
    /**
     * Переводит сущность Flower в FlowerDTO.
     *
     * @param flower - объект Flower
     * @return объект FlowerDTO
     */
    FlowerDTO toDto(Flower flower);

    /**
     * Переводит FlowerDTO в сущность Flower.
     *
     * @param flowerDTO - объект FlowerDTO
     * @return объект Flower
     */
    Flower fromDto(FlowerDTO flowerDTO);

    /**
     * Переводит множество Flower в список FlowerDTO.
     *
     * @param flowers - множество Flower
     * @return список FlowerDTO
     */
    List<FlowerDTO> toDtoList(Set<Flower> flowers);
}
