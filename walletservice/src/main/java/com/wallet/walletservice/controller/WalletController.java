package com.wallet.walletservice.controller;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.walletservice.dto.WalletRequest;
import com.wallet.walletservice.entity.Wallet;
import com.wallet.walletservice.service.WalletService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")

public class WalletController {
    private final WalletService walletService;

    @PostMapping("/wallet/create")
    public ResponseEntity<Wallet> createWallet() {

        Wallet wallet = walletService.createWallet();

        return ResponseEntity.ok(wallet);
    }

    @PostMapping("/wallet")
    public ResponseEntity<String> updateWallet(
            @Valid @RequestBody WalletRequest request) {

        walletService.updateBalance(request);

        return ResponseEntity.ok("Wallet updated successfully");
    }

    @GetMapping("/wallets/{walletId}")
    public ResponseEntity<BigDecimal> getBalance(
            @PathVariable UUID walletId) {

        return ResponseEntity.ok(
                walletService.getBalance(walletId));
    }

}
