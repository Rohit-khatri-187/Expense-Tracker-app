package com.expense_tracker.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeRequestDTO {

    @Size(min = 3, max = 40 , message = "Income Name should be between 3-30 characters")
    @NotNull(message = "Income name should not be null")
    private String incomeName;

    @NotNull(message = "Amount should not be null")
    private Long amount;

    @NotNull(message = "Date should not be null")
    private LocalDate incomeDate;

    @NotNull(message = "Wallet Id should not be null")
    private Long walletId;

}
