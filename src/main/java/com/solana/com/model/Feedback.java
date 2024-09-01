package com.solana.com.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "feedback", columnDefinition = "nvarchar(max)")
    private String feedback;

    @Column(name = "createAt")
    private Timestamp createAt;

    @Column(name = "status", length = 10)
    private String status;

    @Column(name = "rate")
    private Integer rate;

    @ManyToOne
    @JoinColumn(name = "username",nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "id_idea",nullable = false)
    private Ideas idea;

    @OneToMany(mappedBy = "feedback")
    private Set<Report> reports;
}
