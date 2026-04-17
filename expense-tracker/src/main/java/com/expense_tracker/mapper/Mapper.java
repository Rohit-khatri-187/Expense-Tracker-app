package com.expense_tracker.mapper;

import com.expense_tracker.dtos.RegistrationResponse;
import com.expense_tracker.dtos.WalletResponse;
import com.expense_tracker.entities.User;
import com.expense_tracker.entities.Wallet;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public RegistrationResponse toRegistrationResponse( User user){

        return RegistrationResponse.builder()
                .id(user.getUserId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();

    }

    public WalletResponse toWalletResponse(Wallet wallet){
        return WalletResponse.builder()
                .walletId(wallet.getWalletId())
                .walletName(wallet.getWalletName())
                .createdAt(wallet.getCreatedAt())
                .updatedAt(wallet.getUpdatedAt())
                .build();
    }

}
