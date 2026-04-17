package com.expense_tracker.mapper;

import com.expense_tracker.dtos.ExpenseResponseDTO;
import com.expense_tracker.dtos.RegistrationResponseDTO;
import com.expense_tracker.dtos.WalletResponseDTO;
import com.expense_tracker.entities.Expense;
import com.expense_tracker.entities.User;
import com.expense_tracker.entities.Wallet;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public RegistrationResponseDTO toRegistrationResponse(User user){

        return RegistrationResponseDTO.builder()
                .id(user.getUserId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();

    }

    public WalletResponseDTO toWalletResponse(Wallet wallet){
        return WalletResponseDTO.builder()
                .walletId(wallet.getWalletId())
                .walletName(wallet.getWalletName())
                .createdAt(wallet.getCreatedAt())
                .updatedAt(wallet.getUpdatedAt())
                .build();
    }

    public ExpenseResponseDTO toExpenseResponseDTO(Expense expense){
        return ExpenseResponseDTO.builder()
                .expenseId(expense.getExpenseId())
                .expenseName(expense.getExpenseName())
                .amount(expense.getAmount())
                .walletName(expense.getWallet().getWalletName())
                .expenseDate(expense.getExpenseDate())
                .createdAt(expense.getCreatedAt())
                .updatedAt(expense.getUpdatedAt())
                .build();
    }

}
