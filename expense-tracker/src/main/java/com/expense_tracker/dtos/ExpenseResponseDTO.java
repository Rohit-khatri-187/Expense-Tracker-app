package com.expense_tracker.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ExpenseResponseDTO {

    private Long expenseId;

    private String expenseName;

    private Long amount;

    private String walletName;

    private LocalDate expenseDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
