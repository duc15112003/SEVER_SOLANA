package com.solana.com.dto;

public class TransactionDTO {
        private Long id;
        private String transactionType;
        private String description;
        private String status;
        private Long amount;
        private Long feeTransaction;
        private String timestamp;
        private String blockHash;
        private String account;
        
    public TransactionDTO() {
    }

    public TransactionDTO(Long id, String transactionType, String description, String status, Long amount, Long feeTransaction, String timestamp, String blockHash, String account) {
        this.id = id;
        this.transactionType = transactionType;
        this.description = description;
        this.status = status;
        this.amount = amount;
        this.feeTransaction = feeTransaction;
        this.timestamp = timestamp;
        this.blockHash = blockHash;
        this.account = account;
    }

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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", transactionType='" + transactionType + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                ", feeTransaction=" + feeTransaction +
                ", timestamp='" + timestamp + '\'' +
                ", blockHash='" + blockHash + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
