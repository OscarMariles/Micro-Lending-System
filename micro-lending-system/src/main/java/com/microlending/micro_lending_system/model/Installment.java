package com.microlending.micro_lending_system.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter @Setter
@Table(name = "Installments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer installmentNumber;
    private BigDecimal amount;
    private LocalDate dueDate;

    private Boolean isPaid=false;
    private LocalDate paymentDate;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "loan_id")
    private Loan loan;

    //Verifica si la cuota esta vencida comparando con la fecha actual
    public Boolean isOverdue(){
        return !isPaid && dueDate.isBefore(LocalDate.now());
    }
}
