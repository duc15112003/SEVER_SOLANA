package com.solana.com.mapper;

import com.solana.com.dto.TransactionDTO;
import com.solana.com.model.Transaction;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring",unmappedSourcePolicy = WARN,uses = {AccountMapper.class})
public interface TransactionMapper {

    TransactionDTO toTransactionDTO(Transaction transaction);

    Transaction toTransaction(TransactionDTO transactionDTO);

}
