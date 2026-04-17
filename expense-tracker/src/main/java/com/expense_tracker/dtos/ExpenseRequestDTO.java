package com.expense_tracker.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseRequestDTO {

    @Size(min = 3, max = 40 , message = "Expense Name should be between 3-30 characters")
    @NotNull(message = "Expense name should not be null")
    private String expenseName;

    @NotNull(message = "Amount should not be null")
    private Long amount;

    @NotNull(message = "Date should not be null")
    private LocalDate expenseDate;

    @NotNull(message = "Wallet Id should not be null")
    private Long walletId;

}
