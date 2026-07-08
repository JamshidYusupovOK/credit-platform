package org.integer.clientservice.service;


import lombok.RequiredArgsConstructor;
import org.integer.clientservice.dto.ClientResponse;
import org.integer.clientservice.dto.CreateClientRequest;
import org.integer.clientservice.entity.Client;
import org.integer.clientservice.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientResponse createClient(CreateClientRequest request, Long userId) {
        if (clientRepository.existsByPinfl(request.getPinfl())) {
            throw new RuntimeException("Client with PINF " + request.getPinfl() + " already exists");
        }

        Client client = Client.builder().
                firstName(request.getFirstName()).
                lastName(request.getLastName()).
                pinfl(request.getPinfl()).
                documentType(request.getDocumentType()).
                documentNumber(request.getDocumentNumber()).
                documentIssueDate(request.getDocumentIssueDate()).
                documentExpiryDate(request.getDocumentExpiryDate()).
                birthDate(request.getBirthDate()).
                phoneNumber(request.getPhone()).
                email(request.getEmail()).
                address(request.getAddress()).
                employmentType(request.getEmploymentType()).
                createdBy(userId).
                build();

        clientRepository.save(client);

        return toResponse(client);
    }

    public ClientResponse getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return toResponse(client);
    }

    public List<ClientResponse> getClientsByUser(Long userId) {
        return clientRepository.findAllByCreatedBy(userId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ClientResponse updateClient(Long id, CreateClientRequest request) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setPinfl(request.getPinfl());
        client.setDocumentType(request.getDocumentType());
        client.setDocumentNumber(request.getDocumentNumber());
        client.setDocumentIssueDate(request.getDocumentIssueDate());
        client.setDocumentExpiryDate(request.getDocumentExpiryDate());
        client.setBirthDate(request.getBirthDate());
        client.setPhoneNumber(request.getPhone());
        client.setEmail(request.getEmail());
        client.setAddress(request.getAddress());
        client.setEmploymentType(request.getEmploymentType());
        return toResponse(clientRepository.save(client));
    }



    private ClientResponse toResponse(Client client) {
        ClientResponse response = new ClientResponse();
        response.setId(client.getId());
        response.setFirstName(client.getFirstName());
        response.setLastName(client.getLastName());
        response.setPinfl(client.getPinfl());
        response.setDocumentType(client.getDocumentType());
        response.setDocumentNumber(client.getDocumentNumber());
        response.setDocumentIssueDate(client.getDocumentIssueDate());
        response.setDocumentExpiryDate(client.getDocumentExpiryDate());
        response.setBirthDate(client.getBirthDate());
        response.setPhone(client.getPhoneNumber());
        response.setEmail(client.getEmail());
        response.setAddress(client.getAddress());
        response.setEmploymentType(client.getEmploymentType());
        response.setCreatedBy(client.getCreatedBy());
        response.setCreatedAt(client.getCreatedDate());
        return response;
    }
}
