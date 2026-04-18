package com.expense_tracker.services;

import com.expense_tracker.dtos.ExpenseRequestDTO;
import com.expense_tracker.dtos.ExpenseResponseDTO;
import com.expense_tracker.dtos.PageResponse;

public interface ExpenseService {

    public ExpenseResponseDTO addExpense(ExpenseRequestDTO request);

    public ExpenseResponseDTO updateExpense(Long expenseId ,ExpenseRequestDTO request);

    public ExpenseResponseDTO deleteExpense(Long expenseId);

    public PageResponse<ExpenseResponseDTO> getExpenses(int page , int size , String sortBy);

}
