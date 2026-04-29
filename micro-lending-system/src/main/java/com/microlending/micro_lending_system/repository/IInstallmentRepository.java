package com.microlending.micro_lending_system.repository;

import com.microlending.micro_lending_system.model.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IInstallmentRepository extends JpaRepository<Installment,Long> {
    @Query("SELECT i FROM Installment i JOIN FETCH i.loan l JOIN FETCH l.installments WHERE i.id = :id")
    Optional<Installment> findByIdWithLoanAndInstallments(@Param("id") Long id);

}
