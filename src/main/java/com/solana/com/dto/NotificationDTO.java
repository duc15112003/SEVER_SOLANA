package com.solana.com.dto;


import com.solana.com.model.Account;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDTO {

    private Long id;

    private Account account;

    private String message;

    private LocalDate createdAt;

}
