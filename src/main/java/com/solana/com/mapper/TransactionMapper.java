package com.solana.com.mapper;

import com.solana.com.dto.TransactionDTO;
import com.solana.com.model.Account;
import com.solana.com.model.Transaction;
import com.solana.com.util.FormatDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.sql.Timestamp;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {

    @Mapping(target = "timestamp", qualifiedByName = "timestampToString")
    @Mapping(target = "account", qualifiedByName = "accountToString")
    TransactionDTO toTransactionDTO(Transaction transaction);

    @Mapping(target = "timestamp", qualifiedByName = "stringToTimestamp",ignore = true)
    @Mapping(target = "account",ignore = true)
    Transaction toTransaction(TransactionDTO transactionDTO);

    @Named("timestampToString")
    default String timestampToString(Timestamp timestamp){
        return timestamp == null ? null : FormatDate.formatTimestampToString(timestamp);
    }
    @Named("stringToTimestamp")
    default Timestamp stringToTimestamp(String string) {
        return string == null ? null : Timestamp.valueOf(string);
    }

    @Named("accountToString")
    default String accountToString(Account account) {
        return account == null ? null : account.getUsername();
    }

}
