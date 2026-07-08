package org.integer.creditservice.service;

import lombok.RequiredArgsConstructor;
import org.integer.creditservice.messaging.CreditEventPublisher;
import org.integer.creditservice.repository.CreditApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final CreditApplicationRepository creditApplicationRepository;
    private final CreditEventPublisher eventPublisher;
    private final ClientServiceClient clientServiceClient
}
