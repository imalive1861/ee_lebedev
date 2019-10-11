package com.accenture.flowershop.discount.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Вспомогательный класс, испозующийся для передачи ответа на запрос скидки для пользователя.
 */
@XStreamAlias("Discount")
public class Discount {
    /**
     * Идентификатор пользователя.
     */
    @XStreamAlias("customerId")
    private String customerId;
    /**
     * Скидка.
     */
    @XStreamAlias("discount")
    private int discount;

    public Discount() {
    }

    public Discount(String customerId, int discount) {
        this.customerId = customerId;
        this.discount = discount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
