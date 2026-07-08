package org.integer.creditservice.repository;

import org.integer.creditservice.entity.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Long> {
    List<CreditApplication> findAllByClientId(Long clientId);
    List<CreditApplication> findAllByUserId(Long userId);
}
