package org.integer.clientservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.integer.clientservice.converter.EncryptionConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name = "clients")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Convert(converter = EncryptionConverter.class)
    @Column(unique = true, nullable = false)
    private String pinfl;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Convert(converter = EncryptionConverter.class)
    @Column(unique = true, nullable = false)
    private String documentNumber;

    private LocalDate documentIssueDate;
    private LocalDate documentExpiryDate;
    private LocalDate birthDate;
    private String phoneNumber;
    private String address;
    private String email;

    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    private Long createdBy;
    @CreatedDate
    private LocalDate createdDate;
}
