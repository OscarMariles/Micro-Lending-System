package com.microlending.micro_lending_system.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "loans")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal principalAmount;
    private Double interestRate;
    private Integer termInMonths;
    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    private Boolean deleted=false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "loan",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Installment> installments;

    public BigDecimal getPendingBalance(){
        if (installments==null) return BigDecimal.ZERO;

        return installments.stream().filter(i->!i.getIsPaid()).map(Installment::getAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
    }
}
