package com.expense_tracker.services.impl;

import com.expense_tracker.config.CurrentUser;
import com.expense_tracker.dtos.IncomeRequestDTO;
import com.expense_tracker.dtos.IncomeResponseDTO;
import com.expense_tracker.dtos.PageResponse;
import com.expense_tracker.entities.Income;
import com.expense_tracker.entities.User;
import com.expense_tracker.entities.Wallet;
import com.expense_tracker.exceptions.NotAuthenticatedException;
import com.expense_tracker.exceptions.NotFoundException;
import com.expense_tracker.mapper.Mapper;
import com.expense_tracker.repositories.IncomeRepository;
import com.expense_tracker.repositories.WalletRepository;
import com.expense_tracker.services.IncomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;
    private final WalletRepository walletRepository;
    private final Mapper mapper;
    private final CurrentUser currentUser;

    @Override
    public IncomeResponseDTO addIncome(IncomeRequestDTO request) {

        log.info("Inside tht add Income service () : ");

        User user = currentUser.getCurrentUserData();

        if(user == null){
            throw new NotAuthenticatedException("Please Login");
        }

        Wallet wallet = walletRepository.findWalletByIdAndUserId(request.getWalletId(),user.getUserId())
                .orElseThrow(() -> new NotFoundException("Wallet not found"));

        Income income = new Income();
        income.setIncomeName(request.getIncomeName());
        income.setIncomeDate(request.getIncomeDate());
        income.setAmount(request.getAmount());
        income.setUser(user);
        income.setWallet(wallet);

        Income savedIncome = incomeRepository.save(income);

        return mapper.toIncomeResponseDTO(savedIncome);
    }

    @Override
    public IncomeResponseDTO updateIncome(Long incomeId, IncomeRequestDTO request) {

        log.info("Inside update Income service () : ");

        User user = currentUser.getCurrentUserData();

        if(user == null){
            throw new NotAuthenticatedException("Please login");
        }

        Income income = incomeRepository.findIncomeByIncomeIdAndUserId(incomeId, user.getUserId())
                .orElseThrow(()-> new NotFoundException("Income not found"));

        income.setIncomeName(request.getIncomeName());
        income.setAmount(request.getAmount());
        income.setIncomeDate(request.getIncomeDate());
        income.setWallet(income.getWallet());

        Income updatedIncome =  incomeRepository.save(income);

        return mapper.toIncomeResponseDTO(updatedIncome);
    }

    @Override
    public IncomeResponseDTO deleteIncome(Long incomeId) {

        log.info("Inside delete Income service () : ");

        User user = currentUser.getCurrentUserData();

        if(user == null){
            throw new NotAuthenticatedException("Please login");
        }

        Income income = incomeRepository.findIncomeByIncomeIdAndUserId(incomeId, user.getUserId())
                .orElseThrow(()-> new NotFoundException("Income not found"));

        incomeRepository.delete(income);

        return mapper.toIncomeResponseDTO(income);
    }

    @Override
    public PageResponse<IncomeResponseDTO> getIncomes(int page, int size, String sortBy) {

        log.info("Inside get Income service () : ");

        User user = currentUser.getCurrentUserData();

        if(user == null){
            throw new NotAuthenticatedException("Please login");
        }

        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy).descending());

        Page<Income> incomePage = incomeRepository.findAllIncomeOfUser(user.getUserId(), pageable);

        List<IncomeResponseDTO> incomes = incomePage.stream().map(mapper::toIncomeResponseDTO).toList();


        return new PageResponse<>(
                incomes,
                incomePage.getNumber(),
                incomePage.getSize(),
                incomePage.getTotalElements(),
                incomePage.getTotalPages(),
                incomePage.isLast()
        );
    }

    @Override
    public List<IncomeResponseDTO> MonthlyIncomeData() {

        log.info("Inside the Monthly Income Data : ()");

        User user = currentUser.getCurrentUserData();

        if(user == null){
            throw new NotAuthenticatedException("Please login");
        }

        LocalDate today = LocalDate.now();

        LocalDate startDate = today.minusDays(30);

        List<Income> incomes = incomeRepository.findIncomeLast30Days(user.getUserId(),startDate,today);


        return incomes.stream()
                .map(mapper::toIncomeResponseDTO).toList();
    }
}
