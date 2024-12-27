package com.sijibomiaol.skaetAss.service;

import java.math.BigDecimal;

public interface AccountTransactionService {
    BigDecimal deposit(BigDecimal amount, String phoneNumber);

    BigDecimal withdraw(BigDecimal amount, String phoneNumber);

    BigDecimal checkBalance(String phoneNumber);
}
