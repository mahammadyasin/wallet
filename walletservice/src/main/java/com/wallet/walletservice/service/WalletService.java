package com.wallet.walletservice.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.wallet.walletservice.dto.WalletRequest;
import com.wallet.walletservice.entity.Wallet;
import com.wallet.walletservice.enums.OperationType;
import com.wallet.walletservice.exception.InsufficientBalanceException;
import com.wallet.walletservice.exception.WalletNotFoundException;
import com.wallet.walletservice.repository.WalletRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    public Wallet createWallet() {

        Wallet wallet = new Wallet();

        wallet.setId(UUID.randomUUID());

        wallet.setBalance(BigDecimal.ZERO);

        return walletRepository.save(wallet);
    }

    @Transactional
    public void updateBalance(WalletRequest request) {

        Wallet wallet = walletRepository.findByIdForUpdate(request.getWalletId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (request.getOperationType() == OperationType.DEPOSIT) {

            wallet.setBalance(
                    wallet.getBalance().add(request.getAmount()));

        } else if (request.getOperationType() == OperationType.WITHDRAW) {

            if (wallet.getBalance().compareTo(request.getAmount()) < 0) {
                throw new InsufficientBalanceException("Insufficient balance");
            }

            wallet.setBalance(
                    wallet.getBalance().subtract(request.getAmount()));
        }

        walletRepository.save(wallet);
    }

    public BigDecimal getBalance(UUID walletId) {

        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));

        return wallet.getBalance();

    }
}
