package com.accenture.flowershop.be.repository.flower;

import java.math.BigDecimal;
import java.util.List;

public interface CustomFlowerRepository<T> {
    BigDecimal getFlowerMaxPrice();
    List<T> getFlowerByMinPriceAndMaxPrice(BigDecimal min,BigDecimal max);
}
