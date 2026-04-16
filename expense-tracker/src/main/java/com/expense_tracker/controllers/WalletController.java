package com.expense_tracker.controllers;


import com.expense_tracker.dtos.Response;
import com.expense_tracker.dtos.WalletCreateUpdateRequest;
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
    public ResponseEntity<Response<Wallet>> addWallet(@Valid @RequestBody WalletCreateUpdateRequest request){
        Wallet savedWallet = walletService.addWallet(request);

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "Wallet added successfully",
                        savedWallet
                )
        );
    }

    @PutMapping("/{walletId}")
    public ResponseEntity<Response<Wallet>> updateWallet(@PathVariable Long walletId ,@RequestBody WalletCreateUpdateRequest request){

        Wallet updatedWallet = walletService.updateWallet(walletId,request);

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "Wallet added successfully",
                        updatedWallet
                )
        );

    }

    @DeleteMapping("/{walletId}")
    public ResponseEntity<Response<Wallet>> deleteWallet(@PathVariable Long walletId ){

        Wallet deletedWallet = walletService.deleteWallet(walletId);

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "Wallet added successfully",
                        deletedWallet
                )
        );

    }

    @GetMapping
    public ResponseEntity<Response<List<Wallet>>> getAllWallets(@PathVariable Long walletId ){

        List<Wallet> wallets = walletService.getAllWalletOfUser();

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "Wallet added successfully",
                        wallets
                )
        );

    }

}
