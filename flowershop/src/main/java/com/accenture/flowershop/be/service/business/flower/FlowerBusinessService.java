package com.accenture.flowershop.be.service.business.flower;

import com.accenture.flowershop.be.entity.Flower;

import java.util.List;

/**
 * Интерфейс бизнес логики для сущности Flower.
 */
public interface FlowerBusinessService {
    /**
     * Обновить информацию о цветке в базе данных.
     *
     * @param flower - объект Flower
     */
    void update(Flower flower);

    /**
     * Обновить информацию о всех цветках в базе данных.
     *
     * @param flowers - список объектов Flower
     */
    void updateAll(List<Flower> flowers);

    /**
     * Найти цветок по идентификатору.
     *
     * @param id - идентификатор цветка
     * @return объект Flower
     */
    Flower get(Long id);

    /**
     * Выбрать все цветы из базы данных
     *
     * @return список всех цветков
     */
    List<Flower> getAll();

    /**
     * Найти все цветы по названию.
     *
     * @param name - название цветка
     * @return список цветков
     */
    List<Flower> getFlowerByName(String name);

    /**
     * Найти цветы от минимальной и до максимальной цены.
     *
     * @param minFlowerPrice - минимальна цена
     * @param maxFlowerPrice - максимальная цена
     * @return List<Flower> если найдены, пустой List<Flower> если не найдены
     */
    List<Flower> getFlowerByPrice(String minFlowerPrice, String maxFlowerPrice);
}
