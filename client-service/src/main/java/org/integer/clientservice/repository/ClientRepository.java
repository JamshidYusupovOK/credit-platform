package org.integer.clientservice.repository;

import org.integer.clientservice.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAllByCreatedBy(Long userId);
    boolean existsByPinfl(String pinfl);

}
