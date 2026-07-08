package org.integer.clientservice.dto;

import lombok.Data;
import org.integer.clientservice.entity.DocumentType;
import org.integer.clientservice.entity.EmploymentType;

import java.time.LocalDate;

@Data
public class ClientResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String pinfl;
    private DocumentType documentType;
    private String documentNumber;
    private LocalDate documentIssueDate;
    private LocalDate documentExpiryDate;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private String address;
    private EmploymentType employmentType;
    private Long createdBy;
    private LocalDate createdAt;
}
