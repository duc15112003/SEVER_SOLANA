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

    @Column(name = "block_hash", length = 64)
    private String blockHash;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getFeeTransaction() {
        return feeTransaction;
    }

    public void setFeeTransaction(Long feeTransaction) {
        this.feeTransaction = feeTransaction;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
