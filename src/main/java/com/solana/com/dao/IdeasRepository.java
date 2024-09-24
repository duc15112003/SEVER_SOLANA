package com.solana.com.dao;

import com.solana.com.model.Ideas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IdeasRepository extends JpaRepository<Ideas,Long> {
    Ideas findIdeasById(Long id);

    @Query(value = "select idea.* from Ideas idea where idea.username = ?1", nativeQuery = true)
    List<Ideas> findIdeasByUsername(String username);

    @Query(value = "select idea.* from Ideas idea where idea.status = ?1", nativeQuery = true)
    List<Ideas> findIdeasByStatus(String status);
}
