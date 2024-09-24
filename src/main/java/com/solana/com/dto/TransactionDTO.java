package com.solana.com.dto;

import lombok.*;

@Data
public class TransactionDTO {
    private Long id;
    private String transactionType;
    private String description;
    private String status;
    private Long amount;
    private Long feeTransaction;
    private String timestamp;
    private AccountDTO account;

}
