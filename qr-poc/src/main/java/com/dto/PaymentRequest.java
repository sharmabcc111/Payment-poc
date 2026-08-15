package com.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    Long organisationId;
    BigDecimal amount;
}
