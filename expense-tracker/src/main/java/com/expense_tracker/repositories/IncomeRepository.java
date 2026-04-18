package com.expense_tracker.repositories;

import com.expense_tracker.entities.Income;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Long> {

    @Query("SELECT i from Income i where i.incomeId =:incomeId AND i.user.userId =:userId")
    Optional<Income> findIncomeByIncomeIdAndUserId(@Param("incomeId") Long incomeId , @Param("userId") Long userId );

    @Query("SELECT i from Income i where i.user.userId =:userId")
    Page<Income> findAllIncomeOfUser(@Param("userId") Long userId , Pageable pageable);

}
