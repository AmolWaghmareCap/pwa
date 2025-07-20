package com.example.pwa.Repository;

import com.example.pwa.Entity.User;
import com.example.pwa.Entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Currency;
import java.util.Optional;

public interface WalletRepo extends JpaRepository<Wallet, Integer> {

    // Find wallet by user
    Optional<Wallet> findByUser(User user);

    // Optional: Find wallet by user ID if you prefer using ID directly
    @Query("SELECT w FROM Wallet w WHERE w.user.id = :userId")
    Optional<Wallet> findByUserId(@Param("userId") Integer userId);

    // Optional: Find wallet by user and currency
    Optional<Wallet> findByUserAndCurrency(User user, Currency currency);


    // Custom query to check wallet balance threshold
    @Query("SELECT w FROM Wallet w WHERE w.id = :walletId AND w.balance >= :amount")
    Optional<Wallet> findWalletWithSufficientBalance(@Param("walletId") Integer walletId, @Param("amount") Double amount);
}
