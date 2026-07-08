package org.integer.creditservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreditApplicationRequest {

    @NotNull
    private Long clientId;

    @NotNull
    @Positive
    private BigDecimal requestedAmount;

    @NotNull
    @Positive
    private Integer termMonths;

    private String purpose;

    @NotNull
    private String cardNumber;
}