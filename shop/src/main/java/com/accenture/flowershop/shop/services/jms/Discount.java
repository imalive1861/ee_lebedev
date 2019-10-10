package com.accenture.flowershop.shop.services.jms;

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

    public static class Builder {
        private Discount newDiscount;

        public Builder() {
            newDiscount = new Discount();
        }

        public Builder customerId(String customerId) {
            newDiscount.customerId = customerId;
            return this;
        }

        public Builder discount(Integer discount) {
            newDiscount.discount = discount;
            return this;
        }

        public Discount build() {
            return newDiscount;
        }
    }
}
