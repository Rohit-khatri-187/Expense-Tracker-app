package com.expense_tracker.dtos;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class WalletCreateUpdateRequest {

    @Size(min = 3 , max = 20 , message = "Wallet name should be between 3-20 characters")
    private String walletName;

}
