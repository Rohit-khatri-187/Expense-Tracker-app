package com.expense_tracker.services.impl;

import com.expense_tracker.config.CurrentUser;
import com.expense_tracker.dtos.DashboardDataDTO;
import com.expense_tracker.entities.Expense;
import com.expense_tracker.entities.Income;
import com.expense_tracker.entities.User;
import com.expense_tracker.exceptions.NotAuthenticatedException;
import com.expense_tracker.repositories.ExpenseRepository;
import com.expense_tracker.repositories.IncomeRepository;
import com.expense_tracker.services.DashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DashboardServiceImpl implements DashboardService {

    private final ExpenseRepository expenseRepository;
    private final IncomeRepository incomeRepository;
    private final CurrentUser currentUser;

    @Override
    public DashboardDataDTO getCurrentUserDashboardData() {

        log.info("Inside the getCurrentUserDashboardData () : ");

        User user = currentUser.getCurrentUserData();

        if(user == null){
            throw new NotAuthenticatedException("Please Login");
        }

        List<Expense> expenses = expenseRepository.getAllExpenses(user.getUserId());
        List<Income> incomes = incomeRepository.getAllIncome(user.getUserId());

        long income = 0;
        long expense = 0;

        for(Expense e: expenses){
            expense += e.getAmount();
        }

        for(Income i : incomes){
            income += i.getAmount();
        }

        long balance = income - expense;

        return DashboardDataDTO.builder()
                .fullName(user.getFullName())
                .totalIncome(income)
                .totalExpense(expense)
                .balance(balance)
                .build();
    }
}
