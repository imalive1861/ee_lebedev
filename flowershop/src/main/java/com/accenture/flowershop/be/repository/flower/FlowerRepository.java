package com.accenture.flowershop.be.repository.flower;

import com.accenture.flowershop.be.entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Интерфейс доступа к базе данных для сущности Flower.
 */
public interface FlowerRepository extends JpaRepository<Flower, Long>, CustomFlowerRepository<Flower> {

    /**
     * Найти все цветы по названию.
     * @param name - название цветка
     * @return список цветков
     */
    List<Flower> findAllFlowerByName(String name);
}
