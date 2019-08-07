package com.accenture.flowershop.be.repository;

import com.accenture.flowershop.be.entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface FlowerRepository extends JpaRepository<Flower, Long> {
    List<Flower> findAllFlowerByName(String name);
    @Query("select max(f.price) from Flower f")
    BigDecimal getFlowerMaxPrice();
    @Query("select f from Flower f where f.price >= :min and f.price <= :max")
    List<Flower> getFlowerByMinPriceAndMaxPrice(@Param("min") BigDecimal min,@Param("max") BigDecimal max);
}
