package com.expense_tracker.services;

import com.expense_tracker.dtos.ExpenseRequestDTO;
import com.expense_tracker.dtos.ExpenseResponseDTO;

public interface ExpenseService {

    public ExpenseResponseDTO addExpense(ExpenseRequestDTO request);

    public ExpenseResponseDTO updateExpense(Long expenseId ,ExpenseRequestDTO request);

    public ExpenseResponseDTO deleteExpense(Long expenseId);

}
