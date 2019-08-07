package com.accenture.flowershop.be.repository;

import com.accenture.flowershop.be.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
