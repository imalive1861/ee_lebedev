package com.accenture.flowershop.be.repository.flower;

import java.math.BigDecimal;
import java.util.List;

/**
 * Расширенный нтерфейс доступа к базе данных для сущности Flower.
 */
public interface CustomFlowerRepository<T> {
    /**
     * Найти цветки по имени и/или минимальной и максимальной заданных стоимостях.
     *
     * @param name - название цветка
     * @param min  - минимальна цена
     * @param max  - максимальная цена
     * @return List<Flower> если найдены, пустой List<Flower> если не найдены
     */
    List<T> getFlowerByNameOrMinPriceAndMaxPrice(String name, BigDecimal min, BigDecimal max);
}
