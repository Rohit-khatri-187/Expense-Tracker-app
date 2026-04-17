package com.expense_tracker.controllers;


import com.expense_tracker.dtos.Response;
import com.expense_tracker.dtos.WalletCreateUpdateRequest;
import com.expense_tracker.dtos.WalletResponse;
import com.expense_tracker.entities.Wallet;
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
    public ResponseEntity<Response<WalletResponse>> addWallet(@Valid @RequestBody WalletCreateUpdateRequest request){
        WalletResponse savedWallet = walletService.addWallet(request);

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "Wallet added successfully",
                        savedWallet
                )
        );
    }

    @PutMapping("/{walletId}")
    public ResponseEntity<Response<WalletResponse>> updateWallet(@PathVariable Long walletId ,@RequestBody WalletCreateUpdateRequest request){

        WalletResponse updatedWallet = walletService.updateWallet(walletId,request);

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "Wallet updated successfully",
                        updatedWallet
                )
        );

    }

    @DeleteMapping("/{walletId}")
    public ResponseEntity<Response<WalletResponse>> deleteWallet(@PathVariable Long walletId ){

        WalletResponse deletedWallet = walletService.deleteWallet(walletId);

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "Wallet deleted successfully",
                        deletedWallet
                )
        );

    }

    @GetMapping
    public ResponseEntity<Response<List<WalletResponse>>> getAllWallets(){

        List<WalletResponse> wallets = walletService.getAllWalletOfUser();

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "Wallets retrieved successfully",
                        wallets
                )
        );

    }

}
