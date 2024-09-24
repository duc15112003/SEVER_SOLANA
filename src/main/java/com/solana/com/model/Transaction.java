package com.solana.com.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_type", length = 10)
    private String transactionType;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "status", length = 10)
    private String status;

    @Column(name = "amount", precision = 18, scale = 8)
    private Long amount;

    @Column(name = "feeTransaction", precision = 18, scale = 8)
    private Long feeTransaction;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private Account account;

}
