package org.integer.clientservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.integer.clientservice.dto.ClientResponse;
import org.integer.clientservice.dto.CreateClientRequest;
import org.integer.clientservice.repository.ClientRepository;
import org.integer.clientservice.service.ClientService;
import org.integer.clientservice.service.JwtService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientRepository clientRepository;
    private final JwtService jwtService;

    @PostMapping
    public ClientResponse createClient(@Valid @RequestBody CreateClientRequest request,
                                       @RequestHeader("Authorization") String authHeader) {
        Long userID = jwtService.extractUserId(authHeader.substring(7));
        return clientService.createClient(request, userID);
    }

    @GetMapping("/{id}")
    public ClientResponse getClient(@PathVariable("id") Long id) {
        return clientService.getClientById(id);
    }

    @PutMapping("/{id}")
    public ClientResponse updateClient(@PathVariable Long id,
                                       @Valid @RequestBody CreateClientRequest request) {
        return clientService.updateClient(id, request);
    }

    @GetMapping("/user/{userId}")
    public List<ClientResponse> getClientsByUser(@PathVariable Long userId) {
        return clientService.getClientsByUser(userId);
    }
}
