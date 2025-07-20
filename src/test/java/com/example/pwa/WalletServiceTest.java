package com.example.pwa;

import com.example.pwa.Entity.Currency;
import com.example.pwa.Entity.User;
import com.example.pwa.Entity.Wallet;
import com.example.pwa.Model.WalletResponse;
import com.example.pwa.Model.WalletTransferResponse;
import com.example.pwa.Repository.WalletRepo;
import com.example.pwa.Repository.userRepo;
import com.example.pwa.Services.WalletSer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {

    @Mock
    private userRepo userRepo;
    @Mock private WalletRepo walletRepo;
    @InjectMocks
    private WalletSer walletService;

    @Test
    void shouldAddAmountToWallet() {
        User user = new User();
        Wallet wallet = new Wallet();
        wallet.setBalance(100.0);

        WalletResponse request = new WalletResponse();
        request.setAmount(50.0);
        request.setUserId(1);

        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(walletRepo.findByUser(user)).thenReturn(Optional.of(wallet));

        WalletResponse result = walletService.addAmountToWallet(request);

        assertEquals(50.0, result.getAmount());
    }

    @Test
    void shouldReturnBalanceForValidUser() {
        User user = new User();
        user.setId(1223);
        Currency currency = new Currency();
        currency.setAbbreviation("Rs");
        Wallet wallet = new Wallet();
        wallet.setBalance(1000.0);
        wallet.setCurrency(currency);
        wallet.setUser(user);

        when(userRepo.findById(1223)).thenReturn(Optional.of(user));
        when(walletRepo.findByUser(user)).thenReturn(Optional.of(wallet));

        WalletResponse response = walletService.getBalance(1223);

        assertEquals(1000.0, response.getAmount(), 0.001);
    }

    @Test
    void shouldTransferFundsSuccessfully() {
        Wallet fromWallet = new Wallet();
        fromWallet.setId(12334);
        fromWallet.setBalance(1000);

        Wallet toWallet = new Wallet();
        toWallet.setId(7888);
        toWallet.setBalance(500);

        WalletTransferResponse request = new WalletTransferResponse();
        request.setWalletId(12334);
        request.setToWalletId(7888);
        request.setBalance(100);

        when(walletRepo.findById(12334)).thenReturn(Optional.of(fromWallet));
        when(walletRepo.findById(7888)).thenReturn(Optional.of(toWallet));

        String response = walletService.transferFunds(request);

        assertEquals("Transfer done successfully. Use transfer id",
                response.substring(0, 41));
    }


}
