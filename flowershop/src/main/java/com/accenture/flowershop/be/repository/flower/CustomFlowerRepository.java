package com.accenture.flowershop.be.repository.flower;

import java.math.BigDecimal;
import java.util.List;

public interface CustomFlowerRepository<T> {
    List<T> getFlowerByMinPriceAndMaxPrice(BigDecimal min,BigDecimal max);
    List<T> getFlowerByMinPrice(BigDecimal min);
    List<T> getFlowerByMaxPrice(BigDecimal max);
}
