package com.example.pwa.Model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class WalletTransferResponse {

    @NotNull(message = "Source wallet ID is required")
    private Integer walletId;

    @NotNull(message = "Destination wallet ID is required")
    private Integer toWalletId;

    @Positive(message = "Transfer amount must be greater than zero")
    private double balance;


    public WalletTransferResponse() {
    }

    public WalletTransferResponse(Integer walletId, Integer toWalletId, double balance) {
        this.walletId = walletId;
        this.toWalletId = toWalletId;
        this.balance = balance;
    }

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public Integer getToWalletId() {
        return toWalletId;
    }

    public void setToWalletId(Integer toWalletId) {
        this.toWalletId = toWalletId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
