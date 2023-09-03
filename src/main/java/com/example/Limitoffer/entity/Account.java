package com.example.Limitoffer.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private Long customerId;
    private double accountLimit;
    private double perTransactionLimit;
    private double lastAccountLimit;
    private double lastPerTransactionLimit;
    @Timestamp
    private LocalDateTime accountLimitUpdateTime;
    @Timestamp
    private LocalDateTime perTransactionLimitUpdateTime;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public double getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(double accountLimit) {
        this.accountLimit = accountLimit;
    }

    public double getPerTransactionLimit() {
        return perTransactionLimit;
    }

    public void setPerTransactionLimit(double perTransactionLimit) {
        this.perTransactionLimit = perTransactionLimit;
    }

    public double getLastAccountLimit() {
        return lastAccountLimit;
    }

    public void setLastAccountLimit(double lastAccountLimit) {
        this.lastAccountLimit = lastAccountLimit;
    }

    public double getLastPerTransactionLimit() {
        return lastPerTransactionLimit;
    }

    public void setLastPerTransactionLimit(double lastPerTransactionLimit) {
        this.lastPerTransactionLimit = lastPerTransactionLimit;
    }

    public LocalDateTime getAccountLimitUpdateTime() {
        return accountLimitUpdateTime;
    }

    public void setAccountLimitUpdateTime(LocalDateTime accountLimitUpdateTime) {
        this.accountLimitUpdateTime = accountLimitUpdateTime;
    }

    public LocalDateTime getPerTransactionLimitUpdateTime() {
        return perTransactionLimitUpdateTime;
    }

    public void setPerTransactionLimitUpdateTime(LocalDateTime perTransactionLimitUpdateTime) {
        this.perTransactionLimitUpdateTime = perTransactionLimitUpdateTime;
    }




}
