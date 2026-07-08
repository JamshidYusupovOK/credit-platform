package org.integer.clientservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.integer.clientservice.entity.DocumentType;
import org.integer.clientservice.entity.EmploymentType;

import java.time.LocalDate;

@Data
public class CreateClientRequest {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Size(min = 14, max = 14)
    private String pinfl;
    @NotNull
    private DocumentType documentType;
    @NotBlank
    private String documentNumber;
    @NotNull
    private LocalDate documentIssueDate;
    @NotNull
    private LocalDate documentExpiryDate;
    @NotNull
    private LocalDate birthDate;
    @NotBlank
    private String phone;
    @NotBlank
    private String email;
    @NotBlank
    private String address;
    @NotNull
    private EmploymentType employmentType;
}
