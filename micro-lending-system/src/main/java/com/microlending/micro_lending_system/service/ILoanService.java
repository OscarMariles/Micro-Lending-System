package com.microlending.micro_lending_system.service;

import com.microlending.micro_lending_system.dto.LoanDTO;
import com.microlending.micro_lending_system.model.Installment;
import com.microlending.micro_lending_system.model.Loan;

import java.util.List;

public interface ILoanService {
    LoanDTO createLoan(LoanDTO loanDTO);
    LoanDTO approveLoan(Long loanId);
    LoanDTO rejectLoan(Long loanId);
    List<Installment> generateInstallments(Loan loan);
    List<LoanDTO> getLoansByClientId(Long clientId);
    void payInstallment(Long installmentId);
}
