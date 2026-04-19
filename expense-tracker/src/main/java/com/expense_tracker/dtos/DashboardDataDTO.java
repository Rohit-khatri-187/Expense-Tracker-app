package com.expense_tracker.dtos;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardDataDTO {

    private String fullName;
    private Long totalIncome;
    private Long totalExpense;
    private Long balance;

}
