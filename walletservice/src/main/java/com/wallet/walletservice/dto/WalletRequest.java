package com.wallet.walletservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.wallet.walletservice.enums.OperationType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class WalletRequest {

    @NotNull(message = "Wallet id is required")
    private UUID walletId;

    @NotNull(message = "Operation type is required")
    private OperationType operationType;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;
}