package com.expense_tracker.repositories;

import com.expense_tracker.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {

    @Query("SELECT COUNT(w) FROM Wallet w WHERE w.walletName = :walletName AND w.user.userId = :userId")
    long countWalletByWalletName(@Param("walletName") String walletName, @Param("userId") Long userId);

    @Query("SELECT w FROM Wallet w WHERE w.walletId = :walletId AND w.user.userId = :userId")
    Optional<Wallet> findWalletByIdAndUserId(@Param("walletId") Long walletId, @Param("userId") Long userId);

    @Query("SELECT w FROM Wallet w WHERE w.user.userId = :userId")
    List<Wallet> getAllWalletOfUser(@Param("userId") Long userId);
}
