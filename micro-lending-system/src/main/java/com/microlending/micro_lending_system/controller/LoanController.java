package com.microlending.micro_lending_system.controller;

import com.microlending.micro_lending_system.dto.LoanDTO;
import com.microlending.micro_lending_system.service.ILoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private ILoanService loanService;

    @PostMapping
    public ResponseEntity<LoanDTO> createLoan(@Valid @RequestBody LoanDTO loanDTO){
        LoanDTO created=loanService.createLoan(loanDTO);

        return ResponseEntity.created(URI.create("/api/loans/"+created.getId())).body(created);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<LoanDTO>> getLoansByIdClient(@PathVariable Long clientId){
        List<LoanDTO> loans=loanService.getLoansByClientId(clientId);

        return loans.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(loans);
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<LoanDTO> approveLoan(@PathVariable Long id){
        return ResponseEntity.ok(loanService.approveLoan(id));
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<LoanDTO> rejectLoan(@PathVariable Long id){
        return ResponseEntity.ok(loanService.rejectLoan(id));
    }
}
