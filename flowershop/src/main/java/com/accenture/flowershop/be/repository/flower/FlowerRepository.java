package com.accenture.flowershop.be.repository.flower;

import com.accenture.flowershop.be.entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс доступа к базе данных для сущности Flower.
 */
public interface FlowerRepository extends JpaRepository<Flower, Long>, CustomFlowerRepository<Flower> {
}
