package com.solana.com.dao;

import com.solana.com.model.Notification;
import com.solana.com.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
