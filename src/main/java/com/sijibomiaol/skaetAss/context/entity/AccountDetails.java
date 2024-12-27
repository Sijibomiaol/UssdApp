package com.sijibomiaol.skaetAss.context.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name="account")
@Data
public class AccountDetails extends BaseCode implements java.io.Serializable  {
    @Column(name = "account _number")
    private String accountNumber;

    @Column(name= "account_balance")
    private BigDecimal balance;
}
