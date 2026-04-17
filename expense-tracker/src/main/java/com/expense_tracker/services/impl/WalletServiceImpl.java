package com.expense_tracker.services.impl;

import com.expense_tracker.dtos.WalletCreateUpdateRequest;
import com.expense_tracker.dtos.WalletResponse;
import com.expense_tracker.entities.Wallet;
import com.expense_tracker.exceptions.AlreadyExistException;
import com.expense_tracker.exceptions.NotAuthenticatedException;
import com.expense_tracker.exceptions.NotFoundException;
import com.expense_tracker.mapper.Mapper;
import com.expense_tracker.repositories.WalletRepository;
import com.expense_tracker.security.CustomUserDetails;
import com.expense_tracker.services.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final Mapper mapper;

    @Override
    public WalletResponse addWallet(WalletCreateUpdateRequest request) {

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

        Wallet saved = walletRepository.save(wallet);

        return mapper.toWalletResponse(saved);
    }

    @Override
    public WalletResponse updateWallet(Long walletId , WalletCreateUpdateRequest request) {

        log.info("Inside the update wallet service () : ");

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(customUserDetails == null){
            throw new NotAuthenticatedException("Please login");
        }

        Wallet wallet = walletRepository.findWalletByIdAndUserId(walletId,customUserDetails.getUserId())
                .orElseThrow( () -> new NotFoundException("Wallet not found") );

        wallet.setWalletName(request.getWalletName());

        Wallet updatedWallet = walletRepository.save(wallet);

        return mapper.toWalletResponse(updatedWallet);
    }

    @Override
    public WalletResponse deleteWallet(Long walletId) {

        log.info("Inside the delete wallet service () : ");

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(customUserDetails == null){
            throw new NotAuthenticatedException("Please login");
        }

        Wallet wallet = walletRepository.findWalletByIdAndUserId(walletId, customUserDetails.getUserId())
                .orElseThrow( () -> new NotFoundException("Wallet not found") );

        walletRepository.delete(wallet);

        return mapper.toWalletResponse(wallet);
    }

    @Override
    public List<WalletResponse> getAllWalletOfUser() {
        log.info("Inside the get All wallet of user service () : ");

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(customUserDetails == null){
            throw new NotAuthenticatedException("Please login");
        }


        List<Wallet> wallets =  walletRepository.getAllWalletOfUser(customUserDetails.getUserId());

        List<WalletResponse> walletResponses = wallets.stream().map(mapper::toWalletResponse)
                .toList();

        return walletResponses;

    }


}
