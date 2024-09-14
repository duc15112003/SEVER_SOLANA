package com.solana.com.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdeaCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "createAt", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "deleteAt", nullable = false)
    private LocalDateTime deleteAt;

    @OneToMany(mappedBy = "category")
    private Set<IdeaCategoryMapping> ideaCategoryMappings;
}
