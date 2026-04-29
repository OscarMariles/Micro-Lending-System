package com.microlending.micro_lending_system.service;

import com.microlending.micro_lending_system.dto.LoanDTO;
import com.microlending.micro_lending_system.exception.InstallmentAlreadyPaid;
import com.microlending.micro_lending_system.exception.NotFoundException;
import com.microlending.micro_lending_system.mapper.Mapper;
import com.microlending.micro_lending_system.model.Client;
import com.microlending.micro_lending_system.model.Installment;
import com.microlending.micro_lending_system.model.Loan;
import com.microlending.micro_lending_system.model.LoanStatus;
import com.microlending.micro_lending_system.repository.IClientRepository;
import com.microlending.micro_lending_system.repository.IInstallmentRepository;
import com.microlending.micro_lending_system.repository.ILoanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService implements ILoanService{

    private static final BigDecimal AUTO_APPROVAL_MAX_AMOUNT =new BigDecimal("25000");
    private static final int MAX_OVERDUE_LOANS_ALLOWED=1;

    @Autowired
    ILoanRepository loanRepo;

    @Autowired
    IClientRepository clientRepo;

    @Autowired
    IInstallmentRepository installmentRepo;

    @Autowired
    private Mapper mapper;

    @Override
    @Transactional
    public LoanDTO createLoan(LoanDTO loanDTO) {

        // 1. Validar que el cliente existe y está activo
        Client client=clientRepo.findById(loanDTO.getClientId()).filter(Client::getActive).orElseThrow(()->new NotFoundException("Cliente no encontrado o inactivo"));

        // 2. Evaluar reglas de aprobacion semi-automatica
        boolean approvableAmount=loanDTO.getPrincipalAmount().compareTo(AUTO_APPROVAL_MAX_AMOUNT)<=0;
        int expiredLoans=loanRepo.countByClientIdAndStatus(client.getId(),LoanStatus.OVERDUE);
        boolean historyApprovable=expiredLoans <= MAX_OVERDUE_LOANS_ALLOWED;

        boolean automaticApproval=approvableAmount && historyApprovable;

        // 3. Crear la entidad Loan
        Loan loan=Loan.builder()
                .client(client)
                .principalAmount(loanDTO.getPrincipalAmount())
                .interestRate(loanDTO.getInterestRate())
                .termInMonths(loanDTO.getTermInMonths())
                .startDate(LocalDate.now())
                .status(automaticApproval ? LoanStatus.ACTIVE : LoanStatus.PENDING_APPROVAL)
                .deleted(false)
                .build();

        // 4. Generar lista de cuotas solo si fue aprovado automaticamente
        if(automaticApproval){
            loan.setInstallments(generateInstallments(loan));
        }

        Loan savedLoan=loanRepo.save(loan);

        return mapper.toDTO(savedLoan);
    }

    @Override
    public LoanDTO approveLoan(Long loanId){
        Loan loan=loanRepo.findById(loanId).orElseThrow(()-> new NotFoundException("Préstamo con ID " + loanId + " no encontrado"));

        if (loan.getStatus() != LoanStatus.PENDING_APPROVAL){
            throw new IllegalStateException("Solo se pueden aprobar préstamos en estado PENDING_APPROVAL");
        }

        loan.setStatus(LoanStatus.ACTIVE);
        loan.setStartDate(LocalDate.now());
        loan.setInstallments(generateInstallments(loan));

        return mapper.toDTO(loanRepo.save(loan));
    }

    @Override
    public LoanDTO rejectLoan(Long loanId) {
        Loan loan=loanRepo.findById(loanId).orElseThrow(()->new NotFoundException("Préstamo con ID " + loanId + " no encontrado"));

        if (loan.getStatus() != LoanStatus.PENDING_APPROVAL){
            throw new IllegalStateException("Solo se pueden aprobar préstamos en estado PENDING_APPROVAL");
        }

        loan.setStatus(LoanStatus.REJECTED);
        return mapper.toDTO(loanRepo.save(loan));
    }

    @Override
    public List<Installment> generateInstallments(Loan loan) {
        BigDecimal interestMultiplier = BigDecimal.valueOf(1 + (loan.getInterestRate() / 100));
        BigDecimal totalAmount = loan.getPrincipalAmount().multiply(interestMultiplier);
        BigDecimal installmentAmount = totalAmount.divide(BigDecimal.valueOf(loan.getTermInMonths()), 2, RoundingMode.HALF_UP);

        List<Installment> installments=new ArrayList<>();

        for (int i=1;1<= loan.getTermInMonths();i++){
            installments.add(Installment.builder()
                    .installmentNumber(i)
                    .amount(installmentAmount)
                    .dueDate(loan.getStartDate().plusMonths(i))
                    .isPaid(false)
                    .loan(loan)
                    .build());
        }
        return installments;
    }

    @Override
    public List<LoanDTO> getLoansByClientId(Long clientId) {
        return loanRepo.findByClientId(clientId).stream().map(mapper::toDTO).toList();
    }

    @Override
    @Transactional
    public void payInstallment(Long installmentId) {

        Installment installment=installmentRepo.findByIdWithLoanAndInstallments(installmentId).orElseThrow(()->new NotFoundException("Cuota con ID " + installmentId + " no encontrada"));

        if (Boolean.TRUE.equals(installment.getIsPaid())){
            throw new InstallmentAlreadyPaid("La cuota ya fue liquidada anteriormente.");
        }

        installment.setIsPaid(true);
        installment.setPaymentDate(LocalDate.now());
        installmentRepo.save(installment);

        Loan loan=installment.getLoan();
        boolean allPaid=loan.getInstallments().stream().allMatch(Installment::getIsPaid);
        if (allPaid){
            loan.setStatus(LoanStatus.PAID);
            loanRepo.save(loan);
        }

    }
}
