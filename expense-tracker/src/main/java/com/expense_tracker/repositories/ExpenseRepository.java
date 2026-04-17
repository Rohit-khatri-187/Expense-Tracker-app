package com.expense_tracker.repositories;

import com.expense_tracker.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    @Query("SELECT e FROM Expense e WHERE e.expenseId = :expenseId AND e.user.userId = :userId")
    Optional<Expense> findByExpenseIdAndUserId(@Param("expenseId") Long expenseId, @Param("userId") Long userId);
}
