package com.example.pwa.Model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class WalletResponse {

    @Positive(message = "Amount must be greater than zero")
    private double amount;

    @NotNull(message = "User ID is required")
    private Integer userId;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public WalletResponse() {
    }

    public WalletResponse(double amount, Integer userId) {
        this.amount = amount;
        this.userId = userId;
    }
}
