package com.solana.com.dao;

import com.solana.com.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findById(String username);

    Account findByUsername(String username);
}
