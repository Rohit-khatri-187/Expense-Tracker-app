package com.expense_tracker.services.impl;

import com.expense_tracker.dtos.WalletCreateUpdateRequest;
import com.expense_tracker.entities.Wallet;
import com.expense_tracker.exceptions.AlreadyExistException;
import com.expense_tracker.exceptions.NotAuthenticatedException;
import com.expense_tracker.exceptions.NotFoundException;
import com.expense_tracker.repositories.WalletRepository;
import com.expense_tracker.security.CustomUserDetails;
import com.expense_tracker.services.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    public Wallet addWallet(WalletCreateUpdateRequest request) {

        log.info("Inside the add wallet service : () ");

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(customUserDetails == null){
            throw new NotAuthenticatedException("Please login");
        }

        boolean walletExists = walletRepository.countWalletByWalletName(request.getWalletName() , customUserDetails.getUserId()) > 0;

        if(walletExists){
            throw new AlreadyExistException("Wallet already exists");
        }



        Wallet wallet = new Wallet();
        wallet.setWalletName(request.getWalletName());
        wallet.setUser(customUserDetails.getUser());

        return walletRepository.save(wallet);
    }

    @Override
    public Wallet updateWallet(Long walletId , WalletCreateUpdateRequest request) {

        log.info("Inside the update wallet service () : ");

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(customUserDetails == null){
            throw new NotAuthenticatedException("Please login");
        }

        Wallet wallet = walletRepository.findWalletByIdAndUserId(walletId,customUserDetails.getUserId())
                .orElseThrow( () -> new NotFoundException("Wallet not found") );

        wallet.setWalletName(request.getWalletName());

        return walletRepository.save(wallet);
    }

    @Override
    public Wallet deleteWallet(Long walletId) {

        log.info("Inside the delete wallet service () : ");

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(customUserDetails == null){
            throw new NotAuthenticatedException("Please login");
        }

        Wallet wallet = walletRepository.findWalletByIdAndUserId(walletId, customUserDetails.getUserId())
                .orElseThrow( () -> new NotFoundException("Wallet not found") );

        walletRepository.delete(wallet);

        return wallet;
    }

    @Override
    public List<Wallet> getAllWalletOfUser() {
        log.info("Inside the get All wallet of user service () : ");

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(customUserDetails == null){
            throw new NotAuthenticatedException("Please login");
        }


        return walletRepository.getAllWalletOfUser(customUserDetails.getUserId());
    }


}
