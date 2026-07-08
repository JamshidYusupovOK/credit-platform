package org.integer.creditservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table(name = "credit_applications")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplication {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;

    @Column(nullable=false)
    private Long clientId;

    @Column(nullable=false)
    private Long userId;

    @Column(nullable=false)
    private BigDecimal requestedAmount;

    @Column(nullable=false)
    private Integer termMonths;

    private String purpose;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    private Integer score;

    @Column(updatable = false)
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;


    @PrePersist
    public void prePersist()
    {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
        this.status = ApplicationStatus.PENDING;
    }
    @PreUpdate
    public void preUpdate()
    {
        this.updatedDate = LocalDateTime.now();
    }
}
