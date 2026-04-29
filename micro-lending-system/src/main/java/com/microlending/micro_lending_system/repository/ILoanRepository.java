package com.microlending.micro_lending_system.repository;

import com.microlending.micro_lending_system.model.Loan;
import com.microlending.micro_lending_system.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILoanRepository extends JpaRepository<Loan,Long> {
    List<Loan> findByClientId(Long clientID);
    int countByClientIdAndStatus(Long clientId, LoanStatus status);
}
