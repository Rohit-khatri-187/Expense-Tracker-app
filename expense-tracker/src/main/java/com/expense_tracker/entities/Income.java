package com.expense_tracker.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Table(name = "incomes")
@Entity
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incomeId;

    private String incomeName;

    private Long amount;

    @Column(nullable = false)
    private LocalDate incomeDate;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Wallet wallet;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
