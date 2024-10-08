package com.solana.com.service;

import com.solana.com.dao.AccountRepository;
import com.solana.com.dto.AccountDTO;
import com.solana.com.mapper.AccountMapper;
import com.solana.com.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountMapper accountMapper;

    public Page<AccountDTO> getAll(PageRequest pageRequest) {
        Page<Account> accountPage = accountRepository.findAll(pageRequest);
        List<AccountDTO> accountDTOlist = new ArrayList<AccountDTO>();
        for (Account acc : accountPage.getContent()) {
            AccountDTO accDTO = accountMapper.toAccountDTO(acc);
            accountDTOlist.add(accDTO);
        }
        return  new PageImpl<>(accountDTOlist, accountPage.getPageable(), accountPage.getTotalElements());
    }

    public AccountDTO getAccountById(String username) {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            return null; // hoặc ném một ngoại lệ nếu cần
        }
        return accountMapper.toAccountDTO(account);
    }


    public AccountDTO save(AccountDTO accountDTO) {
        Account account = accountMapper.toAccount(accountDTO);
        return accountMapper.toAccountDTO(accountRepository.save(account));
    }

    public AccountDTO update(AccountDTO AccountDTO) {
        Account account = accountMapper.toAccount(AccountDTO);
        return accountMapper.toAccountDTO(accountRepository.save(account));
    }

    public Account findAccount(String username) {
        Account account = accountRepository.findById(username).get();
        return account;
    }

    public boolean delete(String id) {
        if(accountRepository.findById(id).isPresent()){
            accountRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
