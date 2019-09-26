package com.accenture.flowershop.shop.be.repository.flower;

import com.accenture.flowershop.shop.be.entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс доступа к базе данных для сущности Flower.
 */
public interface FlowerRepository extends JpaRepository<Flower, Long>, CustomFlowerRepository<Flower> {
}
