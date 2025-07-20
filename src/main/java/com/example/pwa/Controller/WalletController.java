package com.example.pwa.Controller;

import com.example.pwa.Exception.CustomException;
import com.example.pwa.Model.WalletResponse;
import com.example.pwa.Model.WalletTransferResponse;
import com.example.pwa.Services.WalletSer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/basePath/v1/users/wallet")
public class WalletController {

    @Autowired
    private final WalletSer walletService;

    public WalletController(WalletSer walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<WalletResponse> getBalance(@PathVariable String userId) {
        // Validate that userId is a number
        if (!userId.matches("\\d+")) {
            throw new CustomException("Bad request. User id cannot contain characters.", HttpStatus.BAD_REQUEST);
        }

        WalletResponse response = walletService.getBalance(Integer.parseInt(userId));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@Valid @RequestBody WalletTransferResponse request) {
        String response = walletService.transferFunds(request);

        return ResponseEntity.ok(response);
    }



}
