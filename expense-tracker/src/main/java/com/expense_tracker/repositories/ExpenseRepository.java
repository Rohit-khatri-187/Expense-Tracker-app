package com.expense_tracker.repositories;

import com.expense_tracker.entities.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    @Query("SELECT e FROM Expense e WHERE e.expenseId = :expenseId AND e.user.userId = :userId")
    Optional<Expense> findByExpenseIdAndUserId(@Param("expenseId") Long expenseId, @Param("userId") Long userId);

    @Query("SELECT e FROM Expense e WHERE e.user.userId = :userId")
    Page<Expense> getAllExpensesOfCurrentUser(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT e FROM Expense e WHERE e.user.userId = :userId")
    List<Expense> getAllExpenses(@Param("userId") Long userId );


    @Query("""
    SELECT e FROM Expense e
    WHERE e.user.userId = :userId
    AND e.expenseDate BETWEEN :startDate AND :endDate
    ORDER BY e.expenseDate DESC
    """)
    List<Expense> findExpenseLast30Days(
            @Param("userId") Long userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}
