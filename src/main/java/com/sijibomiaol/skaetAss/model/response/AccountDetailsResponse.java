package com.sijibomiaol.skaetAss.model.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AccountDetailsResponse implements Serializable {
    private String id;
    private String accountNumber;
    private BigDecimal balance;
}
