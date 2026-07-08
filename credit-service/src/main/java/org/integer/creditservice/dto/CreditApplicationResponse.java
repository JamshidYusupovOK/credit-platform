package org.integer.creditservice.dto;


import lombok.Data;
import org.integer.creditservice.entity.ApplicationStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreditApplicationResponse {

    private Long id;
    private Long clientId;
    private Long userId;
    private BigDecimal requestedAmount;
    private Integer termMonths;
    private String purpose;
    private ApplicationStatus status;
    private Integer score;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
