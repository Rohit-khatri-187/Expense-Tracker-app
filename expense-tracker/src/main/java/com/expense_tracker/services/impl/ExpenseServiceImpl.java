package com.expense_tracker.services.impl;

import com.expense_tracker.config.CurrentUser;
import com.expense_tracker.dtos.ExpenseRequestDTO;
import com.expense_tracker.dtos.ExpenseResponseDTO;
import com.expense_tracker.entities.Expense;
import com.expense_tracker.entities.User;
import com.expense_tracker.entities.Wallet;
import com.expense_tracker.exceptions.NotAuthenticatedException;
import com.expense_tracker.exceptions.NotFoundException;
import com.expense_tracker.mapper.Mapper;
import com.expense_tracker.repositories.ExpenseRepository;
import com.expense_tracker.repositories.WalletRepository;
import com.expense_tracker.security.CustomUserDetails;
import com.expense_tracker.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final WalletRepository walletRepository;
    private final Mapper mapper;
    private final CurrentUser currentUser;

    @Override
    public ExpenseResponseDTO addExpense(ExpenseRequestDTO request) {

        log.info("Inside the add Expense service () : ");

        CustomUserDetails customUserDetails = currentUser.getCurrentUserData();
        User user = customUserDetails.getUser();

        if(user == null){
            throw new NotAuthenticatedException("Please login");
        }

        Wallet wallet = walletRepository.findWalletByIdAndUserId(request.getWalletId(), user.getUserId())
                .orElseThrow( () -> new NotFoundException("Wallet not found") );

        Expense newExpense = new Expense();
        newExpense.setExpenseName(request.getExpenseName());
        newExpense.setAmount(request.getAmount());
        newExpense.setExpenseDate(request.getExpenseDate());
        newExpense.setUser(user);
        newExpense.setWallet(wallet);

        Expense savedExpense = expenseRepository.save(newExpense);

        return mapper.toExpenseResponseDTO(savedExpense);
    }

    @Override
    public ExpenseResponseDTO updateExpense(Long expenseId , ExpenseRequestDTO request) {
        log.info("Inside the updateExpense service () : ");

        CustomUserDetails customUserDetails = currentUser.getCurrentUserData();
        User user = customUserDetails.getUser();

        if(user == null){
            throw new NotAuthenticatedException("Please login");
        }

        Wallet wallet = walletRepository.findWalletByIdAndUserId(request.getWalletId(), user.getUserId())
                .orElseThrow( () -> new NotFoundException("Wallet not found") );

        Expense existingExpense = expenseRepository.findByExpenseIdAndUserId(expenseId,user.getUserId())
                        .orElseThrow(() -> new NotFoundException("Expense not found") );

        existingExpense.setExpenseName(request.getExpenseName());
        existingExpense.setAmount(request.getAmount());
        existingExpense.setExpenseDate(request.getExpenseDate());
        existingExpense.setWallet(wallet);

        Expense updatedExpense = expenseRepository.save(existingExpense);

        return mapper.toExpenseResponseDTO(updatedExpense);
    }

    @Override
    public ExpenseResponseDTO deleteExpense(Long expenseId) {

        log.info("Inside the delete Expense service () : ");

        CustomUserDetails customUserDetails = currentUser.getCurrentUserData();
        User user = customUserDetails.getUser();

        if(user == null){
            throw new NotAuthenticatedException("Please login");
        }

        Expense expense = expenseRepository.findByExpenseIdAndUserId(expenseId,customUserDetails.getUserId())
                .orElseThrow(() -> new NotFoundException("Expense not found") );

        expenseRepository.delete(expense);

        return mapper.toExpenseResponseDTO(expense);
    }
}
