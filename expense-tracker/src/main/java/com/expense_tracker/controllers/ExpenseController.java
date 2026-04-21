package com.expense_tracker.controllers;

import com.expense_tracker.dtos.*;
import com.expense_tracker.services.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/expenses")
@RequiredArgsConstructor
@RestController
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/add")
    public ResponseEntity<Response<ExpenseResponseDTO>> addExpense(@Valid @RequestBody ExpenseRequestDTO expenseRequestDTO){

        ExpenseResponseDTO savedExpense = expenseService.addExpense(expenseRequestDTO);

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "expense added successfully",
                        savedExpense
                )
        );

    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<Response<ExpenseResponseDTO>> updateExpense(@PathVariable Long expenseId ,@Valid @RequestBody ExpenseRequestDTO expenseRequestDTO){

        ExpenseResponseDTO updatedExpense = expenseService.updateExpense(expenseId,expenseRequestDTO);

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "expense updated successfully",
                        updatedExpense
                )
        );

    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Response<ExpenseResponseDTO>> deleteExpense(@PathVariable Long expenseId ){

        ExpenseResponseDTO deleteExpense = expenseService.deleteExpense(expenseId);

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "expense deleted successfully",
                        deleteExpense
                )
        );

    }

    @GetMapping
    public ResponseEntity<Response<PageResponse<ExpenseResponseDTO>>> getExpenses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "expenseDate") String sortBy
    ){

        PageResponse<ExpenseResponseDTO> expenses = expenseService.getExpenses(page,size,sortBy);

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "Expenses retrieved",
                        expenses
                )
        );

    }

    @GetMapping("/monthly")
    public ResponseEntity<Response<List<ExpenseResponseDTO>>> getMonthlyExpenseData() {

        List<ExpenseResponseDTO> expenses = expenseService.MonthlyExpenseData();

        return ResponseEntity.ok().body(
                new Response<>(
                        true,
                        "expenses retrieved",
                        expenses
                )
        );
    }

}
