package com.accenture.flowershop.be.repository.flower;

import com.accenture.flowershop.be.entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlowerRepository extends JpaRepository<Flower, Long>, CustomFlowerRepository<Flower> {
    List<Flower> findAllFlowerByName(String name);
}
