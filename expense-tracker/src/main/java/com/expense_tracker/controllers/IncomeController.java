package com.expense_tracker.controllers;

import com.expense_tracker.dtos.*;
import com.expense_tracker.services.IncomeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/incomes")
@RequiredArgsConstructor
@RestController
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping("/add")
    public ResponseEntity<Response<IncomeResponseDTO>> addIncome(@Valid @RequestBody IncomeRequestDTO incomeRequestDTO){

        IncomeResponseDTO savedIncome = incomeService.addIncome(incomeRequestDTO);

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "income added successfully",
                        savedIncome
                )
        );

    }

    @PutMapping("/{incomeId}")
    public ResponseEntity<Response<IncomeResponseDTO>> updateIncome(@PathVariable Long incomeId ,@Valid @RequestBody IncomeRequestDTO incomeRequestDTO){

        IncomeResponseDTO updatedIncome = incomeService.updateIncome(incomeId,incomeRequestDTO);

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "income updated successfully",
                        updatedIncome
                )
        );

    }

    @DeleteMapping("/{incomeId}")
    public ResponseEntity<Response<IncomeResponseDTO>> deleteIncome(@PathVariable Long incomeId ){

        IncomeResponseDTO deletedIncome = incomeService.deleteIncome(incomeId);

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "income deleted successfully",
                        deletedIncome
                )
        );

    }

    @GetMapping
    public ResponseEntity<Response<PageResponse<IncomeResponseDTO>>> getIncomes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "incomeDate") String sortBy
    ){

        PageResponse<IncomeResponseDTO> incomes = incomeService.getIncomes(page,size,sortBy);

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "Income retrieved",
                        incomes
                )
        );

    }




}
