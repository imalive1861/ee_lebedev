package com.accenture.flowershop.be.repository.card;

import com.accenture.flowershop.be.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
