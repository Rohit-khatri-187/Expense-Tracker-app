package com.expense_tracker.services;

import com.expense_tracker.dtos.WalletCreateUpdateRequest;
import com.expense_tracker.dtos.WalletResponse;
import com.expense_tracker.entities.Wallet;

import java.util.List;

public interface WalletService {

    public WalletResponse addWallet(WalletCreateUpdateRequest request);

    public WalletResponse updateWallet(Long walletId , WalletCreateUpdateRequest request);

    public WalletResponse deleteWallet(Long walletId);

    public List<WalletResponse> getAllWalletOfUser();

}
