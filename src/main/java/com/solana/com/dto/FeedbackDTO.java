package com.solana.com.dto;

import com.solana.com.model.Account;
import com.solana.com.model.Ideas;
import com.solana.com.model.Report;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackDTO {


    private Long id;

    private String feedback;

    private LocalDate createAt;

    private String status;

    private Integer rate;

    private Account account;

    private Ideas idea;

   private Set<Report> reports;
}
