package com.accenture.flowershop.be.repository.flower;

import java.math.BigDecimal;
import java.util.List;

/**
 * Расширенный нтерфейс доступа к базе данных для сущности Flower.
 */
public interface CustomFlowerRepository<T> {
    /**
     * Найти цветы от минимальной и до максимальной цены.
     * @param min - минимальна цена
     * @param max - максимальная цена
     * @return List<Flower> если найдены, пустой List<Flower> если не найдены
     */
    List<T> getFlowerByMinPriceAndMaxPrice(BigDecimal min,BigDecimal max);
    /**
     * Найти цветы только дороже минимальной цены.
     * @param min - минимальна цена
     * @return List<Flower> если найдены, пустой List<Flower> если не найдены
     */
    List<T> getFlowerByMinPrice(BigDecimal min);
    /**
     * Найти цветы только дешевле максимальной цены.
     * @param max - максимальная цена
     * @return List<Flower> если найдены, пустой List<Flower> если не найдены
     */
    List<T> getFlowerByMaxPrice(BigDecimal max);
}
