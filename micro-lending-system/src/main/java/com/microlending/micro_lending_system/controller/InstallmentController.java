package com.microlending.micro_lending_system.controller;

import com.microlending.micro_lending_system.service.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/installments")
public class InstallmentController {

    @Autowired
    private ILoanService loanService;

    @PatchMapping("/{id}/pay")
    public ResponseEntity<Void> payInstallment(@PathVariable Long id){
        loanService.payInstallment(id);

        return ResponseEntity.noContent().build();
    }

}
