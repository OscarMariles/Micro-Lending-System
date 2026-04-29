package com.microlending.micro_lending_system.mapper;

import com.microlending.micro_lending_system.dto.ClientDTO;
import com.microlending.micro_lending_system.dto.InstallmentDTO;
import com.microlending.micro_lending_system.dto.LoanDTO;
import com.microlending.micro_lending_system.model.Client;
import com.microlending.micro_lending_system.model.Installment;
import com.microlending.micro_lending_system.model.Loan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {

    public ClientDTO toDTO(Client c){
        if (c==null) return null;

        return ClientDTO.builder()
                .id(c.getId())
                .fullName(c.getFullName())
                .taxId(c.getTaxId())
                .email(c.getEmail())
                .build();
    }

    public LoanDTO toDTO(Loan l){
        if (l==null) return null;

        return LoanDTO.builder()
                .id(l.getId())
                .clientId(l.getClient().getId())
                .principalAmount(l.getPrincipalAmount())
                .interestRate(l.getInterestRate())
                .termInMonths(l.getTermInMonths())
                .status(l.getStatus().toString())
                .installments(l.getInstallments() != null ? l.getInstallments().stream().map(this::toDTO).toList() : List.of())
                .build();
    }

    public InstallmentDTO toDTO(Installment ins){
        if (ins==null) return null;

        return InstallmentDTO.builder()
                .installmentNumber(ins.getInstallmentNumber())
                .amount(ins.getAmount())
                .dueDate(ins.getDueDate().toString())
                .isPaid(ins.getIsPaid())
                .build();
    }
}
