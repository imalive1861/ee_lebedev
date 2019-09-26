package com.accenture.flowershop.services.jms;

import java.io.Serializable;

/**
 * Вспомогательный класс, испозующийся для передачи ответа на запрос скидки для пользователя.
 */
public class Discount implements Serializable {
    /**
     * Идентификатор пользователя.
     */
    private String customerId;
    /**
     * Скидка.
     */
    private int discount;

    public Discount() {
    }

    public Discount(String customerId, int discount) {
        this.customerId = customerId;
        this.discount = discount;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }
}
