package com.microlending.micro_lending_system.repository;

import com.microlending.micro_lending_system.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByTaxId(String taxId);
    List<Client> findByActiveTrue();
}
