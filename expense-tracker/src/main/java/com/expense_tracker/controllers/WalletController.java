package com.expense_tracker.controllers;


import com.expense_tracker.dtos.Response;
import com.expense_tracker.dtos.WalletCreateUpdateRequestDTO;
import com.expense_tracker.dtos.WalletResponseDTO;
import com.expense_tracker.services.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/wallets")
@RestController
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/add")
    public ResponseEntity<Response<WalletResponseDTO>> addWallet(@Valid @RequestBody WalletCreateUpdateRequestDTO request){
        WalletResponseDTO savedWallet = walletService.addWallet(request);

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "Wallet added successfully",
                        savedWallet
                )
        );
    }

    @PutMapping("/{walletId}")
    public ResponseEntity<Response<WalletResponseDTO>> updateWallet(@PathVariable Long walletId , @RequestBody WalletCreateUpdateRequestDTO request){

        WalletResponseDTO updatedWallet = walletService.updateWallet(walletId,request);

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "Wallet updated successfully",
                        updatedWallet
                )
        );

    }

    @DeleteMapping("/{walletId}")
    public ResponseEntity<Response<WalletResponseDTO>> deleteWallet(@PathVariable Long walletId ){

        WalletResponseDTO deletedWallet = walletService.deleteWallet(walletId);

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "Wallet deleted successfully",
                        deletedWallet
                )
        );

    }

    @GetMapping
    public ResponseEntity<Response<List<WalletResponseDTO>>> getAllWallets(){

        List<WalletResponseDTO> wallets = walletService.getAllWalletOfUser();

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "Wallets retrieved successfully",
                        wallets
                )
        );

    }

}
