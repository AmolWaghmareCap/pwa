package com.example.pwa.Services;

import com.example.pwa.Entity.Transactions;
import com.example.pwa.Entity.User;
import com.example.pwa.Entity.Wallet;
import com.example.pwa.Exception.CustomException;
import com.example.pwa.Model.WalletResponse;
import com.example.pwa.Model.WalletTransferResponse;
import com.example.pwa.Repository.TransactionRepo;
import com.example.pwa.Repository.WalletRepo;
import com.example.pwa.Repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class WalletSer {

    @Autowired
    private userRepo userRepo;

    @Autowired
    private WalletRepo walletRepo;

    @Autowired
    private TransactionRepo txRepo;


    public WalletResponse addAmountToWallet(WalletResponse request) {
        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));

        Wallet wallet = walletRepo.findByUser(user)
                .orElseThrow(() -> new CustomException("Wallet not found", HttpStatus.NOT_FOUND));

        wallet.setBalance(wallet.getBalance() + request.getAmount());
        walletRepo.save(wallet);

        return new WalletResponse(wallet.getBalance(), wallet.getId());

    }


    public WalletResponse getBalance(int userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));

        Wallet wallet = walletRepo.findByUser(user)
                .orElseThrow(() -> new CustomException("Wallet not found", HttpStatus.NOT_FOUND));

        return new WalletResponse(wallet.getBalance(),user.getId());
    }


    public String transferFunds(WalletTransferResponse request) {
        Wallet fromWallet = walletRepo.findById(request.getWalletId())
                .orElseThrow(() -> new CustomException("Source wallet not found", HttpStatus.NOT_FOUND));

        Wallet toWallet = walletRepo.findById(request.getToWalletId())
                .orElseThrow(() -> new CustomException("Destination wallet not found", HttpStatus.NOT_FOUND));

        if (request.getBalance() > fromWallet.getBalance())
            throw new CustomException("Insufficient balance. Kindly review your balance.", HttpStatus.BAD_REQUEST);

        // Perform transfer
        fromWallet.setBalance(fromWallet.getBalance() - request.getBalance());
        toWallet.setBalance(toWallet.getBalance() + request.getBalance());

        walletRepo.save(fromWallet);
        walletRepo.save(toWallet);

        Transactions txn = new Transactions();
        txn.setWallet(fromWallet);
        txn.setStatus("TRANSFER");
        txn.setAmount(request.getBalance());
        txn.setDate(LocalDate.now());
        txn.setTime(LocalTime.now());

        Transactions save = txRepo.save(txn);
        return "Transfer done successfully. Use transfer id " + txn.getId() + " for further reference.";
    }



}
