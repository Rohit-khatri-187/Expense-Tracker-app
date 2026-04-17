package com.expense_tracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletResponse {

    Long walletId;
    String walletName;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
