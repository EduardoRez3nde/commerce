package com.rezende.commerce.dto;

import com.rezende.commerce.entities.Payment;
import com.rezende.commerce.entities.enums.OrderStatus;

import java.time.Instant;

public class PaymentDTO {

    private Long id;

    private Instant moment;

    private OrderStatus status;

    public PaymentDTO() {}

    public PaymentDTO(Long id, Instant moment) {
        this.id = id;
        this.moment = moment;
    }

    public PaymentDTO(Payment entity) {
        id = entity.getId();
        moment = entity.getMoment();
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }
}
