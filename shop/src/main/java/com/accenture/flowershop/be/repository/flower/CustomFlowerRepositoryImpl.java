package com.accenture.flowershop.be.repository.flower;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.entity.QFlower;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

/**
 * Реализация интерфейса CustomFlowerRepository.
 */
@Repository
public class CustomFlowerRepositoryImpl implements CustomFlowerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Flower> getFlowerByNameOrMinPriceAndMaxPrice(String name, BigDecimal min, BigDecimal max) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QFlower flower = QFlower.flower;
        JPAQuery<Flower> path = queryFactory.selectFrom(flower);
        if (!"".equals(name)) {
            path = path.where(flower.name.containsIgnoreCase(name));
        }
        if (min != null) {
            path = path.where(flower.price.goe(min));
        }
        if (max != null) {
            path = path.where(flower.price.loe(max));
        }
        return path.fetch();
    }
}