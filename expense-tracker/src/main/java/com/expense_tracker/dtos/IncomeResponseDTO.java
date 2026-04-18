package com.expense_tracker.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class IncomeResponseDTO {

    private Long incomeId;

    private String incomeName;

    private Long amount;

    private String walletName;

    private LocalDate incomeDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
