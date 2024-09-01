package com.solana.com.service;

import com.solana.com.dao.AccountRepository;
import com.solana.com.dao.TransactionRepository;
import com.solana.com.dto.TransactionDTO;
import com.solana.com.mapper.TransactionMapper;
import com.solana.com.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<TransactionDTO> getAll() {
        List<Transaction> transactionList = transactionRepository.findAll();
        List<TransactionDTO> transactionDTOlist = new ArrayList<TransactionDTO>();
        for (Transaction acc : transactionList) {
            TransactionDTO accDTO = transactionMapper.toTransactionDTO(acc);
            transactionDTOlist.add(accDTO);
        }
        return transactionDTOlist;
    }

    public TransactionDTO getTransactionById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.map(value -> transactionMapper.toTransactionDTO(transaction.get())).orElse(null);
    }

    public TransactionDTO save(TransactionDTO transactionDTO) {
        System.out.println(transactionDTO.toString());
        Transaction transaction = transactionMapper.toTransaction(transactionDTO);
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