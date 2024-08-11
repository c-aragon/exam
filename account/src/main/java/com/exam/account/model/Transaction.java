package com.exam.account.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "transaction")
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    @SequenceGenerator(allocationSize = 1, name = "transaction_seq", sequenceName = "transaction_seq")
    @Column(name = "id_transaction", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_account", nullable = false)
    private Account account;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name="balance", nullable = false)
    private BigDecimal balance;

    @Column(name="old_balance", nullable = false)
    private BigDecimal oldBalance;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusTransaction statusTransaction;

    @Column(name = "transaction_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

}
