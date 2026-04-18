package com.expense_tracker.services;

import com.expense_tracker.dtos.*;

public interface IncomeService {

    public IncomeResponseDTO addIncome(IncomeRequestDTO request);

    public IncomeResponseDTO updateIncome(Long incomeId ,IncomeRequestDTO request);

    public IncomeResponseDTO deleteIncome(Long incomeId);

    public PageResponse<IncomeResponseDTO> getIncomes(int page , int size , String sortBy);

}
