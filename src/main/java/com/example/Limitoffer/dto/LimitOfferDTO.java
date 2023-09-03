package com.example.Limitoffer.dto;

import jakarta.persistence.Column;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDateTime;

public class LimitOfferDTO {

    @NotNull
    private Long accountId;

    private String limitType;
    private Long newLimit;

    private LocalDateTime offerActivationTime;

    private LocalDateTime offerExpiryTime;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }

    public Long getNewLimit() {
        return newLimit;
    }

    public void setNewLimit(Long newLimit) {
        this.newLimit = newLimit;
    }

    public LocalDateTime getOfferActivationTime() {
        return offerActivationTime;
    }

    public void setOfferActivationTime(LocalDateTime offerActivationTime) {
        this.offerActivationTime = offerActivationTime;
    }

    public LocalDateTime getOfferExpiryTime() {
        return offerExpiryTime;
    }

    public void setOfferExpiryTime(LocalDateTime offerExpiryTime) {
        this.offerExpiryTime = offerExpiryTime;
    }
}
