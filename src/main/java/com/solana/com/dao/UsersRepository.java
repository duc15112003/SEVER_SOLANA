package com.solana.com.dao;

import com.solana.com.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
