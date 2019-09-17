package com.accenture.flowershop.be.repository.flower;

import com.accenture.flowershop.be.entity.Flower;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Интерфейс доступа к базе данных для сущности Flower.
 */
public interface FlowerRepository extends MongoRepository<Flower, Long>, CustomFlowerRepository<Flower> {
}
