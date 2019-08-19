package com.accenture.flowershop.be.repository.flower;

import com.accenture.flowershop.be.entity.Flower;
import com.accenture.flowershop.be.entity.QFlower;
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
    public List<Flower> getFlowerByMinPriceAndMaxPrice(BigDecimal min, BigDecimal max) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QFlower flower = QFlower.flower;
        return queryFactory.selectFrom(flower)
                .where(flower.price.goe(min).and(flower.price.loe(max)))
                .fetch();
    }

    @Override
    public List getFlowerByMinPrice(BigDecimal min) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QFlower flower = QFlower.flower;
        return queryFactory.selectFrom(flower)
                .where(flower.price.goe(min))
                .fetch();
    }

    @Override
    public List getFlowerByMaxPrice(BigDecimal max) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QFlower flower = QFlower.flower;
        return queryFactory.selectFrom(flower)
                .where(flower.price.loe(max))
                .fetch();
    }
}