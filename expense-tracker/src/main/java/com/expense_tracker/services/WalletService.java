package com.expense_tracker.services;

import com.expense_tracker.dtos.WalletCreateUpdateRequestDTO;
import com.expense_tracker.dtos.WalletResponseDTO;

import java.util.List;

public interface WalletService {

    public WalletResponseDTO addWallet(WalletCreateUpdateRequestDTO request);

    public WalletResponseDTO updateWallet(Long walletId , WalletCreateUpdateRequestDTO request);

    public WalletResponseDTO deleteWallet(Long walletId);

    public List<WalletResponseDTO> getAllWalletOfUser();

}
