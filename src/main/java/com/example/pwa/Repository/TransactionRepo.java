package com.example.pwa.Repository;

import com.example.pwa.Entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transactions, Integer> {

    // Find all transactions for a specific wallet
    List<Transactions> findByWalletId(Integer walletId);

    // Optional: Find transactions by status (e.g., "TRANSFER", "DEPOSIT", "WITHDRAW")
    List<Transactions> findByStatus(String status);

    // Optional: Find transactions within a date range
    List<Transactions> findByDateBetween(LocalDate startDate, LocalDate endDate);

    // Optional: Find transactions by wallet and status
    List<Transactions> findByWalletIdAndStatus(Integer walletId, String status);
}
