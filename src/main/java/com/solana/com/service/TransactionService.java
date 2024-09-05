package com.solana.com.service;

import com.solana.com.dao.AccountRepository;
import com.solana.com.dao.TransactionRepository;
import com.solana.com.dto.FeedbackDTO;
import com.solana.com.dto.TransactionDTO;
import com.solana.com.mapper.TransactionMapper;
import com.solana.com.model.Feedback;
import com.solana.com.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    public Page<TransactionDTO> getAll(PageRequest pageRequest) {
        Page<Transaction> transactionsList = transactionRepository.findAll(pageRequest);
        List<TransactionDTO> transactionDTOlist = new ArrayList<TransactionDTO>();
        for (Transaction tran : transactionsList.getContent()) {
            TransactionDTO tranDTO = transactionMapper.toTransactionDTO(tran);
            transactionDTOlist.add(tranDTO);
        }

        return new PageImpl<>(transactionDTOlist,transactionsList.getPageable(),transactionsList.getTotalElements());
    }

    public TransactionDTO getTransactionById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.map(value -> transactionMapper.toTransactionDTO(transaction.get())).orElse(null);
    }

    public TransactionDTO save(TransactionDTO transactionDTO) {
        transactionDTO.setId(null);
        Transaction transaction = transactionMapper.toTransaction(transactionDTO);
        transaction.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        System.out.println(transaction.toString());
        transaction.setAccount(accountRepository.findById(transactionDTO.getAccount()).orElse(null));
        if (transaction.getAccount() == null) {
            return null;
        } else {
            return transactionMapper.toTransactionDTO(transactionRepository.save(transaction));
        }
    }

    public TransactionDTO update(TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.toTransaction(transactionDTO);
        transaction.setAccount(accountRepository.findById(transactionDTO.getAccount()).orElse(null));
        if (transaction.getAccount() == null) {
            return null;
        } else {
            return transactionMapper.toTransactionDTO(transactionRepository.save(transaction));
        }
    }

    public boolean delete(Long id) {
        if (transactionRepository.findById(id).isPresent()) {
            transactionRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}