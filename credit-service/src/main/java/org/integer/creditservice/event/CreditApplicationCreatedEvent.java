package org.integer.creditservice.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditApplicationCreatedEvent {
    private Long applicationId;
    private Long clientId;
    private Long userId;
    private String pinfl;
    private String cardNumber;
    private BigDecimal requestedAmount;
    private Integer termMonths;
}
