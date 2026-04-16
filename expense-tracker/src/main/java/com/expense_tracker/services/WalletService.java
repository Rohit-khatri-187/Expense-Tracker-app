package com.expense_tracker.services;

import com.expense_tracker.dtos.WalletCreateUpdateRequest;
import com.expense_tracker.entities.Wallet;

import java.util.List;

public interface WalletService {

    public Wallet addWallet(WalletCreateUpdateRequest request);

    public Wallet updateWallet(Long walletId , WalletCreateUpdateRequest request);

    public Wallet deleteWallet(Long walletId);

    public List<Wallet> getAllWalletOfUser();

}
