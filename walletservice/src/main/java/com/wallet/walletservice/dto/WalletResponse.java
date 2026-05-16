package com.wallet.walletservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WalletResponse {

    private UUID walletId;

    private BigDecimal balance;
}