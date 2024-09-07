package com.solana.com.dao;

import com.solana.com.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
    @Query(value = "select feedback.* from Feedback feedback where feedback.username = ?1", nativeQuery = true)
    List<Feedback> findFeedbackByUsername(String username);
}
